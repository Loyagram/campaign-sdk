package com.loyagram.campaignsdkdemo.activity.ces;


import android.support.test.espresso.DataInteraction;
import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.action.ViewActions.*;
import static android.support.test.espresso.assertion.ViewAssertions.*;
import static android.support.test.espresso.matcher.ViewMatchers.*;

import com.loyagram.campaignsdkdemo.MainActivity;
import com.loyagram.campaignsdkdemo.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class ChangeLang {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void changeLang() {
        ViewInteraction appCompatRadioButton = onView(
                allOf(withId(R.id.rdbCes), withText("CES"),
                        childAtPosition(
                                allOf(withId(R.id.radioGroup),
                                        childAtPosition(
                                                withId(R.id.radioGroupcontainer),
                                                0)),
                                3),
                        isDisplayed()));
        appCompatRadioButton.perform(click());

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.btnActivity), withText("Show in Activity"),
                        childAtPosition(
                                allOf(withId(R.id.buttoncontainer),
                                        childAtPosition(
                                                withId(R.id.mainContianer),
                                                1)),
                                0),
                        isDisplayed()));
        appCompatButton.perform(click());

        ViewInteraction appCompatSpinner = onView(
                allOf(withId(R.id.spinnerLang),
                        withParent(allOf(withId(R.id.spinnerContainer),
                                withParent(withId(R.id.campaingHeader)))),
                        isDisplayed()));
        appCompatSpinner.perform(click());

        ViewInteraction textView = onView(
                allOf(withText("Español"), isDisplayed()));
        textView.perform(click());

        ViewInteraction appCompatButton2 = onView(
                allOf(withText("Inicio"),
                        childAtPosition(
                                allOf(withId(R.id.widgetContainerMain),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                1)),
                                4),
                        isDisplayed()));
        appCompatButton2.perform(click());

        ViewInteraction appCompatRadioButton2 = onView(
                allOf(withText("Ni de acuerdo ni en desacuerdo"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.optionsContainer),
                                        0),
                                2)));
        appCompatRadioButton2.perform(scrollTo(), click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction appCompatSpinner1 = onView(
                allOf(withId(R.id.spinnerLang),
                        withParent(allOf(withId(R.id.spinnerContainer),
                                withParent(withId(R.id.campaingHeader)))),
                        isDisplayed()));
        appCompatSpinner1.perform(click());

        ViewInteraction textView1 = onView(
                allOf(withText("Português"), isDisplayed()));
        textView1.perform(click());


        ViewInteraction appCompatButton3 = onView(
                allOf(withText("Seguinte"),
                        childAtPosition(
                                allOf(withId(R.id.btnContainer),
                                        childAtPosition(
                                                withId(R.id.bottomButtonContainer),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatButton3.perform(click());

        ViewInteraction appCompatButton4 = onView(
                allOf(withText("Enviar"),
                        childAtPosition(
                                allOf(withId(R.id.btnContainer),
                                        childAtPosition(
                                                withId(R.id.bottomButtonContainer),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatButton4.perform(click());

    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
