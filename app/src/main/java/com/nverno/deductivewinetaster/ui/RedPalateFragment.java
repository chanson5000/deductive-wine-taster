package com.nverno.deductivewinetaster.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.Switch;

import com.nverno.deductivewinetaster.R;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;

public class RedPalateFragment extends Fragment implements RedWineContract {

    private FragmentActivity mFragmentActivity;
    private SharedPreferences mSharedPreferences;

    @BindView(R.id.scrollView_palate_red)
    ScrollView mScrollViewPalateRed;
    @BindView(R.id.radio_group_palate_sweetness)
    RadioGroup mRadioGroupPalateSweetness;
    @BindView(R.id.check_palate_fruit_red)
    CheckBox mCheckPalateFruitRed;
    @BindView(R.id.check_palate_fruit_black)
    CheckBox mCheckPalateFruitBlack;
    @BindView(R.id.check_palate_fruit_blue)
    CheckBox mCheckPalateFruitBlue;
    @BindView(R.id.check_palate_fruit_character_ripe)
    CheckBox mCheckPalateFruitCharacterRipe;
    @BindView(R.id.check_palate_fruit_character_fresh)
    CheckBox mCheckPalateFruitCharacterFresh;
    @BindView(R.id.check_palate_fruit_character_tart)
    CheckBox mCheckPalateFruitCharacterTart;
    @BindView(R.id.check_palate_fruit_character_baked)
    CheckBox mCheckPalateFruitCharacterBaked;
    @BindView(R.id.check_palate_fruit_character_stewed)
    CheckBox mCheckPalateFruitCharacterStewed;
    @BindView(R.id.check_palate_fruit_character_dried)
    CheckBox mCheckPalateFruitCharacterDried;
    @BindView(R.id.check_palate_fruit_character_desiccated)
    CheckBox mCheckPalateFruitCharacterDesiccated;
    @BindView(R.id.check_palate_fruit_character_bruised)
    CheckBox mCheckPalateFruitCharacterBruised;
    @BindView(R.id.check_palate_fruit_character_jammy)
    CheckBox mCheckPalateFruitCharacterJammy;
    @BindView(R.id.check_palate_non_fruit_floral)
    CheckBox mCheckPalateNonFruitFloral;
    @BindView(R.id.check_palate_non_fruit_vegetal)
    CheckBox mCheckPalateNonFruitVegetal;
    @BindView(R.id.check_palate_non_fruit_herbal)
    CheckBox mCheckPalateNonFruitHerbal;
    @BindView(R.id.check_palate_non_fruit_spice)
    CheckBox mCheckPalateNonFruitSpice;
    @BindView(R.id.check_palate_non_fruit_animal)
    CheckBox mCheckPalateNonFruitAnimal;
    @BindView(R.id.check_palate_non_fruit_barn)
    CheckBox mCheckPalateNonFruitBarn;
    @BindView(R.id.check_palate_non_fruit_petrol)
    CheckBox mCheckPalateNonFruitPetrol;
    @BindView(R.id.check_palate_non_fruit_fermentation)
    CheckBox mCheckPalateNonFruitFermentation;
    @BindView(R.id.check_palate_earth_forest_floor)
    CheckBox mCheckPalateEarthForestFloor;
    @BindView(R.id.check_palate_earth_compost)
    CheckBox mCheckPalateEarthCompost;
    @BindView(R.id.check_palate_earth_mushrooms)
    CheckBox mCheckPalateEarthMushrooms;
    @BindView(R.id.check_palate_earth_potting_soil)
    CheckBox mCheckPalateEarthPottingSoil;
    @BindView(R.id.check_palate_mineral_mineral)
    CheckBox mCheckPalateMineralMineral;
    @BindView(R.id.check_palate_mineral_wet_stone)
    CheckBox mCheckPalateMineralWetStone;
    @BindView(R.id.check_palate_mineral_limestone)
    CheckBox mCheckPalateMineralLimestone;
    @BindView(R.id.check_palate_mineral_chalk)
    CheckBox mCheckPalateMineralChalk;
    @BindView(R.id.check_palate_mineral_slate)
    CheckBox mCheckPalateMineralSlate;
    @BindView(R.id.check_palate_mineral_flint)
    CheckBox mCheckPalateMineralFlint;
    @BindView(R.id.switch_palate_wood)
    Switch mSwitchPalateWood;
    @BindView(R.id.radio_group_wood_old_vs_new)
    RadioGroup mRadioGroupPalateWoodOldVsNew;
    @BindView(R.id.radio_group_wood_large_vs_small)
    RadioGroup mRadioGroupPalateWoodLargeVsSmall;
    @BindView(R.id.radio_group_wood_french_vs_american)
    RadioGroup mRadioGroupPalateWoodFrenchVsAmerican;
    @BindViews({R.id.radio_palate_wood_old, R.id.radio_palate_wood_new,
            R.id.radio_palate_wood_large, R.id.radio_palate_wood_small,
            R.id.radio_palate_wood_french, R.id.radio_palate_wood_american})
    List<RadioButton> mRadioGroupsPalateWood;

