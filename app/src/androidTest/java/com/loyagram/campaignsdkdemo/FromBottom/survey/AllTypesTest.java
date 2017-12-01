package com.loyagram.campaignsdkdemo.FromBottom.survey;

import android.support.test.espresso.ViewInteraction;
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
import static android.support.test.espresso.action.ViewActions.pressImeActionButton;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class AllTypesTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void  surveyActivityAllTypes() {

        ViewInteraction appCompatRadioButton = onView(
                allOf(withId(R.id.rdbSurvey), withText("Survey"),
                        withParent(allOf(withId(R.id.radioGroup),
                                withParent(withId(R.id.radioGroupcontainer)))),
                        isDisplayed()));
        appCompatRadioButton.perform(click());

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.btnFromBottom), withText("Slide From Bottom"),
                        withParent(allOf(withId(R.id.buttoncontainer),
                                withParent(withId(R.id.mainContianer)))),
                        isDisplayed()));
        appCompatButton.perform(click());

        ViewInteraction appCompatButton2 = onView(
                allOf(withText("Start"),
                        withParent(withId(R.id.widgetContainerMain)),
                        isDisplayed()));
        appCompatButton2.perform(click());

        ViewInteraction appCompatButton3 = onView(
                allOf(withText("Next"),
                        withParent(allOf(withId(R.id.btnContainer),
                                withParent(withId(R.id.bottomButtonContainer)))),
                        isDisplayed()));
        appCompatButton3.perform(click());

        ViewInteraction appCompatRadioButton2 = onView(
                withText("Liverpool"));
        appCompatRadioButton2.perform(scrollTo(), click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction appCompatCheckBox = onView(
                allOf(withText("Drama"),
                        withParent(withId(R.id.optionsContainer))));
        appCompatCheckBox.perform(scrollTo(), click());

        ViewInteraction appCompatCheckBox2 = onView(
                allOf(withText("Thriller"),
                        withParent(withId(R.id.optionsContainer))));
        appCompatCheckBox2.perform(scrollTo(), click());

        ViewInteraction appCompatButton4 = onView(
                allOf(withText("Next"),
                        withParent(allOf(withId(R.id.btnContainer),
                                withParent(withId(R.id.bottomButtonContainer)))),
                        isDisplayed()));
        appCompatButton4.perform(click());

        ViewInteraction textView = onView(
                allOf(withId(R.id.ratingView8), withText("8"),
                        withParent(allOf(withId(R.id.ratingContainer),
                                withParent(withId(R.id.topRatingContainer)))),
                        isDisplayed()));
        textView.perform(click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(150);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction appCompatEditText = onView(
                allOf(withClassName(is("android.support.v7.widget.AppCompatEditText")),
                        withParent(withId(R.id.textViewContainer))));
        appCompatEditText.perform(scrollTo(), replaceText("excellent service"), closeSoftKeyboard());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withText("excellent service"),
                        withParent(withId(R.id.textViewContainer))));
        appCompatEditText2.perform(pressImeActionButton());

        ViewInteraction appCompatButton5 = onView(
                allOf(withText("Next"),
                        withParent(allOf(withId(R.id.btnContainer),
                                withParent(withId(R.id.bottomButtonContainer)))),
                        isDisplayed()));
        appCompatButton5.perform(click());

        ViewInteraction appCompatEditText3 = onView(
                allOf(withClassName(is("android.support.v7.widget.AppCompatEditText")),
                        withParent(withId(R.id.textViewContainer))));
        appCompatEditText3.perform(replaceText("try to reduce price"), closeSoftKeyboard());

        ViewInteraction appCompatButton6 = onView(
                allOf(withText("Next"),
                        withParent(allOf(withId(R.id.btnContainer),
                                withParent(withId(R.id.bottomButtonContainer)))),
                        isDisplayed()));
        appCompatButton6.perform(click());

        ViewInteraction appCompatEditText4 = onView(
                allOf(withClassName(is("android.support.v7.widget.AppCompatEditText")),
                        withParent(withId(R.id.textViewContainer))));
        appCompatEditText4.perform(scrollTo(), replaceText("demo@loyagram.com"), closeSoftKeyboard());

        ViewInteraction appCompatButton7 = onView(
                allOf(withText("Next"),
                        withParent(allOf(withId(R.id.btnContainer),
                                withParent(withId(R.id.bottomButtonContainer)))),
                        isDisplayed()));
        appCompatButton7.perform(click());

        ViewInteraction appCompatEditText5 = onView(
                allOf(withClassName(is("android.support.v7.widget.AppCompatEditText")),
                        withParent(withId(R.id.textViewContainer))));
        appCompatEditText5.perform(scrollTo(), replaceText("9947052868"), closeSoftKeyboard());

        ViewInteraction appCompatButton8 = onView(
                allOf(withText("Submit"),
                        withParent(allOf(withId(R.id.btnContainer),
                                withParent(withId(R.id.bottomButtonContainer)))),
                        isDisplayed()));
        appCompatButton8.perform(click());
    }
}
