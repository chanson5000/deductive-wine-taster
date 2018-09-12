package com.wineguesser.deductive;

import android.content.Intent;
import android.graphics.Rect;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.test.espresso.PerformException;
import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.action.ScrollToAction;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.espresso.util.HumanReadables;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.NestedScrollView;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ScrollView;

import com.wineguesser.deductive.view.DeductionFormActivity;
import com.wineguesser.deductive.view.DeductionFormContract;
import com.wineguesser.deductive.view.VarietyResultsActivity;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.action.ViewActions.doubleClick;
import static android.support.test.espresso.action.ViewActions.pressImeActionButton;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.action.ViewActions.swipeUp;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.BundleMatchers.hasEntry;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.intent.matcher.IntentMatchers.toPackage;
import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static android.support.test.espresso.matcher.ViewMatchers.isChecked;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.wineguesser.deductive.CustomScrollActions.nestedScrollTo;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;

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

        onView(withId(R.id.progressBar_final_conclusion)).check(matches(not(isDisplayed())));
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
        onView(withId(R.id.radio_clarity_clear)).perform(scrollTo(), click()).check(matches(isDisplayed()));
        onView(withId(R.id.radio_clarity_clear)).perform(click()).check(matches(isChecked()));
        onView(withId(R.id.radio_concentration_medium)).perform(scrollTo(), click()).check(matches(isChecked()));
        onView(withId(R.id.radio_color_ruby)).perform(scrollTo(), click()).check(matches(isChecked()));
        onView(withId(R.id.radio_secondary_color_orange)).perform(scrollTo(), click()).check(matches(isChecked()));
        onView(withId(R.id.radio_rim_variation_no)).perform(scrollTo(), click()).check(matches(isChecked()));
        onView(withId(R.id.radio_extract_stain_medium)).perform(scrollTo(), click()).check(matches(isChecked()));
        onView(withId(R.id.radio_tearing_light)).perform(scrollTo(), click()).check(matches(isChecked()));
        onView(withId(R.id.radio_gas_evidence_no)).perform(scrollTo(), click()).check(matches(isChecked()));
        onView(withId(R.id.view_pager_red_deduction)).perform(swipeLeft());

        // Nose Fragment
        onView(withId(R.id.radio_intensity_delicate)).perform(scrollTo(), click()).check(matches(isDisplayed()));
        onView(withId(R.id.radio_intensity_delicate)).perform(click()).check(matches(isChecked()));
        onView(withId(R.id.radio_age_assessment_youthful)).perform(scrollTo(), click()).check(matches(isChecked()));
        onView(withId(R.id.view_pager_red_deduction)).perform(swipeLeft());

        // Palate Fragment A
        onView(withId(R.id.radio_palate_sweetness_dry)).perform(scrollTo(), click()).check(matches(isDisplayed()));
        onView(withId(R.id.radio_palate_sweetness_dry)).perform(click()).check(matches(isChecked()));
        onView(withId(R.id.view_pager_red_deduction)).perform(swipeLeft());

        // Palate Fragment B
        onView(withId(R.id.radio_palate_tannin_med_plus)).perform(scrollTo(), click()).check(matches(isDisplayed()));
        onView(withId(R.id.radio_palate_tannin_med_plus)).perform(click()).check(matches(isChecked()));
        onView(withId(R.id.radio_palate_acid_med_plus)).perform(scrollTo(), click()).check(matches(isChecked()));
        onView(withId(R.id.radio_palate_alcohol_low)).perform(scrollTo(), click()).check(matches(isChecked()));
        onView(withId(R.id.radio_palate_body_full)).perform(scrollTo(), click()).check(matches(isChecked()));
        onView(withId(R.id.radio_palate_texture_round)).perform(scrollTo(), click()).check(matches(isChecked()));
        onView(withId(R.id.radio_palate_balanced_yes)).perform(scrollTo(), click()).check(matches(isChecked()));
        onView(withId(R.id.radio_palate_length_finish_med_plus)).perform(scrollTo(), click()).check(matches(isChecked()));
        onView(withId(R.id.radio_palate_complexity_med_plus)).perform(scrollTo(), click()).check(matches(isChecked()));
        onView(withId(R.id.view_pager_red_deduction)).perform(swipeLeft());

        // Initial Conclusion
        onView(withId(R.id.multiText_initial_varieties)).perform(replaceText("Cabernet Sauvignon"));
        onView(withId(R.id.radio_button_initial_world_new)).perform(scrollTo(), click()).check(matches(isDisplayed()));
        onView(withId(R.id.radio_button_initial_world_new)).perform(click()).check(matches(isChecked()));
        onView(withId(R.id.radio_button_climate_warm)).perform(scrollTo(), click()).check(matches(isChecked()));
        onView(withId(R.id.multiText_initial_countries)).perform(replaceText("United States"));
        onView(withId(R.id.radio_button_initial_age_onetothree)).perform(scrollTo(), click()).check(matches(isChecked()));
        onView(withId(R.id.view_pager_red_deduction)).perform(swipeLeft());

        // Final Conclusion
        onView(withId(R.id.autoText_final_grape_variety))
                .perform(replaceText("Cabernet Sauvignon"));
        onView(withId(R.id.autoText_final_country))
                .perform(replaceText("United States"));
        onView(withId(R.id.autoText_final_region))
                .perform(replaceText("California"));
        onView(withId(R.id.autoText_final_quality))
                .perform(replaceText("None"));
        onView(withId(R.id.autoText_final_vintage))
                .perform(replaceText("2012"));
        onView(withId(R.id.button_submit_final_conclusion))
                .perform(click());



        onView(withId(R.id.textView_our_guess)).check(matches(isDisplayed()));
    }
}
