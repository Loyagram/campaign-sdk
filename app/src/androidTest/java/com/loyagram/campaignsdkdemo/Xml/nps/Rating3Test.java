package com.loyagram.campaignsdkdemo.Xml.nps;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.ViewInteraction;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.google.gson.Gson;
import com.loyagram.android.campaignsdk.models.Response;
import com.loyagram.campaignsdkdemo.MainActivity;
import com.loyagram.campaignsdkdemo.R;

import junit.framework.Assert;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.math.BigDecimal;

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
public class Rating3Test {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void npsActivityRating3Test() {
        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.fromXml), withText("Show From xml"),
                        childAtPosition(
                                allOf(withId(R.id.buttoncontainer),
                                        childAtPosition(
                                                withId(R.id.mainContianer),
                                                1)),
                                2),
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

        ViewInteraction textView = onView(
                allOf(withId(R.id.ratingView3), withText("3"),
                        childAtPosition(
                                allOf(withId(R.id.ratingContainer),
                                        childAtPosition(
                                                withId(R.id.topRatingContainer),
                                                0)),
                                3),
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

        ViewInteraction appCompatCheckBox = onView(
                allOf(withText("Features"),
                        childAtPosition(
                                allOf(withId(R.id.optionsContainer),
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

        Response response = getResponse();
        if(response.getResponseAnswers().size() == 2) {
            if(response.getResponseAnswers().get(0).getResponseAnswerText().getText().equals("test")) {
                Assert.assertTrue(response.getResponseAnswers().get(0).getValue().equals(new BigDecimal(3)));
            }
        } else{
            Assert.fail("Response mismatch");
        }

        ViewInteraction appCompatButton5 = onView(
                allOf(withText("Submit"),
                        withParent(allOf(withId(R.id.btnContainer),
                                withParent(withId(R.id.bottomButtonContainer)))),
                        isDisplayed()));
        appCompatButton5.perform(click());
        Response responseAfterSubmit = getResponse();
        if(responseAfterSubmit != null) {
            Assert.fail("Response is not null after submit");
        }


        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction button = onView(
                allOf(withId(R.id.btnExit), withText("Exit Campaign"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.FrameLayout")),
                                        0),
                                0),
                        isDisplayed()));
        button.perform(click());

    }
    public Context getContext() {
        return InstrumentationRegistry.getTargetContext();
    }

    public Response getResponse() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        Gson gson = new Gson();
        String prefName = getAppname() + "response";
        String json = sharedPreferences.getString(prefName, "");
        return gson.fromJson(json, Response.class);
    }

    private static String getAppname() {
        return "LoyagramCampaignSdkDemo";
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