    static final ButterKnife.Action<RadioButton> WOOD_ENABLE = (view, index) -> view.setEnabled(true);

    static final ButterKnife.Action<RadioButton> WOOD_DISABLE = (view, index) -> view.setEnabled(false);

    public RedPalateFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mFragmentActivity = getActivity();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSharedPreferences = mFragmentActivity
                .getSharedPreferences(RED_WINE_FORM_PREFERENCES, Context.MODE_PRIVATE);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_palate_red,
                container, false);

        ButterKnife.bind(this, rootView);

//        setSelectionListeners();

        return rootView;
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        setUiState();
    }

    private int getRadioGroupState(int key) {
        return mSharedPreferences.getInt(Integer.toString(key), NOT_CHECKED);
    }

    private void saveRadioGroupState(int key, int state) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putInt(Integer.toString(key), state);
        editor.apply();
    }

    private boolean getCheckBoxState(int key) {
        return mSharedPreferences.getInt(Integer.toString(key), NOT_CHECKED) == 1;
    }

    // These next two methods give us options for saving the
    // CheckBox state based on boolean or int.
//    private void saveCheckBoxState(int key, boolean checked) {
//        SharedPreferences.Editor editor = mSharedPreferences.edit();
//        if (checked) {
//            editor.putInt(key, CHECKED);
//        } else {
//            editor.putInt(key, NOT_CHECKED);
//        }
//        editor.apply();
//    }

