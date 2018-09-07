package com.wineguesser.deductive;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.wineguesser.deductive.ui.DeductionFormActivity;
import com.wineguesser.deductive.ui.DeductionFormContract;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.action.ViewActions.swipeRight;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class DeductionFormActivityTests implements DeductionFormContract {

    @Rule
    public ActivityTestRule<DeductionFormActivity> activityTestRule =
            new ActivityTestRule<>(DeductionFormActivity.class, false, false);

    @Test
    public void onInputFinalConclusion_LoadingIndicatorShows() {
        Intent intent = new Intent();
        intent.putExtra(IS_RED_WINE, RED_WINE);
        activityTestRule.launchActivity(intent);

        onView(withId(R.layout.fragment_sight_red)).perform(swipeRight());
        onView(withId(R.layout.fragment_nose_red)).perform(swipeRight());
        onView(withId(R.layout.fragment_palate_red_a)).perform(swipeRight());
        onView(withId(R.layout.fragment_palate_red_b)).perform(swipeRight());
        onView(withId(R.layout.fragment_initial_conclusion)).perform(swipeRight());
        onView(withId(R.layout.fragment_final_conclusion))
    }
}
