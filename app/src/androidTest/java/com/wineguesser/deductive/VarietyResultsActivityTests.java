package com.wineguesser.deductive;

import android.content.Intent;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import com.wineguesser.deductive.view.DeductionFormContract;
import com.wineguesser.deductive.view.VarietyResultsActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.pressImeActionButton;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;


@RunWith(AndroidJUnit4.class)
public class VarietyResultsActivityTests implements DeductionFormContract {

    @Rule
    public final ActivityTestRule<VarietyResultsActivity> activityTestRule =
            new ActivityTestRule<>(VarietyResultsActivity.class, false, false);

    @Test
    public void onInvalidInput_errorsShow() {
        Intent intent = new Intent();
        intent.putExtra(IS_RED_WINE, true);
        intent.putExtra("TESTING", true);
        intent.putExtra(APP_VARIETY_GUESS_ID, "cabernetSauvignonNewWorld");
        intent.putExtra(USER_CONCLUSION_VARIETY, "Merlot");
        intent.putExtra(USER_CONCLUSION_COUNTRY, "United States");
        intent.putExtra(USER_CONCLUSION_REGION, "California");
        intent.putExtra(USER_CONCLUSION_QUALITY, "None");
        intent.putExtra(USER_CONCLUSION_VINTAGE, 2013);
        activityTestRule.launchActivity(intent);

        onView(withId(R.id.editText_actual_label))
                .perform(scrollTo(), replaceText("None"), pressImeActionButton());

        onView(withId(R.id.autoText_actual_variety))
                .perform(replaceText("NebbioloNotValid"), pressImeActionButton());

        onView(withId(R.id.autoText_actual_country))
                .perform(replaceText("ItalyNotValid"), pressImeActionButton());

        onView(withId(R.id.autoText_actual_region))
                .perform(replaceText("PiedmontNotValid"), pressImeActionButton());

        onView(withId(R.id.autoText_actual_quality))
                .perform(replaceText("None"), pressImeActionButton());

        onView(withId(R.id.autoText_actual_vintage))
                .perform(replaceText("1800"), pressImeActionButton());

        onView(withId(R.id.textError_variety)).perform(scrollTo())
                .check(matches(withText(R.string.error_input_valid_grape)));
        onView(withId(R.id.textError_country)).perform(scrollTo())
                .check(matches(withText(R.string.error_input_country_origin)));
        onView(withId(R.id.textError_region)).perform(scrollTo())
                .check(matches(withText(R.string.error_input_valid_region)));
        onView(withId(R.id.textError_vintage)).perform(scrollTo())
                .check(matches(withText(R.string.error_input_valid_vintage)));
    }

    @Test
    public void onValidInput_noErrorsShow() {
        Intent intent = new Intent();
        intent.putExtra(IS_RED_WINE, true);
        intent.putExtra("TESTING", true);
        intent.putExtra(APP_VARIETY_GUESS_ID, "cabernetSauvignonNewWorld");
        intent.putExtra(USER_CONCLUSION_VARIETY, "Merlot");
        intent.putExtra(USER_CONCLUSION_COUNTRY, "United States");
        intent.putExtra(USER_CONCLUSION_REGION, "California");
        intent.putExtra(USER_CONCLUSION_QUALITY, "None");
        intent.putExtra(USER_CONCLUSION_VINTAGE, 2013);
        activityTestRule.launchActivity(intent);

        onView(withId(R.id.editText_actual_label))
                .perform(scrollTo(), replaceText("None"), pressImeActionButton());

        onView(withId(R.id.autoText_actual_variety))
                .perform(replaceText("Nebbiolo"), pressImeActionButton());

        onView(withId(R.id.autoText_actual_country))
                .perform(replaceText("Italy"), pressImeActionButton());

        onView(withId(R.id.autoText_actual_region))
                .perform(replaceText("Piedmont"), pressImeActionButton());

        onView(withId(R.id.autoText_actual_quality))
                .perform(replaceText("None"), pressImeActionButton());

        onView(withId(R.id.autoText_actual_vintage))
                .perform(replaceText("2012"), pressImeActionButton());

        onView(withText(R.string.error_input_valid_grape)).check(doesNotExist());
        onView(withText(R.string.error_input_country_origin)).check(doesNotExist());
        onView(withText(R.string.error_input_valid_region)).check(doesNotExist());
        onView(withText(R.string.error_input_valid_vintage)).check(doesNotExist());
    }
}
