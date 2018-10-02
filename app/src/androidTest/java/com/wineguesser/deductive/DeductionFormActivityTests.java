package com.wineguesser.deductive;

import android.content.Intent;

import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.runner.AndroidJUnit4;

import com.wineguesser.deductive.view.DeductionFormActivity;
import com.wineguesser.deductive.view.DeductionFormContract;

import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.InstrumentationRegistry.getInstrumentation;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static androidx.test.espresso.action.ViewActions.pressImeActionButton;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.swipeLeft;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

// Not having luck with any of these tests passing consistently.
@RunWith(AndroidJUnit4.class)
public class DeductionFormActivityTests implements DeductionFormContract {

    @Rule
    public final IntentsTestRule<DeductionFormActivity> activityTestRule =
            new IntentsTestRule<>(DeductionFormActivity.class, true, false);

    // These tests seem to be really flaky in general, thus the weirdness.
    @Ignore
    @Test
    public void onInputFinalConclusion_LoadingIndicatorShows() {
        Intent intent = new Intent();
        intent.putExtra(IS_RED_WINE, TRUE);
        activityTestRule.launchActivity(intent);

        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
        onView(withText("Clear Form")).perform(click());

        onView(withId(R.id.scrollView_sight)).check(matches(isDisplayed()));
        // Sight Fragment
        onView(withId(R.id.radio_color_ruby)).perform(scrollTo(), click());
        onView(withId(R.id.view_pager_red_deduction)).perform(swipeLeft());

        // Nose Fragment
        onView(withId(R.id.check_nose_fruit_black)).perform(scrollTo(), click());
        onView(withId(R.id.check_nose_fruit_blue)).perform(scrollTo(), click());
        onView(withId(R.id.view_pager_red_deduction)).perform(swipeLeft());

        // Palate Fragment A
        onView(withId(R.id.check_palate_fruit_black)).perform(scrollTo(), click());
        onView(withId(R.id.check_palate_fruit_blue)).perform(scrollTo(), click());
        onView(withId(R.id.view_pager_red_deduction)).perform(swipeLeft());

        // Palate Fragment B
        onView(withId(R.id.radio_palate_tannin_med_plus)).perform(click());
        onView(withId(R.id.radio_palate_acid_med_plus)).perform(click());
        onView(withId(R.id.radio_palate_body_full)).perform(scrollTo(), click());
        onView(withId(R.id.radio_palate_texture_round)).perform(scrollTo(), click());
        onView(withId(R.id.radio_palate_complexity_med_plus)).perform(scrollTo(), click());
        onView(withId(R.id.view_pager_red_deduction)).perform(swipeLeft());

        // Initial Conclusion
        onView(withId(R.id.multiText_initial_varieties)).perform(replaceText("Pinot Noir"));
        onView(withId(R.id.radio_button_initial_world_new)).perform(scrollTo(), click());
        onView(withId(R.id.radio_button_climate_warm)).perform(scrollTo(), click());
        onView(withId(R.id.multiText_initial_countries)).perform(scrollTo(), replaceText("United States"));
        onView(withId(R.id.radio_button_initial_age_onetothree)).perform(scrollTo(), click());
        onView(withId(R.id.view_pager_red_deduction)).perform(swipeLeft());

        // Final Conclusion
        onView(withId(R.id.autoText_final_grape_variety))
                .perform(scrollTo(), replaceText("Pinot Noir"));
        onView(withId(R.id.autoText_final_country))
                .perform(scrollTo(), replaceText("United States"));
        onView(withId(R.id.autoText_final_region))
                .perform(scrollTo(), replaceText("California"));
        onView(withId(R.id.autoText_final_quality))
                .perform(scrollTo(), replaceText("None"));
        onView(withId(R.id.autoText_final_vintage))
                .perform(scrollTo(), replaceText("2012"));
        onView(withId(R.id.button_submit_final_conclusion))
                .perform(click());

        onView(withId(R.id.textView_our_guess)).check(matches(isDisplayed()));
//        onView(withId(R.id.progressBar_final_conclusion)).check(matches(not(isDisplayed())));
    }

