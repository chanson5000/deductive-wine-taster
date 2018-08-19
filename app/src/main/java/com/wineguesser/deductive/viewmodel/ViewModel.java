package com.wineguesser.deductive.viewmodel;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.wineguesser.deductive.repository.Repository;

import java.util.List;

public class ViewModel extends android.arch.lifecycle.ViewModel {

    private Repository mRepository;

    public ViewModel() {
        mRepository = new Repository();
    }
//
//    @NonNull
//    public LiveData<List<String>> countriesList() {
//        return mRepository.getCountriesList();
//    }

    @NonNull
    public LiveData<List<String>> redVarietiesList() {
        return mRepository.getRedVarietiesList();
    }

    @NonNull
    public LiveData<List<String>> whiteVarietiesList() {
        return mRepository.getWhiteVarietiesList();
    }

}
