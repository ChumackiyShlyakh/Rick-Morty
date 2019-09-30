package com.omnianobis.rickmorty.net;

import com.omnianobis.rickmorty.R;
import com.omnianobis.rickmorty.utils.ApiConstants;

import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.media.CamcorderProfile.get;

public class RestClient {

    private static final String SSL = "TLSv1.2";
    private static HttpLoggingInterceptor.Level LEVEL_LOG = HttpLoggingInterceptor.Level.BODY;
    public Retrofit retrofit;
    private ApiRickMorty apiRickMorty;

    public RestClient() {
        initRestClient();
        apiRickMorty = retrofit.create(ApiRickMorty.class);
    }

    public ApiRickMorty getApiRickMorty() {
        return apiRickMorty;
    }

    private void initRestClient() {
        try {
            retrofit = new Retrofit.Builder()
                    .baseUrl(ApiConstants.RICK_MORTY_BASE_URL)
                    .client(createOkHttpBuilder().build())
                    .addCallAdapterFactory(new LiveDataCallAdapterFactory())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
    }

    private OkHttpClient.Builder createOkHttpBuilder() throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.sslSocketFactory(createSSLSocketFactory(), createX509TrustManager());
//        builder.addInterceptor(createAuthInterceptor());
        builder.addInterceptor(createLoggedInterceptor());
        return builder;
    }

    private SSLSocketFactory createSSLSocketFactory() throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        SSLContext sslcontext = SSLContext.getInstance(SSL);
        sslcontext.init(null, new TrustManager[]{createX509TrustManager()}, null);
        SSLSocketFactory noSSLv3Factory = new NoSSLv3SocketFactory(sslcontext.getSocketFactory());

        return noSSLv3Factory;
    }

    private X509TrustManager createX509TrustManager() throws KeyStoreException, NoSuchAlgorithmException {
        X509TrustManager trustManager = (X509TrustManager) getTrustManagers()[0];
        return trustManager;
    }

    private TrustManager[] getTrustManagers() throws NoSuchAlgorithmException, KeyStoreException {
        TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        trustManagerFactory.init((KeyStore) null);
        TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();
        if (trustManagers.length != 1 || !(trustManagers[0] instanceof X509TrustManager)) {
            throw new IllegalStateException(get(R.string.unexpected_default_trust_managers) + Arrays.toString(trustManagers));
        }
        return trustManagers;
    }

    private HttpLoggingInterceptor createLoggedInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(LEVEL_LOG);
        return interceptor;
    }
}