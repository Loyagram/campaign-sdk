package com.loyagram.campaignsdkdemo.Preview.Survey;


import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.loyagram.campaignsdkdemo.MainActivity;
import com.loyagram.campaignsdkdemo.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class SurveryPreviewSingleSelTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void surveryPreviewSingleSelTest() {
        ViewInteraction appCompatRadioButton = onView(
                allOf(ViewMatchers.withId(R.id.rdbSurvey), withText("Survey"),
                        withParent(allOf(withId(R.id.radioGroup),
                                withParent(withId(R.id.radioGroupcontainer)))),
                        isDisplayed()));
        appCompatRadioButton.perform(click());

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.btnPreview), withText("Preview"),
                        withParent(allOf(withId(R.id.buttoncontainer),
                                withParent(withId(R.id.mainContianer)))),
                        isDisplayed()));
        appCompatButton.perform(click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        ViewInteraction appCompatButton2 = onView(
                allOf(withText("Start"),
                        withParent(withId(R.id.widgetContainerMain)),
                        isDisplayed()));
        appCompatButton2.perform(click());

        ViewInteraction textView = onView(
                allOf(withId(R.id.ratingView6), withText("6"),
                        withParent(allOf(withId(R.id.ratingContainer),
                                withParent(withId(R.id.topRatingContainer)))),
                        isDisplayed()));
        textView.perform(click());

        ViewInteraction appCompatButton4 = onView(
                allOf(withText("Next"),
                        withParent(allOf(withId(R.id.btnContainer),
                                withParent(withId(R.id.bottomButtonContainer)))),
                        isDisplayed()));
        appCompatButton4.perform(click());

        ViewInteraction appCompatRadioButton2 = onView(
                withText("GOT"));
        appCompatRadioButton2.perform(scrollTo(), click());

        ViewInteraction appCompatRadioButton3 = onView(
                withText("Sherlock"));
        appCompatRadioButton3.perform(scrollTo(), click());

        ViewInteraction appCompatRadioButton4 = onView(
                withText("GOT"));
        appCompatRadioButton4.perform(scrollTo(), click());

        ViewInteraction appCompatRadioButton5 = onView(
                withText("walking dead"));
        appCompatRadioButton5.perform(scrollTo(), click());

        ViewInteraction appCompatRadioButton6 = onView(
                withText("GOT"));
        appCompatRadioButton6.perform(scrollTo(), click());

        ViewInteraction appCompatRadioButton7 = onView(
                withText("walking dead"));
        appCompatRadioButton7.perform(scrollTo(), click());

        ViewInteraction appCompatButton5 = onView(
                allOf(withText("Next"),
                        withParent(allOf(withId(R.id.btnContainer),
                                withParent(withId(R.id.bottomButtonContainer)))),
                        isDisplayed()));
        appCompatButton5.perform(click());
        appCompatButton5.perform(click());
        appCompatButton5.perform(click());
        ViewInteraction appCompatButton3 = onView(
                allOf(withText("Submit"),
                        withParent(allOf(withId(R.id.btnContainer),
                                withParent(withId(R.id.bottomButtonContainer)))),
                        isDisplayed()));
        appCompatButton3.perform(click());
        mActivityTestRule.getActivity().finish();

    }

}
