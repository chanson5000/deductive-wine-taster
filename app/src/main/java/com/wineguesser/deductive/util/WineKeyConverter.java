package com.wineguesser.deductive.util;

import android.util.SparseIntArray;

import com.wineguesser.deductive.repository.RepoKeyContract;
import com.wineguesser.deductive.ui.DeductionFormContract;

import java.util.HashMap;
import java.util.Map;

public class WineKeyConverter implements DeductionFormContract, RepoKeyContract {

    private static Map<Integer, String> keyConversionMap = new HashMap<Integer, String>(){{
        put(CHECK_FAULT_TCA, KEY_FAULT_TCA);
        put(CHECK_FAULT_HYDROGEN_SULFIDE, KEY_FAULT_HYDROGEN_SULFIDE);
        put(CHECK_FAULT_ETHYL_ACETATE, KEY_FAULT_ETHYL_ACETATE);
    }};




    public HashMap<String, Integer> convertToDataFormat(SparseIntArray wineProperties) {

        HashMap<String, Integer> convertedData = new HashMap<>();

        for (int i = 0; i < wineProperties.size(); i++) {
            int key = wineProperties.keyAt(i);
            int value = wineProperties.get(key);

            convertedData.put(keyConversionMap.get(key), value);
        }

        return convertedData;
    }
}
