package com.loyagram.campaignsdkdemo.Activity.Survey;


import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.google.gson.Gson;
import com.loyagram.android.campaignsdk.models.Response;
import com.loyagram.campaignsdkdemo.MainActivity;
import com.loyagram.campaignsdkdemo.R;

import junit.framework.Assert;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.math.BigDecimal;

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
public class SurveryActivitySingleSelTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void surveryActivitySingleSelTest() {
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

        ViewInteraction appCompatRadioButton7 = onView(
                withText("walking dead"));
        appCompatRadioButton7.perform(scrollTo(), click());

        appCompatButton4.perform(click());
        appCompatButton4.perform(click());
        appCompatButton4.perform(click());

        Response response= getResponse();
        if(response.getResponseAnswers().size() == 2) {
            if(response.getResponseAnswers().get(0).getValue().equals(new BigDecimal(6))) {
                if(! response.getResponseAnswers().get(1).getQuestionLabelId().equals(new BigDecimal(1390))) {
                    Assert.fail("Response mismatch");
                }
            } else {
                Assert.fail("Response mismatch");
            }
        } else {
            Assert.fail("Response mismatch");
        }
        ViewInteraction appCompatButton5 = onView(
                allOf(withText("Submit"),
                        withParent(allOf(withId(R.id.btnContainer),
                                withParent(withId(R.id.bottomButtonContainer)))),
                        isDisplayed()));
        appCompatButton5.perform(click());
        Response responseAfterSubmit= getResponse();
        if(responseAfterSubmit != null) {
            Assert.fail("Response is not null after submit");
        }
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
        return "LoyagramSdk";
    }

}
