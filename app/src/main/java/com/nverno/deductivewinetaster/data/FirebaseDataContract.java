package com.nverno.deductivewinetaster.data;

import com.nverno.deductivewinetaster.R;

import java.util.HashMap;
import java.util.Map;

public interface FirebaseDataContract  {

    String Acidity = "acidity";

    Map<Integer, String> propertiesMap = new HashMap<Integer, String>()
    {{
        put(R.id.radio_group_palate_acid, "acidity");
    }};

}