//    private void saveCheckBoxState(int key, int checkedInt) {
//        SharedPreferences.Editor editor = mSharedPreferences.edit();
//        editor.putInt(key, checkedInt);
//        editor.apply();
//    }

    private void setUiState() {
        mRadioGroupPalateSweetness.check(getRadioGroupState(PALATE_SWEETNESS));
        mCheckPalateFruitRed.setChecked(getCheckBoxState(PALATE_FRUIT_RED));
        mCheckPalateFruitBlack.setChecked(getCheckBoxState(PALATE_FRUIT_BLACK));
        mCheckPalateFruitBlue.setChecked(getCheckBoxState(PALATE_FRUIT_BLACK));
        mCheckPalateFruitCharacterRipe.setChecked(getCheckBoxState(PALATE_FRUIT_CHARACTER_RIPE));
        mCheckPalateFruitCharacterFresh.setChecked(getCheckBoxState(PALATE_FRUIT_CHARACTER_FRESH));
        mCheckPalateFruitCharacterTart.setChecked(getCheckBoxState(PALATE_FRUIT_CHARACTER_TART));
        mCheckPalateFruitCharacterBaked.setChecked(getCheckBoxState(PALATE_FRUIT_CHARACTER_BAKED));
        mCheckPalateFruitCharacterStewed.setChecked(getCheckBoxState(PALATE_FRUIT_CHARACTER_STEWED));
        mCheckPalateFruitCharacterDried.setChecked(getCheckBoxState(PALATE_FRUIT_CHARACTER_DRIED));
        mCheckPalateFruitCharacterDesiccated.setChecked(getCheckBoxState(PALATE_FRUIT_CHARACTER_DESICATTED));
        mCheckPalateFruitCharacterBruised.setChecked(getCheckBoxState(PALATE_FRUIT_CHARACTER_BRUISED));
        mCheckPalateFruitCharacterJammy.setChecked(getCheckBoxState(PALATE_FRUIT_CHARACTER_JAMMY));
        mCheckPalateNonFruitFloral.setChecked(getCheckBoxState(PALATE_NON_FRUIT_FLORAL));
        mCheckPalateNonFruitVegetal.setChecked(getCheckBoxState(PALATE_NON_FRUIT_VEGETAL));
        mCheckPalateNonFruitHerbal.setChecked(getCheckBoxState(PALATE_NON_FRUIT_HERBAL));
        mCheckPalateNonFruitSpice.setChecked(getCheckBoxState(PALATE_NON_FRUIT_SPICE));
        mCheckPalateNonFruitAnimal.setChecked(getCheckBoxState(PALATE_NON_FRUIT_ANIMAL));
        mCheckPalateNonFruitBarn.setChecked(getCheckBoxState(PALATE_NON_FRUIT_BARN));
        mCheckPalateNonFruitPetrol.setChecked(getCheckBoxState(PALATE_NON_FRUIT_PETROL));
        mCheckPalateNonFruitFermentation.setChecked(getCheckBoxState(PALATE_NON_FRUIT_FERMENTATION));
        mCheckPalateEarthForestFloor.setChecked(getCheckBoxState(PALATE_EARTH_FOREST_FLOOR));
        mCheckPalateEarthCompost.setChecked(getCheckBoxState(PALATE_EARTH_COMPOST));
        mCheckPalateEarthMushrooms.setChecked(getCheckBoxState(PALATE_EARTH_MUSHROOMS));
        mCheckPalateEarthPottingSoil.setChecked(getCheckBoxState(PALATE_EARTH_POTTING_SOIL));
        mCheckPalateMineralMineral.setChecked(getCheckBoxState(PALATE_MINERAL_MINERAL));
        mCheckPalateMineralWetStone.setChecked(getCheckBoxState(PALATE_MINERAL_WET_STONE));
        mCheckPalateMineralLimestone.setChecked(getCheckBoxState(PALATE_MINERAL_LIMESTONE));
        mCheckPalateMineralChalk.setChecked(getCheckBoxState(PALATE_MINERAL_CHALK));
        mCheckPalateMineralSlate.setChecked(getCheckBoxState(PALATE_MINERAL_SLATE));
        mCheckPalateMineralFlint.setChecked(getCheckBoxState(PALATE_MINERAL_FLINT));

        boolean switchPalateWoodState = getCheckBoxState(PALATE_WOOD);
        mSwitchPalateWood.setChecked(getCheckBoxState(PALATE_WOOD));
        if (switchPalateWoodState) {
            ButterKnife.apply(mRadioGroupsPalateWood, WOOD_ENABLE);
        } else {
            ButterKnife.apply(mRadioGroupsPalateWood, WOOD_DISABLE);
        }

        mRadioGroupPalateWoodOldVsNew.check(getRadioGroupState(PALATE_WOOD_OLD_VS_NEW));
        mRadioGroupPalateWoodLargeVsSmall.check(getRadioGroupState(PALATE_WOOD_LARGE_VS_SMALL));
        mRadioGroupPalateWoodFrenchVsAmerican.check(getRadioGroupState(PALATE_WOOD_FRENCH_VS_AMERICAN));
    }

    private void clearAllSelectionStates() {
        mRadioGroupPalateSweetness.clearCheck();
        mCheckPalateFruitRed.setChecked(false);
        mCheckPalateFruitBlack.setChecked(false);
        mCheckPalateFruitBlue.setChecked(false);
        mCheckPalateFruitCharacterRipe.setChecked(false);
        mCheckPalateFruitCharacterFresh.setChecked(false);
        mCheckPalateFruitCharacterTart.setChecked(false);
        mCheckPalateFruitCharacterBaked.setChecked(false);
        mCheckPalateFruitCharacterStewed.setChecked(false);
        mCheckPalateFruitCharacterDried.setChecked(false);
        mCheckPalateFruitCharacterDesiccated.setChecked(false);
        mCheckPalateFruitCharacterBruised.setChecked(false);
        mCheckPalateFruitCharacterJammy.setChecked(false);
        mCheckPalateNonFruitFloral.setChecked(false);
        mCheckPalateNonFruitVegetal.setChecked(false);
        mCheckPalateNonFruitHerbal.setChecked(false);
        mCheckPalateNonFruitSpice.setChecked(false);
        mCheckPalateNonFruitAnimal.setChecked(false);
        mCheckPalateNonFruitBarn.setChecked(false);
        mCheckPalateNonFruitPetrol.setChecked(false);
        mCheckPalateNonFruitFermentation.setChecked(false);
        mCheckPalateEarthForestFloor.setChecked(false);
        mCheckPalateEarthCompost.setChecked(false);
        mCheckPalateEarthMushrooms.setChecked(false);
        mCheckPalateEarthPottingSoil.setChecked(false);
        mCheckPalateMineralMineral.setChecked(false);
        mCheckPalateMineralWetStone.setChecked(false);
        mCheckPalateMineralLimestone.setChecked(false);
        mCheckPalateMineralChalk.setChecked(false);
        mCheckPalateMineralSlate.setChecked(false);
        mCheckPalateMineralFlint.setChecked(false);
        mSwitchPalateWood.setChecked(false);
        ButterKnife.apply(mRadioGroupsPalateWood, WOOD_DISABLE);
        mRadioGroupPalateWoodOldVsNew.clearCheck();
        mRadioGroupPalateWoodLargeVsSmall.clearCheck();
        mRadioGroupPalateWoodFrenchVsAmerican.clearCheck();
    }

