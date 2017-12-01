package com.loyagram.campaignsdkdemo.Preview.csat;


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
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class SomewhatDisTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void somewhatDisTest() {
        ViewInteraction appCompatRadioButton = onView(
                allOf(ViewMatchers.withId(R.id.rdbCsat), withText("CSAT"),
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

        ViewInteraction appCompatButton2 = onView(
                allOf(withText("Start"),
                        withParent(withId(R.id.widgetContainerMain)),
                        isDisplayed()));
        appCompatButton2.perform(click());

        ViewInteraction appCompatRadioButton2 = onView(
                withText("Somewhat dissatisfied"));
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
                allOf(withText("Features"),
                        withParent(withId(R.id.followUpOptionsContainer))));
        appCompatCheckBox.perform(scrollTo(), click());

        ViewInteraction appCompatButton3 = onView(
                allOf(withText("Next"),
                        withParent(allOf(withId(R.id.btnContainer),
                                withParent(withId(R.id.bottomButtonContainer)))),
                        isDisplayed()));
        appCompatButton3.perform(click());

        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.txtReason),
                        withParent(withId(R.id.reasonFooter))));
        appCompatEditText.perform(scrollTo(), replaceText("test"), closeSoftKeyboard());

        ViewInteraction appCompatCheckBox2 = onView(
                allOf(withId(R.id.chkEmail), withText("I would like to receive a follow up."),
                        withParent(allOf(withId(R.id.emailFollowUpContainer),
                                withParent(withId(R.id.reasonFooter))))));
        appCompatCheckBox2.perform(scrollTo(), click());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.txtEmail),
                        withParent(allOf(withId(R.id.emailFollowUpContainer),
                                withParent(withId(R.id.reasonFooter))))));
        appCompatEditText2.perform(scrollTo(), replaceText("demo@loyagram.com"), closeSoftKeyboard());

        ViewInteraction appCompatButton4 = onView(
                allOf(withText("Submit"),
                        withParent(allOf(withId(R.id.btnContainer),
                                withParent(withId(R.id.bottomButtonContainer)))),
                        isDisplayed()));
        appCompatButton4.perform(click());

    }

}
