package com.loyagram.campaignsdkdemo.Dialog.nps;

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
import static android.support.test.espresso.action.ViewActions.pressImeActionButton;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class Rating2Test {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void npsActivityRating2Test() {


        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.btnDialog), withText("Show as Dialog"),
                        withParent(allOf(withId(R.id.buttoncontainer),
                                withParent(withId(R.id.mainContianer)))),
                        isDisplayed()));
        appCompatButton.perform(click());

        ViewInteraction appCompatButton2 = onView(
                allOf(withText("Start"),
                        withParent(withId(R.id.widgetContainerMain)),
                        isDisplayed()));
        appCompatButton2.perform(click());

        ViewInteraction textView = onView(
                allOf(withId(R.id.ratingView2), withText("2"),
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

        ViewInteraction appCompatCheckBox = onView(
                allOf(withText("Features"),
                        withParent(withId(R.id.optionsContainer))));
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
        appCompatEditText2.perform(scrollTo(), replaceText("test@loyagram.com"), closeSoftKeyboard());

        ViewInteraction appCompatEditText3 = onView(
                allOf(withId(R.id.txtEmail), withText("test@loyagram.com"),
                        withParent(allOf(withId(R.id.emailFollowUpContainer),
                                withParent(withId(R.id.reasonFooter))))));
        appCompatEditText3.perform(pressImeActionButton());

        Response response = getResponse();
        if(response.getResponseAnswers().size() == 2) {
            if(response.getResponseAnswers().get(0).getResponseAnswerText().getText().equals("test")) {
                Assert.assertTrue(response.getResponseAnswers().get(0).getValue().equals(new BigDecimal(2)));
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
}