//    private void clearAllSharedPreferenceStates() {
//        saveRadioGroupState(PALATE_SWEETNESS, NONE_SELECTED);
//        saveCheckBoxState(PALATE_FRUIT_RED, NOT_CHECKED);
//        saveCheckBoxState(PALATE_FRUIT_BLACK, NOT_CHECKED);
//        saveCheckBoxState(PALATE_FRUIT_BLUE, NOT_CHECKED);
//        saveCheckBoxState(PALATE_FRUIT_CHARACTER_RIPE, NOT_CHECKED);
//        saveCheckBoxState(PALATE_FRUIT_CHARACTER_FRESH, NOT_CHECKED);
//        saveCheckBoxState(PALATE_FRUIT_CHARACTER_TART, NOT_CHECKED);
//        saveCheckBoxState(PALATE_FRUIT_CHARACTER_BAKED, NOT_CHECKED);
//        saveCheckBoxState(PALATE_FRUIT_CHARACTER_STEWED, NOT_CHECKED);
//        saveCheckBoxState(PALATE_FRUIT_CHARACTER_DRIED, NOT_CHECKED);
//        saveCheckBoxState(PALATE_FRUIT_CHARACTER_DESICATTED, NOT_CHECKED);
//        saveCheckBoxState(PALATE_FRUIT_CHARACTER_BRUISED, NOT_CHECKED);
//        saveCheckBoxState(PALATE_FRUIT_CHARACTER_JAMMY, NOT_CHECKED);
//        saveCheckBoxState(PALATE_NON_FRUIT_FLORAL, NOT_CHECKED);
//        saveCheckBoxState(PALATE_NON_FRUIT_VEGETAL, NOT_CHECKED);
//        saveCheckBoxState(PALATE_NON_FRUIT_HERBAL, NOT_CHECKED);
//        saveCheckBoxState(PALATE_NON_FRUIT_SPICE, NOT_CHECKED);
//        saveCheckBoxState(PALATE_NON_FRUIT_ANIMAL, NOT_CHECKED);
//        saveCheckBoxState(PALATE_NON_FRUIT_BARN, NOT_CHECKED);
//        saveCheckBoxState(PALATE_NON_FRUIT_PETROL, NOT_CHECKED);
//        saveCheckBoxState(PALATE_NON_FRUIT_FERMENTATION, NOT_CHECKED);
//        saveCheckBoxState(PALATE_EARTH_FOREST_FLOOR, NOT_CHECKED);
//        saveCheckBoxState(PALATE_EARTH_COMPOST, NOT_CHECKED);
//        saveCheckBoxState(PALATE_EARTH_MUSHROOMS, NOT_CHECKED);
//        saveCheckBoxState(PALATE_EARTH_POTTING_SOIL, NOT_CHECKED);
//        saveCheckBoxState(PALATE_MINERAL_MINERAL, NOT_CHECKED);
//        saveCheckBoxState(PALATE_MINERAL_WET_STONE, NOT_CHECKED);
//        saveCheckBoxState(PALATE_MINERAL_LIMESTONE, NOT_CHECKED);
//        saveCheckBoxState(PALATE_MINERAL_CHALK, NOT_CHECKED);
//        saveCheckBoxState(PALATE_MINERAL_SLATE, NOT_CHECKED);
//        saveCheckBoxState(PALATE_MINERAL_FLINT, NOT_CHECKED);
//        saveCheckBoxState(PALATE_WOOD, NOT_CHECKED);
//        saveRadioGroupState(PALATE_WOOD_OLD_VS_NEW, NONE_SELECTED);
//        saveRadioGroupState(PALATE_WOOD_LARGE_VS_SMALL, NONE_SELECTED);
//        saveRadioGroupState(PALATE_WOOD_FRENCH_VS_AMERICAN, NONE_SELECTED);
//    }

