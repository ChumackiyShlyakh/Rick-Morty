package com.omnianobis.rickmorty.person.viewmodel;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.databinding.Bindable;
import android.databinding.ObservableField;

import com.omnianobis.rickmorty.core.arch.BaseAndroidViewModel;
import com.omnianobis.rickmorty.core.model.response.singleperson.SinglePersonResponse;
import com.omnianobis.rickmorty.net.Resource;
import com.omnianobis.rickmorty.person.PersonData;

import java.util.List;

public class PersonViewModel extends BaseAndroidViewModel {

    private LiveData<Resource<List<SinglePersonResponse>>> following;
    private PersonData personData;
    public final ObservableField<String> namePerson = new ObservableField<>();
    public final ObservableField<String> statusPerson = new ObservableField<>();
    public final ObservableField<String> speciesPerson = new ObservableField<>();
    public final ObservableField<String> typePerson = new ObservableField<>();
    public final ObservableField<String> genderPerson = new ObservableField<>();
    public final ObservableField<String> createdPerson = new ObservableField<>();
    public final ObservableField<String> imagePerson = new ObservableField<>();

    private int id_res = 1;

    @Bindable
    public ObservableField<String> imageUrl = imagePerson;

    public PersonViewModel(Application application) {
        super(application);
        personData = new PersonData(application);
        following = personData.loadPerson("" + id_res);
    }

    public void id_Person(int id) {
        this.id_res = id;
    }

}