package com.loyagram.campaignsdkdemo.FromBottom.Nps;

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
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withChild;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class NpsBottomRating2Test {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void npsBottomRating2Test() {
        ViewInteraction appCompatButton = onView(
                allOf(ViewMatchers.withId(R.id.btnFromBottom), withText("Slide From Bottom"),
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
        onView(withId(R.id.ratingView2)).perform(click());
        onView(withId(R.id.txtReason)).perform(typeText("Test"), closeSoftKeyboard());
        Response response = getResponse();
        if(response.getResponseAnswers().size() == 1) {
            if(response.getResponseAnswers().get(0).getResponseAnswerText().getText().equals("Test")) {
                Assert.assertTrue(response.getResponseAnswers().get(0).getValue().equals(new BigDecimal(2)));
            }
        } else{
            Assert.fail("Response mismatch");
        }
        onView(withId(R.id.btnContainer)).check(matches(withChild(withText("Submit")))).perform(click());
        Response responseAfterSubmit = getResponse();
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
