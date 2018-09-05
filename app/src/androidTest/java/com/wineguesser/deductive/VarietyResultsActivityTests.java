package com.wineguesser.deductive;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.wineguesser.deductive.ui.DeductionFormContract;
import com.wineguesser.deductive.ui.VarietyResultsActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withText;


@RunWith(AndroidJUnit4.class)
public class VarietyResultsActivityTests implements DeductionFormContract {

    @Rule
    public ActivityTestRule<VarietyResultsActivity> activityTestRule =
            new ActivityTestRule<>(VarietyResultsActivity.class, false, false);

    @Test
    public void onInputInvalidVariety_ErrorTextShows() {
        Intent intent = new Intent();
        intent.putExtra(IS_RED_WINE, true);
        intent.putExtra("DISABLE_AD", true);
        intent.putExtra(APP_VARIETY_GUESS_ID, "cabernetSauvignonNewWorld");
        intent.putExtra(USER_CONCLUSION_VARIETY, "Merlot");
        intent.putExtra(USER_CONCLUSION_COUNTRY, "United States");
        intent.putExtra(USER_CONCLUSION_REGION, "California");
        intent.putExtra(USER_CONCLUSION_QUALITY, "None");
        intent.putExtra(USER_CONCLUSION_VINTAGE, 2013);
        activityTestRule.launchActivity(intent);

        onView(withId(R.id.autoText_actual_variety))
                .perform(scrollTo(), replaceText("NebbioloNotValid"));

        onView(withId(R.id.autoText_actual_country))
                .perform(scrollTo(), replaceText("ItalyNotValid"));

        onView(withId(R.id.autoText_actual_region))
                .perform(scrollTo(), replaceText("PiedmontNotValid"));

        onView(withId(R.id.autoText_actual_quality))
                .perform(scrollTo(), replaceText("None"));

        onView(withId(R.id.autoText_actual_vintage))
                .perform(scrollTo(), replaceText("1800"));

        onView(withId(R.id.wine_result_save)).perform(scrollTo(), click());

        onView(withId(R.id.textError_variety))
                .check(matches(withText(R.string.error_input_valid_grape)));
        onView(withId(R.id.textError_country))
                .check(matches(withText(R.string.error_input_country_origin)));
        onView(withId(R.id.textError_region))
                .check(matches(withText(R.string.error_input_valid_region)));
        onView(withId(R.id.textError_vintage))
                .check(matches(withText(R.string.error_input_valid_vintage)));

    }

    @Test
    public void onInputActualWine_LaunchHistoryActivity() {
        Intent intent = new Intent();
        intent.putExtra(IS_RED_WINE, true);
        intent.putExtra("DISABLE_AD", true);

        intent.putExtra(APP_VARIETY_GUESS_ID, "cabernetSauvignonNewWorld");
        intent.putExtra(USER_CONCLUSION_VARIETY, "Merlot");
        intent.putExtra(USER_CONCLUSION_COUNTRY, "United States");
        intent.putExtra(USER_CONCLUSION_REGION, "California");
        intent.putExtra(USER_CONCLUSION_QUALITY, "None");
        intent.putExtra(USER_CONCLUSION_VINTAGE, 2013);

        activityTestRule.launchActivity(intent);

        onView(withId(R.id.autoText_actual_variety))
                .perform(scrollTo(), replaceText("Nebbiolo"));

        onView(withId(R.id.autoText_actual_country))
                .perform(scrollTo(), replaceText("Italy"));

        onView(withId(R.id.autoText_actual_region))
                .perform(scrollTo(), replaceText("Piedmont"));

        onView(withId(R.id.autoText_actual_quality))
                .perform(scrollTo(), replaceText("None"));

        onView(withId(R.id.autoText_actual_vintage))
                .perform(scrollTo(), replaceText("2012"));

        onView(withId(R.id.wine_result_save)).perform(scrollTo(), click());
        onView(withId(R.id.conclusion_item_recycler_view)).check(matches(isDisplayed()));
    }
}