    @Test
    public void onInputFinalConclusionWithNoOakSelections_AdvancedToVarietyResults() {
        Intent intent = new Intent();
        intent.putExtra(IS_RED_WINE, TRUE);
        activityTestRule.launchActivity(intent);

        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
        onView(withText("Clear Form")).perform(click());

        onView(withId(R.id.radio_clarity_clear)).check(matches(isDisplayed()));
        // Sight Fragment
        onView(withId(R.id.radio_clarity_clear)).perform(scrollTo(), click());
        onView(withId(R.id.radio_concentration_medium)).perform(scrollTo(), click());
        onView(withId(R.id.radio_color_ruby)).perform(scrollTo(), click());
        onView(withId(R.id.radio_secondary_color_orange)).perform(scrollTo(), click());
        onView(withId(R.id.radio_rim_variation_no)).perform(scrollTo(), click());
        onView(withId(R.id.radio_extract_stain_medium)).perform(scrollTo(), click());
        onView(withId(R.id.radio_tearing_light)).perform(scrollTo(), click());
        onView(withId(R.id.radio_gas_evidence_no)).perform(scrollTo(), click());
        onView(withId(R.id.view_pager_red_deduction)).perform(swipeLeft());

        // Nose Fragment
        onView(withId(R.id.radio_intensity_delicate)).perform(scrollTo()).check(matches(isDisplayed()));
        onView(withId(R.id.radio_intensity_delicate)).perform(scrollTo(), click());
        onView(withId(R.id.radio_age_assessment_youthful)).perform(scrollTo(), click());
        onView(withId(R.id.view_pager_red_deduction)).perform(swipeLeft());

        // Palate Fragment A
        onView(withId(R.id.radio_palate_sweetness_dry)).perform(scrollTo(), click());
        onView(withId(R.id.view_pager_red_deduction)).perform(swipeLeft());

        // Palate Fragment B
        onView(withId(R.id.radio_palate_tannin_med_plus)).perform(scrollTo()).check(matches(isDisplayed()));
        onView(withId(R.id.radio_palate_tannin_med_plus)).perform(scrollTo(), click());
        onView(withId(R.id.radio_palate_acid_med_plus)).perform(scrollTo(), click());
        onView(withId(R.id.radio_palate_alcohol_low)).perform(scrollTo(), click());
        onView(withId(R.id.radio_palate_body_full)).perform(scrollTo(), click());
        onView(withId(R.id.radio_palate_texture_round)).perform(scrollTo(), click());
        onView(withId(R.id.radio_palate_balanced_yes)).perform(scrollTo(), click());
        onView(withId(R.id.radio_palate_length_finish_med_plus)).perform(scrollTo(), click());
        onView(withId(R.id.radio_palate_complexity_med_plus)).perform(scrollTo(), click());
        onView(withId(R.id.view_pager_red_deduction)).perform(swipeLeft());

        // Initial Conclusion
        onView(withId(R.id.multiText_initial_varieties))
                .perform(scrollTo(), replaceText("Cabernet Sauvignon"),
                        pressImeActionButton());
        onView(withId(R.id.radio_button_initial_world_new)).perform(scrollTo(), click());
        onView(withId(R.id.radio_button_climate_warm)).perform(scrollTo(), click());
        onView(withId(R.id.multiText_initial_countries))
                .perform(scrollTo(), replaceText("United States"),
                        pressImeActionButton());
        onView(withId(R.id.radio_button_initial_age_onetothree)).perform(scrollTo(), click());
        onView(withId(R.id.view_pager_red_deduction)).perform(swipeLeft());

        // Final Conclusion
        onView(withId(R.id.autoText_final_grape_variety))
                .perform(scrollTo(), replaceText("Cabernet Sauvignon"),
                        pressImeActionButton());
        onView(withId(R.id.autoText_final_country))
                .perform(replaceText("United States"), pressImeActionButton());
        onView(withId(R.id.autoText_final_region))
                .perform(replaceText("California"), pressImeActionButton());
        onView(withId(R.id.autoText_final_quality))
                .perform(replaceText("None"), pressImeActionButton());
        onView(withId(R.id.autoText_final_vintage))
                .perform(replaceText("2012"), pressImeActionButton());
        onView(withId(R.id.button_submit_final_conclusion))
                .perform(click());

        onView(withId(R.id.textView_our_guess)).check(matches(isDisplayed()));
    }

