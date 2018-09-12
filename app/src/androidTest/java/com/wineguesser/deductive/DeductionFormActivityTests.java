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
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.action.ViewActions.swipeUp;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.BundleMatchers.hasEntry;
import static android.support.test.espresso.intent.matcher.IntentMatchers.toPackage;
import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
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
import static org.hamcrest.CoreMatchers.not;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class DeductionFormActivityTests implements DeductionFormContract {

//    private static CollapsingToolbarLayout findCollapsingToolbarLayoutChildIn(ViewGroup viewGroup) {
//        for (int i = 0; i < viewGroup.getChildCount(); i++) {
//            View child = viewGroup.getChildAt(i);
//            if (child instanceof CollapsingToolbarLayout) {
//                return (CollapsingToolbarLayout) child;
//            } else if (child instanceof ViewGroup) {
//                return findCollapsingToolbarLayoutChildIn((ViewGroup) child);
//            }
//        }
//        return null;
//    }
//
//    private static View findFirstParentLayoutOfClass(View view, Class<? extends View> parentClass) {
//        ViewParent parent = new FrameLayout(view.getContext());
//        ViewParent incrementView = null;
//        int i = 0;
//        while (parent != null && !(parent.getClass() == parentClass)) {
//            if (i == 0) {
//                parent = findParent(view);
//            } else {
//                parent = findParent(incrementView);
//            }
//            incrementView = parent;
//            i++;
//        }
//        return (View) parent;
//    }
//
//    private static ViewParent findParent(View view) {
//        return view.getParent();
//    }
//
//    private static ViewParent findParent(ViewParent view) {
//        return view.getParent();
//    }
//
//    public static ViewAction nestedScrollTo() {
//        return new ViewAction() {
//            @Override
//            public Matcher<View> getConstraints() {
//                return allOf(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE),
//                        isDescendantOfA(isAssignableFrom(NestedScrollView.class)));
//            }
//
//            @Override
//            public String getDescription() {
//                return null;
//            }
//
//            @Override
//            public void perform(UiController uiController, View view) {
//                try {
//                    NestedScrollView nestedScrollView = (NestedScrollView)
//                            findFirstParentLayoutOfClass(view, NestedScrollView.class);
//
////                    Rect rect = new Rect();
////                    view.getDrawingRect(rect);
//                    if (nestedScrollView != null) {
//
//                        nestedScrollView.scrollTo(0, view.getTop());
//                    } else {
//                        throw new Exception("Unable to find NestedScrollView parent.");
//                    }
//
////                    NestedScrollView nestedScrollView = (NestedScrollView)
////                            findFirstParentLayoutOfClass(view, NestedScrollView.class);
////                    if (nestedScrollView != null) {
////                        CoordinatorLayout coordinatorLayout =
////                                (CoordinatorLayout) findFirstParentLayoutOfClass(view, CoordinatorLayout.class);
////                        if (coordinatorLayout != null) {
////                            CollapsingToolbarLayout collapsingToolbarLayout =
////                                    findCollapsingToolbarLayoutChildIn(coordinatorLayout);
////                            if (collapsingToolbarLayout != null) {
////                                int toolbarHeight = collapsingToolbarLayout.getHeight();
////                                nestedScrollView.startNestedScroll(ViewCompat.SCROLL_AXIS_VERTICAL);
////                                nestedScrollView.dispatchNestedPreScroll(0, toolbarHeight, null, null);
////                            }
////                        }
////                        nestedScrollView.scrollTo(0, view.getTop());
////                    }
//                } catch (Exception e) {
//                    throw new PerformException.Builder()
//                            .withActionDescription(this.getDescription())
//                            .withViewDescription(HumanReadables.describe(view))
//                            .withCause(e)
//                            .build();
//                }
//                uiController.loopMainThreadUntilIdle();
//            }
//        };
//    }

    @Rule
    public final ActivityTestRule<DeductionFormActivity> activityTestRule =
            new ActivityTestRule<>(DeductionFormActivity.class, true, false);

    @Test
    public void onInputFinalConclusion_LoadingIndicatorShows() {
        Intent intent = new Intent();
        intent.putExtra(IS_RED_WINE, TRUE);
        activityTestRule.launchActivity(intent);

        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
        onView(withText("Clear Form")).perform(click());

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
        onView(withId(R.id.radio_palate_tannin_med_plus)).perform(scrollTo(), click());
        onView(withId(R.id.radio_palate_acid_med_plus)).perform(scrollTo(), click());
        onView(withId(R.id.radio_palate_body_full)).perform(scrollTo(), click());
        onView(withId(R.id.radio_palate_texture_round)).perform(scrollTo(), click());
        onView(withId(R.id.radio_palate_complexity_med_plus)).perform(scrollTo(), click());
        onView(withId(R.id.view_pager_red_deduction)).perform(swipeLeft());

        // Initial Conclusion
        onView(withId(R.id.multiText_initial_varieties)).perform(scrollTo(), replaceText("Pinot Noir"));
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
    @LargeTest
    public void onInputFinalConclusionWithNoOakSelections_AdvancedToVarietyResults() {
        Intent intent = new Intent();
        intent.putExtra(IS_RED_WINE, TRUE);
        activityTestRule.launchActivity(intent);

        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
        onView(withText("Clear Form")).perform(click());

        // Sight Fragment
//        onView(withId(R.id.radio_clarity_clear)).perform(nestedScrollTo());
        onView(withId(R.id.radio_clarity_clear)).perform(click());
//        onView(withId(R.id.radio_concentration_medium)).perform(nestedScrollTo());
        onView(withId(R.id.radio_concentration_medium)).perform(click());
        onView(withId(R.id.radio_color_ruby)).perform(nestedScrollTo());
        onView(withId(R.id.radio_color_ruby)).perform(click());
        onView(withId(R.id.radio_secondary_color_orange)).perform(nestedScrollTo());
        onView(withId(R.id.radio_secondary_color_orange)).perform(click());
        onView(withId(R.id.radio_rim_variation_no)).perform(nestedScrollTo());
        onView(withId(R.id.radio_rim_variation_no)).perform(click());
        onView(withId(R.id.radio_extract_stain_medium)).perform(nestedScrollTo());
        onView(withId(R.id.radio_extract_stain_medium)).perform(click());
        onView(withId(R.id.radio_tearing_light)).perform(nestedScrollTo());
        onView(withId(R.id.radio_tearing_light)).perform(click());
        onView(withId(R.id.radio_gas_evidence_no)).perform(nestedScrollTo());
        onView(withId(R.id.radio_gas_evidence_no)).perform(click());
        onView(withId(R.id.view_pager_red_deduction)).perform(swipeLeft());

        // Nose Fragment
        onView(withId(R.id.radio_intensity_moderate)).perform(nestedScrollTo());
        onView(withId(R.id.radio_intensity_moderate)).perform(click());
        onView(withId(R.id.radio_age_assessment_youthful)).perform(click());
        onView(withId(R.id.radio_age_assessment_youthful)).perform(click());
        onView(withId(R.id.view_pager_red_deduction)).perform(swipeLeft());

        // Palate Fragment A
        onView(withId(R.id.radio_palate_sweetness_dry)).perform(nestedScrollTo());
        onView(withId(R.id.radio_palate_sweetness_dry)).perform(nestedScrollTo());
        onView(withId(R.id.view_pager_red_deduction)).perform(swipeLeft());

        // Palate Fragment B
        onView(withId(R.id.radio_palate_tannin_med_plus)).perform(nestedScrollTo());
        onView(withId(R.id.radio_palate_tannin_med_plus)).perform(click());
        onView(withId(R.id.radio_palate_acid_med_plus)).perform(nestedScrollTo());
        onView(withId(R.id.radio_palate_acid_med_plus)).perform(click());
        onView(withId(R.id.radio_palate_alcohol_low)).perform(nestedScrollTo());
        onView(withId(R.id.radio_palate_alcohol_low)).perform(click());
        onView(withId(R.id.radio_palate_body_full)).perform(nestedScrollTo());
        onView(withId(R.id.radio_palate_body_full)).perform(click());
        onView(withId(R.id.radio_palate_texture_round)).perform(nestedScrollTo());
        onView(withId(R.id.radio_palate_texture_round)).perform(click());
        onView(withId(R.id.radio_palate_balanced_yes)).perform(nestedScrollTo());
        onView(withId(R.id.radio_palate_balanced_yes)).perform(click());
        onView(withId(R.id.radio_palate_length_finish_med_plus)).perform(nestedScrollTo());
        onView(withId(R.id.radio_palate_length_finish_med_plus)).perform(click());
        onView(withId(R.id.radio_palate_complexity_med_plus)).perform(nestedScrollTo());
        onView(withId(R.id.radio_palate_complexity_med_plus)).perform(click());
        onView(withId(R.id.view_pager_red_deduction)).perform(swipeLeft());

        // Initial Conclusion
        onView(withId(R.id.multiText_initial_varieties)).perform(replaceText("Cabernet Sauvignon"));
        onView(withId(R.id.radio_button_initial_world_new)).perform(nestedScrollTo());
        onView(withId(R.id.radio_button_initial_world_new)).perform(click());
        onView(withId(R.id.radio_button_climate_warm)).perform(nestedScrollTo());
        onView(withId(R.id.radio_button_climate_warm)).perform(click());
        onView(withId(R.id.multiText_initial_countries)).perform(nestedScrollTo());
        onView(withId(R.id.multiText_initial_countries)).perform(replaceText("United States"));
        onView(withId(R.id.radio_button_initial_age_onetothree)).perform(nestedScrollTo());
        onView(withId(R.id.radio_button_initial_age_onetothree)).perform(click());
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
//        onView(withId(R.id.textView_our_guess)).check(matches(not(isDisplayed())));
    }

    @Test
    public void DeductionFormNestedScroll_ViewIsDisplayed() {
        Intent intent = new Intent();
        intent.putExtra(IS_RED_WINE, TRUE);
        activityTestRule.launchActivity(intent);

        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
        onView(withText("Clear Form")).perform(click());

        onView(withId(R.id.radio_gas_evidence_no)).perform(nestedScrollTo());
        onView(withId(R.id.radio_gas_evidence_no)).check(matches(isDisplayed()));
    }

}
