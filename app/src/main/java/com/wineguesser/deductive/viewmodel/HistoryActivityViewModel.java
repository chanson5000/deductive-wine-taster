package com.wineguesser.deductive.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.wineguesser.deductive.model.ConclusionRecord;
import com.wineguesser.deductive.repository.ConclusionsRepository;

import java.util.List;

public class HistoryActivityViewModel extends ViewModel {

    private final ConclusionsRepository conclusionsRepository;
    private final MutableLiveData<Boolean> noData = new MutableLiveData<>();

    public HistoryActivityViewModel() {
        conclusionsRepository = new ConclusionsRepository();
    }

    public LiveData<List<ConclusionRecord>> getUserConclusions(String uid) {
        return conclusionsRepository.getConclusionsForUser(uid);
    }

    public MutableLiveData<Boolean> getNoData() {
        return noData;
    }

    public void setNoData(Boolean isData) {
        this.noData.setValue(isData);
    }
}
