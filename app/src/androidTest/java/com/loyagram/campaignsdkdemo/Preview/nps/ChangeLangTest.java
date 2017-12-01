package com.loyagram.campaignsdkdemo.Preview.nps;


import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.loyagram.campaignsdkdemo.MainActivity;
import com.loyagram.campaignsdkdemo.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class ChangeLangTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void npsActivityChangeLang() {
        ViewInteraction appCompatButton = onView(
                allOf(ViewMatchers.withId(R.id.btnPreview), withText("Preview"),
                        withParent(allOf(withId(R.id.buttoncontainer),
                                withParent(withId(R.id.mainContianer)))),
                        isDisplayed()));
        appCompatButton.perform(click());

        ViewInteraction appCompatSpinner = onView(
                allOf(withId(R.id.spinnerLang),
                        withParent(allOf(withId(R.id.spinnerContainer),
                                withParent(withId(R.id.campaingHeader)))),
                        isDisplayed()));
        appCompatSpinner.perform(click());

        ViewInteraction textView = onView(
                allOf(withText("Português"), isDisplayed()));
        textView.perform(click());

        ViewInteraction appCompatButton2 = onView(
                allOf(withText("Início"),
                        withParent(withId(R.id.widgetContainerMain)),
                        isDisplayed()));
        appCompatButton2.perform(click());

        ViewInteraction appCompatSpinner2 = onView(
                allOf(withId(R.id.spinnerLang),
                        withParent(allOf(withId(R.id.spinnerContainer),
                                withParent(withId(R.id.campaingHeader)))),
                        isDisplayed()));
        appCompatSpinner2.perform(click());

        ViewInteraction textView2 = onView(
                allOf(withText("Español"), isDisplayed()));
        textView2.perform(click());

        ViewInteraction textView3 = onView(
                allOf(withId(R.id.ratingView8), withText("8"),
                        withParent(allOf(withId(R.id.ratingContainer),
                                withParent(withId(R.id.topRatingContainer)))),
                        isDisplayed()));
        textView3.perform(click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(150);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction appCompatSpinner3 = onView(
                allOf(withId(R.id.spinnerLang),
                        withParent(allOf(withId(R.id.spinnerContainer),
                                withParent(withId(R.id.campaingHeader)))),
                        isDisplayed()));
        appCompatSpinner3.perform(click());

        ViewInteraction textView4 = onView(
                allOf(withText("English"), isDisplayed()));
        textView4.perform(click());

        ViewInteraction appCompatButton3 = onView(
                allOf(withText("Next"),
                        withParent(allOf(withId(R.id.btnContainer),
                                withParent(withId(R.id.bottomButtonContainer)))),
                        isDisplayed()));
        appCompatButton3.perform(click());

        ViewInteraction appCompatSpinner4 = onView(
                allOf(withId(R.id.spinnerLang),
                        withParent(allOf(withId(R.id.spinnerContainer),
                                withParent(withId(R.id.campaingHeader)))),
                        isDisplayed()));
        appCompatSpinner4.perform(click());

        ViewInteraction textView5 = onView(
                allOf(withText("Español"), isDisplayed()));
        textView5.perform(click());

        ViewInteraction appCompatButton4 = onView(
                allOf(withText("Enviar"),
                        withParent(allOf(withId(R.id.btnContainer),
                                withParent(withId(R.id.bottomButtonContainer)))),
                        isDisplayed()));
        appCompatButton4.perform(click());

    }

}
