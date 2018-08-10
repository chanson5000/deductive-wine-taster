package com.nverno.deductivewinetaster.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;

import com.nverno.deductivewinetaster.R;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;

public class RedNoseFragment extends Fragment implements RedWineContract {

    private RedDeductionFormActivity mFragmentActivity;
    private SharedPreferences mSharedPreferences;

    @BindView(R.id.check_faulty_tca)
    CheckBox mCheckFaultyTca;
    @BindView(R.id.check_faulty_hydrogen_sulfide)
    CheckBox mCheckFaultyHydrogenSulfide;
    @BindView(R.id.check_faulty_volatile_acidity)
    CheckBox mCheckFaultyVolatileAcidity;
    @BindView(R.id.check_faulty_ethyl_acetate)
    CheckBox mCheckFaultyEthylAcetate;
    @BindView(R.id.check_faulty_brett)
    CheckBox mCheckFaultyBrett;
    @BindView(R.id.check_faulty_oxidization)
    CheckBox mCheckFaultyOxidization;
    @BindView(R.id.check_faulty_other)
    CheckBox mCheckFaultyOther;
    @BindView(R.id.radio_group_nose_intensity)
    RadioGroup mRadioGroupNoseIntensity;
    @BindView(R.id.radio_group_nose_age_assessment)
    RadioGroup mRadioGroupNoseAgeAssessment;
    @BindView(R.id.check_nose_fruit_red)
    CheckBox mCheckNoseFruitRed;
    @BindView(R.id.check_nose_fruit_black)
    CheckBox mCheckNoseFruitBlack;
    @BindView(R.id.check_nose_fruit_blue)
    CheckBox mCheckNoseFruitBlue;
    @BindView(R.id.check_nose_fruit_character_ripe)
    CheckBox mCheckNoseFruitCharacterRipe;
    @BindView(R.id.check_nose_fruit_character_fresh)
    CheckBox mCheckNoseFruitCharacterFresh;
    @BindView(R.id.check_nose_fruit_character_tart)
    CheckBox mCheckNoseFruitCharacterTart;
    @BindView(R.id.check_nose_fruit_character_baked)
    CheckBox mCheckNoseFruitCharacterBaked;
    @BindView(R.id.check_nose_fruit_character_stewed)
    CheckBox mCheckNoseFruitCharacterStewed;
    @BindView(R.id.check_nose_fruit_character_dried)
    CheckBox mCheckNoseFruitCharacterDried;
    @BindView(R.id.check_nose_fruit_character_desiccated)
    CheckBox mCheckNoseFruitCharacterDesiccated;
    @BindView(R.id.check_nose_fruit_character_bruised)
    CheckBox mCheckNoseFruitCharacterBruised;
    @BindView(R.id.check_nose_fruit_character_jammy)
    CheckBox mCheckNoseFruitCharacterJammy;
    @BindView(R.id.check_nose_non_fruit_floral)
    CheckBox mCheckNoseNonFruitFloral;
    @BindView(R.id.check_nose_non_fruit_vegetal)
    CheckBox mCheckNoseNonFruitVegetal;
    @BindView(R.id.check_nose_non_fruit_herbal)
    CheckBox mCheckNoseNonFruitHerbal;
    @BindView(R.id.check_nose_non_fruit_spice)
    CheckBox mCheckNoseNonFruitSpice;
    @BindView(R.id.check_nose_non_fruit_animal)
    CheckBox mCheckNoseNonFruitAnimal;
    @BindView(R.id.check_nose_non_fruit_barn)
    CheckBox mCheckNoseNonFruitBarn;
    @BindView(R.id.check_nose_non_fruit_petrol)
    CheckBox mCheckNoseNonFruitPetrol;
    @BindView(R.id.check_nose_non_fruit_fermentation)
    CheckBox mCheckNoseNonFruitFermentation;
    @BindView(R.id.check_nose_earth_forest_floor)
    CheckBox mCheckNoseEarthForestFloor;
    @BindView(R.id.check_nose_earth_compost)
    CheckBox mCheckNoseEarthCompost;
    @BindView(R.id.check_nose_earth_mushrooms)
    CheckBox mCheckNoseEarthMushrooms;
    @BindView(R.id.check_nose_earth_potting_soil)
    CheckBox mCheckNoseEarthPottingSoil;
    @BindView(R.id.check_nose_mineral_mineral)
    CheckBox mCheckNoseMineralMineral;
    @BindView(R.id.check_nose_mineral_wet_stone)
    CheckBox mCheckNoseMineralWetStone;
    @BindView(R.id.check_nose_mineral_limestone)
    CheckBox mCheckNoseMineralLimestone;
    @BindView(R.id.check_nose_mineral_chalk)
    CheckBox mCheckNoseMineralChalk;
    @BindView(R.id.check_nose_mineral_slate)
    CheckBox mCheckNoseMineralSlate;
    @BindView(R.id.check_nose_mineral_flint)
    CheckBox mCheckNoseMineralFlint;
    @BindView(R.id.switch_nose_wood)
    Switch mSwitchNoseWood;
    @BindView(R.id.radio_group_wood_old_vs_new)
    RadioGroup mRadioGroupNoseWoodOldVsNew;
    @BindView(R.id.radio_group_wood_large_vs_small)
    RadioGroup mRadioGroupNoseWoodLargeVsSmall;
    @BindView(R.id.radio_group_wood_french_vs_american)
    RadioGroup mRadioGroupNoseWoodFrenchVsAmerican;
    @BindViews({R.id.radio_nose_wood_old, R.id.radio_nose_wood_new,
            R.id.radio_nose_wood_large, R.id.radio_nose_wood_small,
            R.id.radio_nose_wood_french, R.id.radio_nose_wood_american})
    List<RadioButton> mRadioGroupsNoseWood;