//    private void setSelectionListeners() {
//        mRadioGroupPalateSweetness.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                saveRadioGroupState(PALATE_SWEETNESS, checkedId);
//            }
//        });
//
//        mCheckPalateFruitRed.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                saveCheckBoxState(PALATE_FRUIT_RED, isChecked);
//            }
//        });
//
//        mCheckPalateFruitBlack.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                saveCheckBoxState(PALATE_FRUIT_BLACK, isChecked);
//            }
//        });
//
//        mCheckPalateFruitBlue.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                saveCheckBoxState(PALATE_FRUIT_BLUE, isChecked);
//            }
//        });
//
//        mCheckPalateFruitCharacterRipe.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                saveCheckBoxState(PALATE_FRUIT_CHARACTER_RIPE, isChecked);
//            }
//        });
//
//        mCheckPalateFruitCharacterFresh.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                saveCheckBoxState(PALATE_FRUIT_CHARACTER_FRESH, isChecked);
//            }
//        });
//
//        mCheckPalateFruitCharacterTart.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                saveCheckBoxState(PALATE_FRUIT_CHARACTER_TART, isChecked);
//            }
//        });
//
//        mCheckPalateFruitCharacterBaked.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                saveCheckBoxState(PALATE_FRUIT_CHARACTER_BAKED, isChecked);
//            }
//        });
//
//        mCheckPalateFruitCharacterStewed.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                saveCheckBoxState(PALATE_FRUIT_CHARACTER_STEWED, isChecked);
//            }
//        });
//
//        mCheckPalateFruitCharacterDried.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                saveCheckBoxState(PALATE_FRUIT_CHARACTER_DRIED, isChecked);
//            }
//        });
//
//        mCheckPalateFruitCharacterDesiccated.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                saveCheckBoxState(PALATE_FRUIT_CHARACTER_DESICATTED, isChecked);
//            }
//        });
//
//        mCheckPalateFruitCharacterBruised.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                saveCheckBoxState(PALATE_FRUIT_CHARACTER_BRUISED, isChecked);
//            }
//        });
//
//        mCheckPalateFruitCharacterJammy.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                saveCheckBoxState(PALATE_FRUIT_CHARACTER_JAMMY, isChecked);
//            }
//        });
//
//        mCheckPalateNonFruitFloral.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                saveCheckBoxState(PALATE_NON_FRUIT_FLORAL, isChecked);
//            }
//        });
//
//        mCheckPalateNonFruitVegetal.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                saveCheckBoxState(PALATE_NON_FRUIT_VEGETAL, isChecked);
//            }
//        });
//
//        mCheckPalateNonFruitHerbal.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                saveCheckBoxState(PALATE_NON_FRUIT_HERBAL, isChecked);
//            }
//        });
//
//        mCheckPalateNonFruitSpice.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                saveCheckBoxState(PALATE_NON_FRUIT_SPICE, isChecked);
//            }
//        });
//
//        mCheckPalateNonFruitAnimal.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                saveCheckBoxState(PALATE_NON_FRUIT_ANIMAL, isChecked);
//            }
//        });
//
//        mCheckPalateNonFruitBarn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                saveCheckBoxState(PALATE_NON_FRUIT_BARN, isChecked);
//            }
//        });
//
//        mCheckPalateNonFruitPetrol.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                saveCheckBoxState(PALATE_NON_FRUIT_PETROL, isChecked);
//            }
//        });
//
//        mCheckPalateNonFruitFermentation.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                saveCheckBoxState(PALATE_NON_FRUIT_FERMENTATION, isChecked);
//            }
//        });
//
//        mCheckPalateEarthForestFloor.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                saveCheckBoxState(PALATE_EARTH_FOREST_FLOOR, isChecked);
//            }
//        });
//
//        mCheckPalateEarthCompost.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                saveCheckBoxState(PALATE_EARTH_COMPOST, isChecked);
//            }
//        });
//
//        mCheckPalateEarthMushrooms.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                saveCheckBoxState(PALATE_EARTH_MUSHROOMS, isChecked);
//            }
//        });
//
//        mCheckPalateEarthPottingSoil.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                saveCheckBoxState(PALATE_EARTH_POTTING_SOIL, isChecked);
//            }
//        });
//
//        mCheckPalateMineralMineral.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                saveCheckBoxState(PALATE_MINERAL_MINERAL, isChecked);
//            }
//        });
//
//        mCheckPalateMineralWetStone.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                saveCheckBoxState(PALATE_MINERAL_WET_STONE, isChecked);
//            }
//        });
//
//        mCheckPalateMineralLimestone.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                saveCheckBoxState(PALATE_MINERAL_LIMESTONE, isChecked);
//            }
//        });
//
//        mCheckPalateMineralChalk.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                saveCheckBoxState(PALATE_MINERAL_CHALK, isChecked);
//            }
//        });
//
//        mCheckPalateMineralSlate.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                saveCheckBoxState(PALATE_MINERAL_SLATE, isChecked);
//            }
//        });
//
//        mCheckPalateMineralFlint.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                saveCheckBoxState(PALATE_MINERAL_FLINT, isChecked);
//            }
//        });
//
//        mSwitchPalateWood.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                saveCheckBoxState(PALATE_WOOD, isChecked);
//                if (isChecked) {
//                    ButterKnife.apply(mRadioGroupsPalateWood, WOOD_ENABLE);
//                } else {
//                    ButterKnife.apply(mRadioGroupsPalateWood, WOOD_DISABLE);
//                }
//            }
//        });
//
//        mRadioGroupPalateWoodOldVsNew.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                saveRadioGroupState(PALATE_WOOD_OLD_VS_NEW, checkedId);
//            }
//        });
//
//        mRadioGroupPalateWoodLargeVsSmall.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                saveRadioGroupState(PALATE_WOOD_LARGE_VS_SMALL, checkedId);
//            }
//        });
//
//        mRadioGroupPalateWoodFrenchVsAmerican.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                saveRadioGroupState(PALATE_WOOD_FRENCH_VS_AMERICAN, checkedId);
//            }
//        });
//    }
}
