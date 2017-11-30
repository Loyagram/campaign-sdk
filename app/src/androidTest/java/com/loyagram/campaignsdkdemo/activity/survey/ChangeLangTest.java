package com.loyagram.campaignsdkdemo.activity.survey;


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
    public void surveyActivityChangeLangTest() {
        ViewInteraction appCompatRadioButton = onView(
                allOf(ViewMatchers.withId(R.id.rdbSurvey), withText("Survey"),
                        withParent(allOf(withId(R.id.radioGroup),
                                withParent(withId(R.id.radioGroupcontainer)))),
                        isDisplayed()));
        appCompatRadioButton.perform(click());

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.btnActivity), withText("Show in Activity"),
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

        ViewInteraction appCompatButton3 = onView(
                allOf(withText("Seguinte"),
                        withParent(allOf(withId(R.id.btnContainer),
                                withParent(withId(R.id.bottomButtonContainer)))),
                        isDisplayed()));
        appCompatButton3.perform(click());

        ViewInteraction appCompatSpinner2 = onView(
                allOf(withId(R.id.spinnerLang),
                        withParent(allOf(withId(R.id.spinnerContainer),
                                withParent(withId(R.id.campaingHeader)))),
                        isDisplayed()));
        appCompatSpinner2.perform(click());

        ViewInteraction textView2 = onView(
                allOf(withText("Español"), isDisplayed()));
        textView2.perform(click());

        ViewInteraction appCompatButton4 = onView(
                allOf(withText("Siguiente"),
                        withParent(allOf(withId(R.id.btnContainer),
                                withParent(withId(R.id.bottomButtonContainer)))),
                        isDisplayed()));
        appCompatButton4.perform(click());

        ViewInteraction appCompatButton5 = onView(
                allOf(withText("Siguiente"),
                        withParent(allOf(withId(R.id.btnContainer),
                                withParent(withId(R.id.bottomButtonContainer)))),
                        isDisplayed()));
        appCompatButton5.perform(click());

        ViewInteraction appCompatButton6 = onView(
                allOf(withText("Siguiente"),
                        withParent(allOf(withId(R.id.btnContainer),
                                withParent(withId(R.id.bottomButtonContainer)))),
                        isDisplayed()));
        appCompatButton6.perform(click());

        ViewInteraction appCompatButton7 = onView(
                allOf(withText("Siguiente"),
                        withParent(allOf(withId(R.id.btnContainer),
                                withParent(withId(R.id.bottomButtonContainer)))),
                        isDisplayed()));
        appCompatButton7.perform(click());

        ViewInteraction appCompatButton8 = onView(
                allOf(withText("Siguiente"),
                        withParent(allOf(withId(R.id.btnContainer),
                                withParent(withId(R.id.bottomButtonContainer)))),
                        isDisplayed()));
        appCompatButton8.perform(click());

        ViewInteraction appCompatButton9 = onView(
                allOf(withText("Siguiente"),
                        withParent(allOf(withId(R.id.btnContainer),
                                withParent(withId(R.id.bottomButtonContainer)))),
                        isDisplayed()));
        appCompatButton9.perform(click());

        ViewInteraction appCompatButton10 = onView(
                allOf(withText("Enviar"),
                        withParent(allOf(withId(R.id.btnContainer),
                                withParent(withId(R.id.bottomButtonContainer)))),
                        isDisplayed()));
        appCompatButton10.perform(click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
