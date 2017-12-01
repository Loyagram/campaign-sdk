package com.loyagram.campaignsdkdemo.Dialog.ces;


import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.loyagram.campaignsdkdemo.MainActivity;
import com.loyagram.campaignsdkdemo.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class AgreeTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void mainActivityTest() {
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
                allOf(withId(R.id.btnDialog), withText("Show as Dialog"),
                        childAtPosition(
                                allOf(withId(R.id.buttoncontainer),
                                        childAtPosition(
                                                withId(R.id.mainContianer),
                                                1)),
                                1),
                        isDisplayed()));
        appCompatButton.perform(click());

        ViewInteraction appCompatButton2 = onView(
                allOf(withText("Start"),
                        childAtPosition(
                                allOf(withId(R.id.widgetContainerMain),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                1)),
                                4),
                        isDisplayed()));
        appCompatButton2.perform(click());

        ViewInteraction appCompatRadioButton2 = onView(
                allOf(withText("Agree"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.optionsContainer),
                                        0),
                                0)));
        appCompatRadioButton2.perform(scrollTo(), click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction appCompatCheckBox = onView(
                allOf(withText("Service"),
                        childAtPosition(
                                allOf(withId(R.id.followUpOptionsContainer),
                                        childAtPosition(
                                                withClassName(is("android.widget.ScrollView")),
                                                0)),
                                0)));
        appCompatCheckBox.perform(scrollTo(), click());

        ViewInteraction appCompatButton3 = onView(
                allOf(withText("Next"),
                        childAtPosition(
                                allOf(withId(R.id.btnContainer),
                                        childAtPosition(
                                                withId(R.id.bottomButtonContainer),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatButton3.perform(click());

        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.txtReason),
                        childAtPosition(
                                allOf(withId(R.id.reasonFooter),
                                        childAtPosition(
                                                withClassName(is("android.widget.ScrollView")),
                                                0)),
                                1)));
        appCompatEditText.perform(scrollTo(), replaceText("test"), closeSoftKeyboard());

        ViewInteraction appCompatCheckBox2 = onView(
                allOf(withId(R.id.chkEmail), withText("I would like to receive a follow up."),
                        childAtPosition(
                                allOf(withId(R.id.emailFollowUpContainer),
                                        childAtPosition(
                                                withId(R.id.reasonFooter),
                                                2)),
                                0)));
        appCompatCheckBox2.perform(scrollTo(), click());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.txtEmail),
                        childAtPosition(
                                allOf(withId(R.id.emailFollowUpContainer),
                                        childAtPosition(
                                                withId(R.id.reasonFooter),
                                                2)),
                                1)));
        appCompatEditText2.perform(scrollTo(), replaceText("demo@loyagram.com"), closeSoftKeyboard());

        ViewInteraction appCompatButton4 = onView(
                allOf(withText("Submit"),
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