    @Ignore
    @Test
    public void onStartActivity_allFieldsAreDisplayed() {
        Intent intent = new Intent();
        intent.putExtra(IS_RED_WINE, TRUE);

    }

    @Test
    public void onMissRadioSelection_displaySnackbarError() {
        Intent intent = new Intent();
        intent.putExtra(IS_RED_WINE, TRUE);
        activityTestRule.launchActivity(intent);

        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
        onView(withText("Clear Form")).perform(click());

        // Sight fragment
        onView(withId(R.id.radio_clarity_clear)).perform(scrollTo(), click());
        onView(withId(R.id.radio_concentration_medium)).perform(scrollTo(), click());
        onView(withId(R.id.radio_color_ruby)).perform(scrollTo(), click());
        onView(withId(R.id.radio_secondary_color_blue)).perform(scrollTo(), click());
        onView(withId(R.id.radio_rim_variation_no)).perform(scrollTo(), click());
        onView(withId(R.id.radio_extract_stain_medium)).perform(scrollTo(), click());
        onView(withId(R.id.radio_tearing_medium)).perform(scrollTo(), click());
        onView(withId(R.id.radio_gas_evidence_no)).perform(scrollTo(), click());
        onView(withId(R.id.view_pager_red_deduction)).perform(swipeLeft());

        // Nose fragment
        onView(withId(R.id.radio_intensity_delicate)).perform(scrollTo(), click());
        onView(withId(R.id.radio_age_assessment_youthful)).perform(scrollTo(), click());
        onView(withId(R.id.view_pager_red_deduction)).perform(swipeLeft());

        // Palate fragment A
        onView(withId(R.id.radio_palate_sweetness_dry)).perform(scrollTo(), click());
        onView(withId(R.id.view_pager_red_deduction)).perform(swipeLeft());

        // Palate fragment B
        onView(withId(R.id.radio_palate_tannin_med_plus)).perform(scrollTo(), click());
        onView(withId(R.id.radio_palate_acid_med_plus)).perform(scrollTo(), click());
        onView(withId(R.id.radio_palate_alcohol_low)).perform(scrollTo(), click());
        onView(withId(R.id.radio_palate_body_full)).perform(scrollTo(), click());
        onView(withId(R.id.radio_palate_texture_round)).perform(scrollTo(), click());
        onView(withId(R.id.radio_palate_balanced_yes)).perform(scrollTo(), click());
        onView(withId(R.id.radio_palate_length_finish_med)).perform(scrollTo(), click());
        onView(withId(R.id.radio_palate_complexity_med_plus)).perform(scrollTo(), click());
        onView(withId(R.id.view_pager_red_deduction)).perform(swipeLeft());

        // Initial conclusion
        onView(withId(R.id.multiText_initial_varieties))
                .perform(scrollTo(), replaceText("Cabernet Sauvignon"),
                        pressImeActionButton());
        onView(withId(R.id.radio_button_initial_world_new))
                .perform(scrollTo(), click());
        onView(withId(R.id.radio_button_initial_world_new))
                .perform(scrollTo(), click());
        onView(withId(R.id.radio_button_climate_warm))
                .perform(scrollTo(), click());
        onView(withId(R.id.multiText_initial_countries))
                .perform(replaceText("United States"));
//        onView(withId(R.id.radio_button_initial_age_onetothree))
//                .perform(scrollTo(), click());
        onView(withId(R.id.view_pager_red_deduction)).perform(swipeLeft());

        // Final conclusion
        onView(withId(R.id.autoText_final_grape_variety))
                .perform(scrollTo(), replaceText("Cabernet Sauvignon"),
                        pressImeActionButton());
        onView(withId(R.id.autoText_final_country))
                .perform(replaceText("United States"), pressImeActionButton());
        onView(withId(R.id.autoText_final_region))
                .perform(replaceText("California"), pressImeActionButton());
        onView(withId(R.id.autoText_final_quality))
                .perform(replaceText("None"), pressImeActionButton());
        onView(withId(R.id.autoText_final_vintage))
                .perform(replaceText("2012"), pressImeActionButton());
        onView(withId(R.id.button_submit_final_conclusion))
                .perform(click());

        onView(withText("Please make all selections before continuing."))
                .check(matches(isDisplayed()));
    }
}