    static final ButterKnife.Action<RadioButton> WOOD_ENABLE = new ButterKnife.Action<RadioButton>() {
        @Override
        public void apply(@NonNull RadioButton view, int index) {
            view.setEnabled(true);
        }
    };

    static final ButterKnife.Action<RadioButton> WOOD_DISABLE = new ButterKnife.Action<RadioButton>() {
        @Override
        public void apply(@NonNull RadioButton view, int index) {
            view.setEnabled(false);
        }
    };

    public RedNoseFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mFragmentActivity = (RedDeductionFormActivity) getActivity();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSharedPreferences = mFragmentActivity.getPreferences(Context.MODE_PRIVATE);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_nose_red,
                container, false);

        ButterKnife.bind(this, rootView);

        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        loadAllSelectionStates();
        setSelectionListeners();
    }

    public void resetView() {
        clearAllSelectionStates();
    }

    private int getRadioGroupState(String key) {
        return mSharedPreferences.getInt(key, NOT_CHECKED);
    }

    private void saveRadioGroupState(String key, int state) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putInt(key, state);
        editor.apply();
    }

    private boolean getCheckBoxState(String key) {
        return mSharedPreferences.getInt(key, NOT_CHECKED) == 1;
    }

    private void saveCheckBoxState(String key, boolean checked) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        if (checked) {
            editor.putInt(key, CHECKED);
        } else {
            editor.putInt(key, NOT_CHECKED);
        }
        editor.apply();
    }

    private void loadAllSelectionStates() {
        mCheckFaultyTca.setChecked(getCheckBoxState(FAULTY_TCA));
        mCheckFaultyHydrogenSulfide.setChecked(getCheckBoxState(FAULTY_HYDROGEN_SULFIDE));
        mCheckFaultyVolatileAcidity.setChecked(getCheckBoxState(FAULTY_VOLATILE_ACIDITY));
        mCheckFaultyEthylAcetate.setChecked(getCheckBoxState(FAULTY_ETHYL_ACETATE));
        mCheckFaultyBrett.setChecked(getCheckBoxState(FAULTY_BRETT));
        mCheckFaultyOxidization.setChecked(getCheckBoxState(FAULTY_OXIDIZATION));
        mCheckFaultyOther.setChecked(getCheckBoxState(FAULTY_OTHER));
        mRadioGroupNoseIntensity.check(getRadioGroupState(NOSE_INTENSITY));
        mRadioGroupNoseAgeAssessment.check(getRadioGroupState(NOSE_AGE_ASSESSMENT));
        mCheckNoseFruitRed.setChecked(getCheckBoxState(NOSE_FRUIT_RED));
        mCheckNoseFruitBlack.setChecked(getCheckBoxState(NOSE_FRUIT_BLACK));
        mCheckNoseFruitBlue.setChecked(getCheckBoxState(NOSE_FRUIT_BLACK));
        mCheckNoseFruitCharacterRipe.setChecked(getCheckBoxState(NOSE_FRUIT_CHARACTER_RIPE));
        mCheckNoseFruitCharacterFresh.setChecked(getCheckBoxState(NOSE_FRUIT_CHARACTER_FRESH));
        mCheckNoseFruitCharacterTart.setChecked(getCheckBoxState(NOSE_FRUIT_CHARACTER_TART));
        mCheckNoseFruitCharacterBaked.setChecked(getCheckBoxState(NOSE_FRUIT_CHARACTER_BAKED));
        mCheckNoseFruitCharacterStewed.setChecked(getCheckBoxState(NOSE_FRUIT_CHARACTER_STEWED));
        mCheckNoseFruitCharacterDried.setChecked(getCheckBoxState(NOSE_FRUIT_CHARACTER_DRIED));
        mCheckNoseFruitCharacterDesiccated.setChecked(getCheckBoxState(NOSE_FRUIT_CHARACTER_DESICATTED));
        mCheckNoseFruitCharacterBruised.setChecked(getCheckBoxState(NOSE_FRUIT_CHARACTER_BRUISED));
        mCheckNoseFruitCharacterJammy.setChecked(getCheckBoxState(NOSE_FRUIT_CHARACTER_JAMMY));
        mCheckNoseNonFruitFloral.setChecked(getCheckBoxState(NOSE_NON_FRUIT_FLORAL));
        mCheckNoseNonFruitVegetal.setChecked(getCheckBoxState(NOSE_NON_FRUIT_VEGETAL));
        mCheckNoseNonFruitHerbal.setChecked(getCheckBoxState(NOSE_NON_FRUIT_HERBAL));
        mCheckNoseNonFruitSpice.setChecked(getCheckBoxState(NOSE_NON_FRUIT_SPICE));
        mCheckNoseNonFruitAnimal.setChecked(getCheckBoxState(NOSE_NON_FRUIT_ANIMAL));
        mCheckNoseNonFruitBarn.setChecked(getCheckBoxState(NOSE_NON_FRUIT_BARN));
        mCheckNoseNonFruitPetrol.setChecked(getCheckBoxState(NOSE_NON_FRUIT_PETROL));
        mCheckNoseNonFruitFermentation.setChecked(getCheckBoxState(NOSE_NON_FRUIT_FERMENTATION));
        mCheckNoseEarthForestFloor.setChecked(getCheckBoxState(NOSE_EARTH_FOREST_FLOOR));
        mCheckNoseEarthCompost.setChecked(getCheckBoxState(NOSE_EARTH_COMPOST));
        mCheckNoseEarthMushrooms.setChecked(getCheckBoxState(NOSE_EARTH_MUSHROOMS));
        mCheckNoseEarthPottingSoil.setChecked(getCheckBoxState(NOSE_EARTH_POTTING_SOIL));
        mCheckNoseMineralMineral.setChecked(getCheckBoxState(NOSE_MINERAL_MINERAL));
        mCheckNoseMineralWetStone.setChecked(getCheckBoxState(NOSE_MINERAL_WET_STONE));
        mCheckNoseMineralLimestone.setChecked(getCheckBoxState(NOSE_MINERAL_LIMESTONE));
        mCheckNoseMineralChalk.setChecked(getCheckBoxState(NOSE_MINERAL_CHALK));
        mCheckNoseMineralSlate.setChecked(getCheckBoxState(NOSE_MINERAL_SLATE));
        mCheckNoseMineralFlint.setChecked(getCheckBoxState(NOSE_MINERAL_FLINT));

        boolean switchNoseWoodState = getCheckBoxState(NOSE_WOOD);
        mSwitchNoseWood.setChecked(getCheckBoxState(NOSE_WOOD));
        if (switchNoseWoodState) {
            ButterKnife.apply(mRadioGroupsNoseWood, WOOD_ENABLE);
        } else {
            ButterKnife.apply(mRadioGroupsNoseWood, WOOD_DISABLE);
        }

        mRadioGroupNoseWoodOldVsNew.check(getRadioGroupState(NOSE_WOOD_OLD_VS_NEW));
        mRadioGroupNoseWoodLargeVsSmall.check(getRadioGroupState(NOSE_WOOD_LARGE_VS_SMALL));
        mRadioGroupNoseWoodFrenchVsAmerican.check(getRadioGroupState(NOSE_WOOD_FRENCH_VS_AMERICAN));
    }

    private void clearAllSelectionStates() {
        mCheckFaultyTca.setChecked(false);
        mCheckFaultyHydrogenSulfide.setChecked(false);
        mCheckFaultyVolatileAcidity.setChecked(false);
        mCheckFaultyEthylAcetate.setChecked(false);
        mCheckFaultyBrett.setChecked(false);
        mCheckFaultyOxidization.setChecked(false);
        mCheckFaultyOther.setChecked(false);
        mRadioGroupNoseIntensity.clearCheck();
        mRadioGroupNoseAgeAssessment.clearCheck();
        mCheckNoseFruitRed.setChecked(false);
        mCheckNoseFruitBlack.setChecked(false);
        mCheckNoseFruitBlue.setChecked(false);
        mCheckNoseFruitCharacterRipe.setChecked(false);
        mCheckNoseFruitCharacterFresh.setChecked(false);
        mCheckNoseFruitCharacterTart.setChecked(false);
        mCheckNoseFruitCharacterBaked.setChecked(false);
        mCheckNoseFruitCharacterStewed.setChecked(false);
        mCheckNoseFruitCharacterDried.setChecked(false);
        mCheckNoseFruitCharacterDesiccated.setChecked(false);
        mCheckNoseFruitCharacterBruised.setChecked(false);
        mCheckNoseFruitCharacterJammy.setChecked(false);
        mCheckNoseNonFruitFloral.setChecked(false);
        mCheckNoseNonFruitVegetal.setChecked(false);
        mCheckNoseNonFruitHerbal.setChecked(false);
        mCheckNoseNonFruitSpice.setChecked(false);
        mCheckNoseNonFruitAnimal.setChecked(false);
        mCheckNoseNonFruitBarn.setChecked(false);
        mCheckNoseNonFruitPetrol.setChecked(false);
        mCheckNoseNonFruitFermentation.setChecked(false);
        mCheckNoseEarthForestFloor.setChecked(false);
        mCheckNoseEarthCompost.setChecked(false);
        mCheckNoseEarthMushrooms.setChecked(false);
        mCheckNoseEarthPottingSoil.setChecked(false);
        mCheckNoseMineralMineral.setChecked(false);
        mCheckNoseMineralWetStone.setChecked(false);
        mCheckNoseMineralLimestone.setChecked(false);
        mCheckNoseMineralChalk.setChecked(false);
        mCheckNoseMineralSlate.setChecked(false);
        mCheckNoseMineralFlint.setChecked(false);
        mSwitchNoseWood.setChecked(false);
        ButterKnife.apply(mRadioGroupsNoseWood, WOOD_DISABLE);
        mRadioGroupNoseWoodOldVsNew.clearCheck();
        mRadioGroupNoseWoodLargeVsSmall.clearCheck();
        mRadioGroupNoseWoodFrenchVsAmerican.clearCheck();
    }

    private void setSelectionListeners() {
        mCheckFaultyTca.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                saveCheckBoxState(FAULTY_TCA, isChecked);
            }
        });

        mCheckFaultyHydrogenSulfide.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                saveCheckBoxState(FAULTY_HYDROGEN_SULFIDE, isChecked);
            }
        });

        mCheckFaultyVolatileAcidity.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                saveCheckBoxState(FAULTY_VOLATILE_ACIDITY, isChecked);
            }
        });

        mCheckFaultyEthylAcetate.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                saveCheckBoxState(FAULTY_ETHYL_ACETATE, isChecked);
            }
        });

        mCheckFaultyBrett.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                saveCheckBoxState(FAULTY_BRETT, isChecked);
            }
        });

        mCheckFaultyOxidization.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                saveCheckBoxState(FAULTY_OXIDIZATION, isChecked);
            }
        });

        mCheckFaultyOther.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                saveCheckBoxState(FAULTY_OTHER, isChecked);
            }
        });

        mRadioGroupNoseIntensity.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                saveRadioGroupState(NOSE_INTENSITY, checkedId);
            }
        });

        mRadioGroupNoseAgeAssessment.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                saveRadioGroupState(NOSE_AGE_ASSESSMENT, checkedId);
            }
        });

        mCheckNoseFruitRed.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                saveCheckBoxState(NOSE_FRUIT_RED, isChecked);
            }
        });

        mCheckNoseFruitBlack.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                saveCheckBoxState(NOSE_FRUIT_BLACK, isChecked);
            }
        });

        mCheckNoseFruitBlue.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                saveCheckBoxState(NOSE_FRUIT_BLUE, isChecked);
            }
        });

        mCheckNoseFruitCharacterRipe.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                saveCheckBoxState(NOSE_FRUIT_CHARACTER_RIPE, isChecked);
            }
        });

        mCheckNoseFruitCharacterFresh.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                saveCheckBoxState(NOSE_FRUIT_CHARACTER_FRESH, isChecked);
            }
        });

        mCheckNoseFruitCharacterTart.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                saveCheckBoxState(NOSE_FRUIT_CHARACTER_TART, isChecked);
            }
        });

        mCheckNoseFruitCharacterBaked.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                saveCheckBoxState(NOSE_FRUIT_CHARACTER_BAKED, isChecked);
            }
        });

        mCheckNoseFruitCharacterStewed.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                saveCheckBoxState(NOSE_FRUIT_CHARACTER_STEWED, isChecked);
            }
        });

        mCheckNoseFruitCharacterDried.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                saveCheckBoxState(NOSE_FRUIT_CHARACTER_DRIED, isChecked);
            }
        });

        mCheckNoseFruitCharacterDesiccated.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                saveCheckBoxState(NOSE_FRUIT_CHARACTER_DESICATTED, isChecked);
            }
        });

        mCheckNoseFruitCharacterBruised.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                saveCheckBoxState(NOSE_FRUIT_CHARACTER_BRUISED, isChecked);
            }
        });

        mCheckNoseFruitCharacterJammy.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                saveCheckBoxState(NOSE_FRUIT_CHARACTER_JAMMY, isChecked);
            }
        });

        mCheckNoseNonFruitFloral.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                saveCheckBoxState(NOSE_NON_FRUIT_FLORAL, isChecked);
            }
        });

        mCheckNoseNonFruitVegetal.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                saveCheckBoxState(NOSE_NON_FRUIT_VEGETAL, isChecked);
            }
        });

        mCheckNoseNonFruitHerbal.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                saveCheckBoxState(NOSE_NON_FRUIT_HERBAL, isChecked);
            }
        });

        mCheckNoseNonFruitSpice.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                saveCheckBoxState(NOSE_NON_FRUIT_SPICE, isChecked);
            }
        });

        mCheckNoseNonFruitAnimal.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                saveCheckBoxState(NOSE_NON_FRUIT_ANIMAL, isChecked);
            }
        });

        mCheckNoseNonFruitBarn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                saveCheckBoxState(NOSE_NON_FRUIT_BARN, isChecked);
            }
        });

        mCheckNoseNonFruitPetrol.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                saveCheckBoxState(NOSE_NON_FRUIT_PETROL, isChecked);
            }
        });

        mCheckNoseNonFruitFermentation.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                saveCheckBoxState(NOSE_NON_FRUIT_FERMENTATION, isChecked);
            }
        });

        mCheckNoseEarthForestFloor.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                saveCheckBoxState(NOSE_EARTH_FOREST_FLOOR, isChecked);
            }
        });

        mCheckNoseEarthCompost.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                saveCheckBoxState(NOSE_EARTH_COMPOST, isChecked);
            }
        });

        mCheckNoseEarthMushrooms.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                saveCheckBoxState(NOSE_EARTH_MUSHROOMS, isChecked);
            }
        });

        mCheckNoseEarthPottingSoil.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                saveCheckBoxState(NOSE_EARTH_POTTING_SOIL, isChecked);
            }
        });

        mCheckNoseMineralMineral.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                saveCheckBoxState(NOSE_MINERAL_MINERAL, isChecked);
            }
        });

        mCheckNoseMineralWetStone.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                saveCheckBoxState(NOSE_MINERAL_WET_STONE, isChecked);
            }
        });

        mCheckNoseMineralLimestone.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                saveCheckBoxState(NOSE_MINERAL_LIMESTONE, isChecked);
            }
        });

        mCheckNoseMineralChalk.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                saveCheckBoxState(NOSE_MINERAL_CHALK, isChecked);
            }
        });

        mCheckNoseMineralSlate.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                saveCheckBoxState(NOSE_MINERAL_SLATE, isChecked);
            }
        });

        mCheckNoseMineralFlint.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                saveCheckBoxState(NOSE_MINERAL_FLINT, isChecked);
            }
        });

        mSwitchNoseWood.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                saveCheckBoxState(NOSE_WOOD, isChecked);
                if (isChecked) {
                    ButterKnife.apply(mRadioGroupsNoseWood, WOOD_ENABLE);
                } else {
                    ButterKnife.apply(mRadioGroupsNoseWood, WOOD_DISABLE);
                }
            }
        });

        mRadioGroupNoseWoodOldVsNew.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                saveRadioGroupState(NOSE_WOOD_OLD_VS_NEW, checkedId);
            }
        });

        mRadioGroupNoseWoodLargeVsSmall.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                saveRadioGroupState(NOSE_WOOD_LARGE_VS_SMALL, checkedId);
            }
        });

        mRadioGroupNoseWoodFrenchVsAmerican.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                saveRadioGroupState(NOSE_WOOD_FRENCH_VS_AMERICAN, checkedId);
            }
        });
    }
}
