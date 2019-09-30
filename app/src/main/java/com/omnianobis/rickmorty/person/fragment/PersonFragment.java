package com.omnianobis.rickmorty.person.fragment;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import com.omnianobis.rickmorty.R;
import com.omnianobis.rickmorty.utils.OnBackPressedListener;
import com.omnianobis.rickmorty.allcharacters.fragment.AllCharactersFragment;
import com.omnianobis.rickmorty.app.RickMortyApplication;
import com.omnianobis.rickmorty.core.model.response.singleperson.SinglePersonResponse;
import com.omnianobis.rickmorty.databinding.FragmentPersonBinding;
import com.omnianobis.rickmorty.person.viewmodel.PersonViewModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PersonFragment extends Fragment implements OnBackPressedListener, Callback<SinglePersonResponse> { //

    private Callback<SinglePersonResponse> userCallback;
    PersonViewModel personViewModel;
    ViewModelProvider.Factory viewModelFactory;
    private int idPerson;

    @Nullable
    @Override
    public android.view.View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                                          @Nullable Bundle savedInstanceState) {

        Bundle bundle = getArguments();
        idPerson = bundle.getInt("idPerson");
        userCallback = this;

        FragmentPersonBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_person, container, false);

        personViewModel = ViewModelProviders.of(this, viewModelFactory).get(PersonViewModel.class);
        personViewModel.id_Person(idPerson);
        binding.setViewmodel(personViewModel);

        RickMortyApplication.getRestClientInstance().getApiRickMorty().getPerson("" + idPerson).enqueue(userCallback);

        return binding.getRoot();
    }

    @Override
    public void onResponse(Call<SinglePersonResponse> call, Response<SinglePersonResponse> response) {
        personViewModel.namePerson.set(response.body().getName());
        personViewModel.statusPerson.set(response.body().getStatus());
        personViewModel.speciesPerson.set(response.body().getSpecies());
        personViewModel.typePerson.set(response.body().getType());
        personViewModel.genderPerson.set(response.body().getGender());
        personViewModel.createdPerson.set(response.body().getCreated());
        personViewModel.imagePerson.set(response.body().getImage());
    }

    @Override
    public void onFailure(Call<SinglePersonResponse> call, Throwable t) {
        Toast.makeText(getActivity(), R.string.something_is_wrong, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onBackPressed() {
        FragmentActivity activity = (FragmentActivity) getView().getContext();
        activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_login,
                new AllCharactersFragment()).commit();
    }
}