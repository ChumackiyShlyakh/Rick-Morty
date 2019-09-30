package com.omnianobis.rickmorty.core.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.Toast;

import com.omnianobis.rickmorty.R;
import com.omnianobis.rickmorty.utils.Utils;

public class SplashActivity extends AppCompatActivity {

    private final int SPLASH_DISPLAY_LENGTH = 500;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        imageView = (ImageView) findViewById(R.id.image);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (Utils.isNetworkConnected(getApplication())) {
                    Intent loginIntent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(loginIntent);
                    finish();

                } else {
                    imageView.setImageDrawable(getResources().getDrawable(R.drawable.rickmortyno1));
                    Toast.makeText(getApplication(), R.string.check_network_connection, Toast.LENGTH_LONG).show();
                }
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}