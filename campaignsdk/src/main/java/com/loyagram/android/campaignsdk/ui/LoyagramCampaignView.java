package com.loyagram.android.campaignsdk.ui;


import android.app.Activity;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatSpinner;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.URLSpan;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loyagram.android.campaignsdk.globals.LoyagramAttributes;
import com.loyagram.android.campaignsdk.globals.QuestionStatus;
import com.loyagram.android.campaignsdk.R;
import com.loyagram.android.campaignsdk.adapter.SpinnerAdapter;
import com.loyagram.android.campaignsdk.asynctask.AsyncTaskAuth;
import com.loyagram.android.campaignsdk.asynctask.AsyncTaskGetImage;
import com.loyagram.android.campaignsdk.asynctask.AsyncTaskSendResponse;
import com.loyagram.android.campaignsdk.campaignbase.LoyagramCampaignManager;
import com.loyagram.android.campaignsdk.campaigncallback.CampaignCallback;
import com.loyagram.android.campaignsdk.models.Campaign;
import com.loyagram.android.campaignsdk.models.ErrorModel;
import com.loyagram.android.campaignsdk.models.Language;
import com.loyagram.android.campaignsdk.models.LanguageBase;
import com.loyagram.android.campaignsdk.models.Question;
import com.loyagram.android.campaignsdk.models.QuestionLabel;
import com.loyagram.android.campaignsdk.models.Response;
import com.loyagram.android.campaignsdk.models.ResponseAnswer;
import com.loyagram.android.campaignsdk.models.StaticTextTransalation;
import com.loyagram.android.campaignsdk.models.ThankYouTranslation;
import com.loyagram.android.campaignsdk.models.WelcomeTranslation;
import com.loyagram.android.campaignsdk.models.npssettings.CustomThankYouAndRedirectSettings;
import com.loyagram.android.campaignsdk.models.npssettings.Link;
import com.loyagram.android.campaignsdk.models.npssettings.ThankYouAndRedirectSettings;
import com.loyagram.android.campaignsdk.restapi.APIResultCallback;
import com.loyagram.android.campaignsdk.restapi.ApiBase;

import org.json.JSONObject;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import android.os.Handler;

import static android.view.Gravity.CENTER;


/**
 * Base class for the campaign view. Loads different types of campaigns based on the question type.
 */

@SuppressWarnings("SpellCheckingInspection")
public class LoyagramCampaignView extends LinearLayout {


    public interface LanguageChangeListener {
        void onLanguageChanged(Language language);

    }

    public interface SubmitPressListener {
        void onSubmitPressed(int iterator);

        void onPreviousPressed(int iterator);
    }

    Campaign campaign;
    Response response;
    SharedPreferences sharedPreferences;
    List<Question> questionList;
    LinearLayout llWidgetcontainer;
    LinearLayout llWidgetcontainerMain;
    RelativeLayout rrbottomButtonContainer;
    RelativeLayout rrCamapignView;
    RelativeLayout rrCampaignHeader;
    ImageView imageViewLogo;
    TextView questionCount;
    TextView txtFooterCredit;
    TextView txtBrandName;
    Context currentContext;
    RelativeLayout spinnerContainer;
    AppCompatSpinner spinnerLang;
    AppCompatButton btnNext;
    AppCompatButton btnPrev;
    ImageView btnExit;
    Question currentQuestion = null;
    int questionNumber = -1;
    int noOfQuestions = 0;
    ProgressBar progressBar;
    DialogFragment campaignDialog = null;
    String colorPrimary = null;
    View campaignLauncher = null;
    ViewGroup campaignContainer = null;
    LinearLayout llActivityBg = null;
    CampaignCallback listener;
    LanguageChangeListener languageChangeListener = null;
    SubmitPressListener submitPressListener = null;
    AppCompatButton btnStartCampaign;
    Boolean isRepeatable = false;
    Boolean isPreview = false;
    Boolean isFollowUpEnabled = false;
    Language currentLanguage;
    int npsRating = 0;
    Typeface typeFace;
    String token;
    AlertDialog dialog = null;
    ScrollView scrollView;
    BigDecimal locationId;
    HashMap<String, String> customAttributes = null;
    TextView txtWelcomeMessage;
    HashMap<String, String> staticTextes = new HashMap<>();
    Boolean isSpinnerChanged = false;
    Language primaryLanguage;
    String csatCesOption;
    int followUpIterator = 0;

    /**
     * enumarator to define type of campaign view selected
     */
    private enum campignWidgetType {
        ACTIVITY,
        DIALOG,
        VIEW,
        FROMXML
    }

    int widgetType = -1;

    /**
     * constructor without Attribute set and style
     *
     * @param context Context of the applciation
     */
    public LoyagramCampaignView(Context context) {
        super(context);
        init(context);
        getAppPrimaryColor();
        setTheme();
    }

    @SuppressWarnings("unused")
    public LoyagramCampaignView(Context context, Boolean isRepeatable) {
        super(context);
        init(context);
    }

    /**
     * @param context application context
     * @param attr    Attribute set which includes custom attributes as well like log, theme and campaign Id.
     */
    public LoyagramCampaignView(Context context, AttributeSet attr) {
        super(context, attr);
        init(context);
        getAttributes(context, attr);
        getAppPrimaryColor();
        setTheme();
    }

    /**
     * @param context  Application context
     * @param attr     Atributes set
     * @param defStyle custom style
     */
    public LoyagramCampaignView(Context context, AttributeSet attr, int defStyle) {
        super(context, attr, defStyle);
        init(context);
        getAppPrimaryColor();
        setTheme();
    }

    /**
     * Initialize campaign call back listener
     *
     * @param campaignCallback callback object
     */
    public void setLoyagramCampaingListener(CampaignCallback campaignCallback) {
        this.listener = campaignCallback;
    }

    /**
     * Sets campaign language change listener.
     *
     * @param languageChangeListener listner
     */
    public void setLanguageChangeListener(LanguageChangeListener languageChangeListener) {
        this.languageChangeListener = languageChangeListener;
    }

    public void setSubmitPressListener(SubmitPressListener submitPressListener) {
        this.submitPressListener = submitPressListener;
    }

    /**
     * Methods to get the Attribiute set defined via XML.
     * Custom attributes like logo theme color and campaign Id may be included.
     *
     * @param context      Application context
     * @param attributeSet custom attribute set
     */
    public void getAttributes(Context context, AttributeSet attributeSet) {
        TypedArray typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.LoyagramCampaignView);
        this.colorPrimary = typedArray.getText(R.styleable.LoyagramCampaignView_campaignColor).toString();
        CharSequence campaignId = typedArray.getText(R.styleable.LoyagramCampaignView_campaignId);
        typedArray.recycle();
        widgetType = 3;

        //Get campaign from preference only if no internet

        if (!ApiBase.isEverythingOK(context)) {
            Campaign campaign = LoyagramCampaignManager.getCampaignFromPreference(campaignId.toString(), context);
            if (campaign != null) {
                setCampaign(campaign);
            } else {
                showSubView(false);
                Toast.makeText(context, "No active internet connection.",
                        Toast.LENGTH_SHORT).show();
            }
        } else {
            showProgress();
            LoyagramCampaignManager.requestCampaignFromServer(context, campaignId.toString(), this);
        }
        //Old code
      /*  Campaign campaign = LoyagramCampaignManager.getCampaignFromPreference(campaignId.toString(), context);
        if (campaign == null) {
            if (!ApiBase.isEverythingOK(context)) {
                showSubView(false);
                Toast.makeText(context, "No active internet connection.",
                        Toast.LENGTH_SHORT).show();
            } else {
                showProgress();
                LoyagramCampaignManager.requestCampaignFromServer(context, campaignId.toString(), this);
            }
        } else {
            setCampaign(campaign);
        } */
    }

    /**
     * Hide the content view while laoding
     */
    public void hideSubView() {
        questionCount.setVisibility(INVISIBLE);
        imageViewLogo.setVisibility(INVISIBLE);
        btnNext.setVisibility(GONE);
        rrCampaignHeader.setVisibility(INVISIBLE);
        txtFooterCredit.setVisibility(INVISIBLE);

    }

    /**
     * shows campaign view once the campaign request is completed
     *
     * @param status true if campaign is loaded from server otherwise false
     */
    public void showSubView(Boolean status) {
        if (status) {
            String campaignType = campaign.getType();
            btnNext.setVisibility(VISIBLE);
            if (campaignType.equals("SURVEY")) {
                questionCount.setVisibility(VISIBLE);
            }
        } else {
            if (campaign != null) {
                LanguageBase languageBase = campaign.getLanguageBase();
                final List<Language> languages = languageBase.getLanguage();
                if (languages.size() > 1) {
                    spinnerContainer.setVisibility(VISIBLE);
                }
            }

            txtFooterCredit.setVisibility(VISIBLE);
        }
        imageViewLogo.setVisibility(VISIBLE);
        rrCampaignHeader.setVisibility(VISIBLE);
    }

    /**
     * Initialize the layouts in the campaignview
     *
     * @param context Appliaction context
     */
    public void init(Context context) {
        currentContext = context;
        View.inflate(context, R.layout.loyagram_campaignview, this);
        llWidgetcontainer = (LinearLayout) findViewById(R.id.widgetContainer);
        llWidgetcontainerMain = (LinearLayout) findViewById(R.id.widgetContainerMain);
        rrbottomButtonContainer = (RelativeLayout) findViewById(R.id.bottomButtonContainer);
        questionCount = (TextView) findViewById(R.id.qstnCount);
        rrCamapignView = (RelativeLayout) findViewById(R.id.campaignView);
        rrCampaignHeader = (RelativeLayout) findViewById(R.id.campaingHeader);
        txtFooterCredit = (TextView) findViewById(R.id.footerCredits);
        imageViewLogo = (ImageView) findViewById(R.id.logo);
        spinnerLang = (AppCompatSpinner) findViewById(R.id.spinnerLang);
        spinnerContainer = (RelativeLayout) findViewById(R.id.spinnerContainer);
        btnExit = (ImageView) findViewById(R.id.closeButton);
        txtBrandName = (TextView) findViewById(R.id.brandName);
        initCampaignButton();
        initCampaignStartButton();
        hideSubView();
        initLayouts();
        setFont();
    }

    public void initLayouts() {
        this.setFocusableInTouchMode(true);
        this.requestFocus();
        overrideBackPress();
        setButtonListeners();
    }

    public void setRepeatMode(Boolean repeatMode, BigDecimal locationId) {
        this.isRepeatable = repeatMode;
        if (locationId != null) {
            this.locationId = locationId;
        }
        if (repeatMode) {
            btnExit.setVisibility(INVISIBLE);
        }

    }

    public void setPreviewMode(Boolean previewMode) {
        this.isPreview = previewMode;
        if (previewMode) {
            btnExit.setVisibility(INVISIBLE);
        }
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setCustomAtributes(HashMap<String, String> attr) {
        this.customAttributes = attr;
    }

    /**
     * Initialize campaign question navigation buttons
     */
    public void initCampaignButton() {
        LinearLayout btnContainer = (LinearLayout) findViewById(R.id.btnContainer);
        btnPrev = new AppCompatButton(currentContext);
        btnNext = new AppCompatButton(currentContext);
        btnNext.setVisibility(GONE);
        btnPrev.setVisibility(GONE);
        btnPrev.setAllCaps(false);
        btnNext.setAllCaps(false);
        btnPrev.setGravity(CENTER);
        btnNext.setGravity(CENTER);
        int pxButtonHeight = (int) getResources().getDimension(R.dimen.button_height);
        int pxButtonWidth = (int) getResources().getDimension(R.dimen.navbutton_width);
        btnPrev.setBackgroundResource(R.drawable.lg_surveybuttons);
        btnNext.setBackgroundResource(R.drawable.lg_surveybuttons);
        LinearLayout.LayoutParams prevParams = new LinearLayout.LayoutParams(pxButtonWidth, pxButtonHeight);
        prevParams.gravity = CENTER;
        float scale = getContext().getResources().getDisplayMetrics().density;
        int btnRightLeftMargin = (int) (20 * scale + 0.5f);
        prevParams.setMargins(0, 0, btnRightLeftMargin, 0);
        LinearLayout.LayoutParams nextParams = new LinearLayout.LayoutParams(pxButtonWidth, pxButtonHeight);
        nextParams.gravity = CENTER;
        btnPrev.setLayoutParams(prevParams);
        btnNext.setLayoutParams(nextParams);
        btnContainer.addView(btnPrev);
        btnContainer.addView(btnNext);
    }

    /**
     * Initialize start campaign button.
     */
    public void initCampaignStartButton() {
        btnStartCampaign = new AppCompatButton(currentContext);
        int pxButtonHeight = (int) getResources().getDimension(R.dimen.button_height);
        int pxButtonWidth = (int) getResources().getDimension(R.dimen.button_width);
        int pxPadding = (int) getResources().getDimension(R.dimen.button_padding);
        btnStartCampaign.setAllCaps(false);
        btnStartCampaign.setTextColor(Color.parseColor("#FFFFFF"));
        btnStartCampaign.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
        btnStartCampaign.setPadding(pxPadding, pxPadding, pxPadding, pxPadding);
        btnStartCampaign.setBackgroundResource(R.drawable.lg_surveybuttons);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(pxButtonWidth, pxButtonHeight);
        params.gravity = CENTER;
        btnStartCampaign.setLayoutParams(params);
        btnStartCampaign.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (noOfQuestions > 0) {
                    btnStartCampaign.setVisibility(GONE);
                    if (txtWelcomeMessage != null) {
                        txtWelcomeMessage.setVisibility(GONE);
                    }
                    rrbottomButtonContainer.setVisibility(VISIBLE);
                    llWidgetcontainer.setVisibility(VISIBLE);
                    questionNumber = 1;
                    showQuestion(true);
                    QuestionStatus.getInstance().setQuestionNumber(questionNumber);
                    QuestionStatus.getInstance().setCampaignId(campaign.getStringId());
                } else {
                    showAlert("No questions found for this Campaign");
                }
            }
        });
    }

    /**
     *
     */
    public void initWelcomeTextView() {
        txtWelcomeMessage = new TextView(currentContext);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        params.gravity = CENTER;
        params.setMargins(20, 20, 20, 50);
        txtWelcomeMessage.setLayoutParams(params);
        txtWelcomeMessage.setTypeface(typeFace);
        txtWelcomeMessage.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
        txtWelcomeMessage.setGravity(Gravity.CENTER);
    }

    /**
     * Override the theme specified by the user
     */
    public void setTheme() {
        if (colorPrimary != null) {
            int color = Color.parseColor(colorPrimary);
            int stroke = getResources().getDimensionPixelSize(R.dimen.stroke_width);
            ((GradientDrawable) rrCampaignHeader.getBackground()).setStroke(0, color);
            ((GradientDrawable) rrCampaignHeader.getBackground()).setColor(color);
            ((GradientDrawable) btnNext.getBackground()).setStroke(stroke, color);
            ((GradientDrawable) btnNext.getBackground()).setColor(Color.parseColor("#FFFFFF"));
            ((GradientDrawable) btnPrev.getBackground()).setColor(Color.parseColor("#FFFFFF"));
            ((GradientDrawable) btnPrev.getBackground()).setStroke(stroke, color);
            ((GradientDrawable) btnStartCampaign.getBackground()).setColor(Color.parseColor(colorPrimary));
            ((GradientDrawable) btnStartCampaign.getBackground()).setStroke(stroke, Color.parseColor(colorPrimary));
            btnPrev.setTextColor(color);
            btnNext.setTextColor(color);
            ((GradientDrawable) txtFooterCredit.getBackground()).setColor(color);
            ((GradientDrawable) txtFooterCredit.getBackground()).setStroke(0, color);

            //Set status bar color same as theme color for in-store campaign
            if (isPreview || isRepeatable) {
                if (Build.VERSION.SDK_INT > 20) {
                    Activity activity = (Activity) currentContext;
                    Window window = activity.getWindow();
                    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                    window.setStatusBarColor(Color.parseColor(colorPrimary));
                }
            }
        }
    }

    /**
     * sets Listeners for various campaign buttons
     */
    public void setButtonListeners() {
        btnPrev.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                showPreviousQuestion();
            }
        });
        btnExit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                exitCampaign();
            }
        });
        btnNext.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                showNextQuestion();
            }
        });
    }

    /**
     * @param colorPrimary sets the theme color of the campaign
     */
    public void setColorPrimary(String colorPrimary) {
        this.colorPrimary = colorPrimary;
        setTheme();
    }

    public void getAppPrimaryColor() {
        if (this.colorPrimary == null) {
            TypedValue typedValue = new TypedValue();
            TypedArray a = currentContext.obtainStyledAttributes(typedValue.data, new int[]{R.attr.colorPrimary});
            int color = a.getColor(0, 0);
            a.recycle();
            this.colorPrimary = String.format("#%06X", 0xFFFFFF & color);
        }
    }

    /**
     * @param lauchButton       campaing launch Button
     * @param campaignContainer container view for campaign
     */
    public void initAnimation(View lauchButton, ViewGroup campaignContainer) {
        this.campaignLauncher = lauchButton;
        this.campaignContainer = campaignContainer;

    }

    /**
     * Shows either single choice surbey or multiple choice based on the question type.
     *
     * @param context Application context
     */
    public void showSurveyView(Context context, Boolean isFromRight) {

        LoyagramSurveyView loyagramSurveyView = new LoyagramSurveyView(context, currentQuestion, response, this, colorPrimary, currentLanguage, primaryLanguage);
        llWidgetcontainer.addView(loyagramSurveyView);
        animateContentView(isFromRight);
        loyagramSurveyView.setSurveyListener(new LoyagramSurveyView.LoyagramSurveyListener() {
            @Override
            public void onSurveyBackPress() {
                if (isRepeatable && campaign != null) {
                    showPasswordView();

                } else {
                    exitCampaign();
                }
            }

            @Override
            public void onSurveySubmit(Response resp) {
                saveResponseToPreference();
            }

        });
    }

    /**
     * Shows the NPS Widget
     *
     * @param context      Application context
     * @param campaignType Type of campaign.
     */
    public void showNPSView(Context context, String campaignType, Boolean isFromRight) {

        Question followUpQuestion = null;
        isFollowUpEnabled = campaign.getLanguageBase().getFollowUpEnabled();
        if (!campaign.getType().equals("SURVEY") && isFollowUpEnabled) {
            followUpQuestion = campaign.getResults().get(1);
            btnNext.setText(staticTextes.get("CAMPAIGN_MODE_NEXT_BUTTON_TEXT"));
        }
        LoyagramNPSView loyagramNPSView = new LoyagramNPSView(context, campaignType, currentQuestion, followUpQuestion, isFollowUpEnabled, response, this, colorPrimary, currentLanguage, staticTextes, primaryLanguage);
        llWidgetcontainer.addView(loyagramNPSView);
        animateContentView(isFromRight);
        loyagramNPSView.setNPSListener(new LoyagramNPSView.LoyagramNPSListener() {
            @Override
            public void onNPSBackPress() {
                if (isRepeatable && campaign != null) {
                    showPasswordView();

                } else {
                    exitCampaign();
                }
            }

            @Override
            public void onNPSSubmit(Boolean isSubmit) {
                saveResponseToPreference();
                if (isSubmit) {
                    submitCampaign();
                }
            }

            @Override
            public void setRating(int rating) {
                npsRating = rating;
                saveResponseToPreference();

            }

            @Override
            public void hideSubmitButton(Boolean show) {
//                if (show) {
//                    btnNext.setVisibility(VISIBLE);
//                } else {
//                    btnNext.setVisibility(GONE);
//                }
            }

        });
    }

    /**
     * Shows Rating View if question type is rating
     *
     * @param context Application context
     */
    public void showRatingView(Context context, Boolean isFromRight) {

        LoyagramRatingView loyagramRatingView = new LoyagramRatingView(context, currentQuestion, response, this, colorPrimary, currentLanguage, primaryLanguage);
        llWidgetcontainer.addView(loyagramRatingView);
        animateContentView(isFromRight);
        loyagramRatingView.setRatingListener(new LoyagramRatingView.LoyagramRatingListener() {
            @Override
            public void onRatingBackPress() {
                if (isRepeatable && campaign != null) {
                    showPasswordView();
                } else {
                    exitCampaign();
                }
            }

            @Override
            public void onRatingSubmit(Response res) {
                saveResponseToPreference();
            }
        });
    }

    /**
     * shows text Type View
     *
     * @param context Application context
     */
    public void showTextView(Context context, Boolean isFromRight) {

        LoyagramTextView loyagramTextView = new LoyagramTextView(context, currentQuestion, response, this, colorPrimary, currentLanguage, primaryLanguage);
        llWidgetcontainer.addView(loyagramTextView);
        animateContentView(isFromRight);
        loyagramTextView.setTextViewListener(new LoyagramTextView.LoyagramTextviewListener() {
            @Override
            public void onTextviewBackPress() {
                if (isRepeatable && campaign != null) {
                    showPasswordView();
                } else {
                    exitCampaign();
                }
            }

            @Override
            public void onTextviewSubmit() {
                saveResponseToPreference();
            }
        });
    }


    /**
     * shows text Type View
     *
     * @param context Application context
     */
    public void showCSATCES(Context context, Boolean isFromRight, Boolean isCsat) {

        Question followUpQuestion = null;
        isFollowUpEnabled = campaign.getLanguageBase().getFollowUpEnabled();
        if (!campaign.getType().equals("SURVEY") && isFollowUpEnabled) {
            followUpQuestion = campaign.getResults().get(1);
            btnNext.setText(staticTextes.get("CAMPAIGN_MODE_NEXT_BUTTON_TEXT"));
        }
        LoyagramCSATCESView loyagramCSATCESView = new LoyagramCSATCESView(context, currentQuestion, followUpQuestion, isFollowUpEnabled, response, this, colorPrimary, currentLanguage, primaryLanguage, isCsat, staticTextes);
        llWidgetcontainer.addView(loyagramCSATCESView);
        animateContentView(isFromRight);
        loyagramCSATCESView.setCSATCESListener(new LoyagramCSATCESView.LoyagramCSATCESListener() {
            @Override
            public void onCSATCESBackPress() {
                if (isRepeatable && campaign != null) {
                    showPasswordView();
                } else {
                    exitCampaign();
                }
            }

            @Override
            public void setOptions(String option) {
                csatCesOption = option;
            }

            @Override
            public void hideSubmitButton(Boolean show) {
                if (show) {
                    btnNext.setVisibility(GONE);
                } else {
                    btnNext.setVisibility(VISIBLE);
                }
            }

            @Override
            public void onCSATCESSubmit(Boolean show) {
                saveResponseToPreference();
            }
        });
    }


    /**
     * Loads next question. checks whether questiojn is optional or not.
     */
    public void showNextQuestion() {
        if (!currentQuestion.getOptional()) {
            if (!questionAttended(currentQuestion)) {
                showAlertDialog("Please attend the current question");
                return;
            }
        }

        ((GradientDrawable) btnPrev.getBackground()).setColor(Color.parseColor("#FFFFFF"));
        btnPrev.setTextColor(Color.parseColor(colorPrimary));
        ((GradientDrawable) btnNext.getBackground()).setColor(Color.parseColor(colorPrimary));
        btnNext.setTextColor(Color.parseColor("#FFFFFF"));

        /* Handle Follow up for NPS CSAT and CES */
        if (!campaign.getType().equals("SURVEY") && isFollowUpEnabled) {
            switch (followUpIterator) {
                case 0:
                    if (submitPressListener != null) {
                        submitPressListener.onSubmitPressed(followUpIterator);
                    }
                    btnPrev.setVisibility(VISIBLE);
                    followUpIterator++;
                    break;
                case 1:
                    if (submitPressListener != null) {
                        submitPressListener.onSubmitPressed(followUpIterator);
                    }
                    btnNext.setText(staticTextes.get("CAMPAIGN_MODE_SUBMIT_BUTTON_TEXT"));
                    followUpIterator++;
                    break;
                case 2:
                    followUpIterator = 0;
                    submitCampaign();
            }
            return;
        }
        questionNumber++;
        if (btnNext.getText().equals(staticTextes.get("CAMPAIGN_MODE_SUBMIT_BUTTON_TEXT"))) {
            submitCampaign();
            return;
        }
        if (questionNumber == noOfQuestions) {
            btnNext.setText(staticTextes.get("CAMPAIGN_MODE_SUBMIT_BUTTON_TEXT"));
        }
        if (questionNumber == 2) {
            btnPrev.setVisibility(VISIBLE);
        }
        QuestionStatus.getInstance().setQuestionNumber(questionNumber);
        QuestionStatus.getInstance().setCampaignId(campaign.getStringId());
        showQuestion(true);
    }

    /**
     * Loads previous question
     */
    public void showPreviousQuestion() {

        ((GradientDrawable) btnPrev.getBackground()).setColor(Color.parseColor(colorPrimary));
        btnPrev.setTextColor(Color.parseColor("#FFFFFF"));
        ((GradientDrawable) btnNext.getBackground()).setColor(Color.parseColor("#FFFFFF"));
        btnNext.setTextColor(Color.parseColor(colorPrimary));
        /* Handle Follow up for NPS CSAT and CES */
        if (!campaign.getType().equals("SURVEY") && isFollowUpEnabled) {
            switch (followUpIterator) {
                case 0:
                    break;
                case 1:
                    if (submitPressListener != null) {
                        submitPressListener.onPreviousPressed(followUpIterator);
                    }
                    btnPrev.setVisibility(GONE);
                    break;
                case 2:
                    if (submitPressListener != null) {
                        submitPressListener.onPreviousPressed(followUpIterator);
                    }
                    btnNext.setText(staticTextes.get("CAMPAIGN_MODE_NEXT_BUTTON_TEXT"));
                    break;
            }
            followUpIterator--;
            return;
        }


        if (questionNumber == 2) {
            btnPrev.setVisibility(GONE);
        }
        if (questionNumber == noOfQuestions) {
            // btnNext.setText("Next");
            btnNext.setText(staticTextes.get("CAMPAIGN_MODE_NEXT_BUTTON_TEXT"));
        }
        questionNumber--;
        QuestionStatus.getInstance().setQuestionNumber(questionNumber);
        QuestionStatus.getInstance().setCampaignId(campaign.getStringId());
        showQuestion(false);

    }

    /**
     * Exits campaign
     */
    public void exitCampaign() {
        llWidgetcontainer.removeAllViews();
        QuestionStatus.getInstance().resetQuestionStats();
        if (widgetType > -1) {
            campignWidgetType viewtype = campignWidgetType.values()[widgetType];
            switch (viewtype) {
                case ACTIVITY:
                    Activity activity = (Activity) currentContext;
                    if (activity != null) {
                        activity.finish();
                    }
                    break;
                case DIALOG:
                    if (campaignDialog != null) {
                        campaignDialog.dismiss();
                    }
                    break;
                case VIEW:
                    if (campaignContainer != null && campaignLauncher != null) {
                        campaignContainer.removeView(LoyagramCampaignView.this);
                        campaignLauncher.setVisibility(VISIBLE);
                    }
                    break;
                case FROMXML:
                    int id = this.getId();
                    LinearLayout v = (LinearLayout) findViewById(id);
                    ViewGroup vg = (ViewGroup) findViewById(id).getParent();
                    if (v != null && vg != null) {
                        vg.removeView(v);
                    }

            }
        }
    }

    public void showAlert(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * Methods to load campaign question based on the question type.
     */
    public void showQuestion(Boolean isFromRight) {

        llWidgetcontainer.removeAllViews();
        questionCount.setText(questionNumber + "/" + noOfQuestions);
        currentQuestion = questionList.get(questionNumber - 1);
        String campaignType = campaign.getType();
        if (campaignType.equalsIgnoreCase("CSAT")) {
            showCSATCES(currentContext, isFromRight, true);
        } else if (campaignType.equalsIgnoreCase("CES")) {
            showCSATCES(currentContext, isFromRight, false);
        } else {
            if (currentQuestion != null) {
                String questiontype = currentQuestion.getType();
                switch (questiontype) {
                    case "SINGLE_SELECT":
                        showSurveyView(currentContext, isFromRight);
                        break;
                    case "MULTI_SELECT":
                        showSurveyView(currentContext, isFromRight);
                        break;
                    case "RATING":
                        showRatingView(currentContext, isFromRight);
                        break;
                    case "NPS":
                        showNPSView(currentContext, campaignType, isFromRight);
                        break;
                    case "TEXT":
                        showTextView(currentContext, isFromRight);
                        break;
                }
            }
        }
    }

    /**
     * Initialize the response if the response is null
     */
    public void initResponse() {
        response = getResponseFromPreference();
        if (response == null) {
            response = new Response();
            response.setId(UUID.randomUUID().toString());
            response.setSubChannel("ANDROID");
            if (isRepeatable) {
                response.setChannel("KIOSK");
                response.setLocationId(locationId);
            } else {
                HashMap<String, String> customAttr = LoyagramAttributes.getInstance().getAttributes();
                if (customAttr != null && !customAttr.isEmpty()) {
                    if (customAttributes != null) {
                        customAttributes.putAll(customAttr);
                    } else {
                        customAttributes = customAttr;
                    }
                }
                response.setAttributes(customAttributes);
                response.setChannel("MOBILE-SDK");
            }
            response.setBizId(campaign.getBizId());
            response.setCampaignId(campaign.getId());
            response.setStartedAt(System.currentTimeMillis());
            if (response.getResponseAnswers() == null)
                response.setResponseAnswers(new ArrayList<ResponseAnswer>());
            saveResponseToPreference();
        }
    }

    /**
     *
     */
    public void saveResponseToPreference() {
        if (isPreview) {
            return;
        }
        String prefName = getAppname(currentContext) + "response";
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(response);
        prefsEditor.putString(prefName, json);
        prefsEditor.apply();
    }

    /**
     * Method to get response saved in preference
     *
     * @return response saved in preference
     */
    public Response getResponseFromPreference() {
        if (sharedPreferences == null) {
            sharedPreferences = PreferenceManager.getDefaultSharedPreferences(currentContext);
        }
        Gson gson = new Gson();
        String prefName = getAppname(currentContext) + "response";
        String json = sharedPreferences.getString(prefName, "");
        response = gson.fromJson(json, Response.class);
        return response;
    }

    /**
     * sets the campaign object from the http response
     *
     * @param campaign object of Campaign class
     */
    public void setCampaign(Campaign campaign) {
        if (campaign != null) {
            if (campaign.getResults() == null || campaign.getResults().size() < 1) {
                Toast.makeText(currentContext, "No Questions found for the campaign.",
                        Toast.LENGTH_SHORT).show();
                showEmptyView();
                return;
            }

            this.campaign = campaign;
            questionList = campaign.getResults();
            isFollowUpEnabled = campaign.getLanguageBase().getFollowUpEnabled();
            String campaignType = campaign.getType();
//            if (campaignType.matches("NPS|CSAT|CES")) {
//                noOfQuestions = 1;
//            } else {
//                noOfQuestions = questionList.size();
//            }
            noOfQuestions = questionList.size();
            questionNumber = QuestionStatus.getInstance().getQuestionNumber();
            String currentCampaignID = QuestionStatus.getInstance().getCampaignId();
            if (isPreview || isRepeatable) {
                removeResponseFromPreference();
                if (campaign.getColorPriamry() != null) {
                    colorPrimary = campaign.getColorPriamry();
                } else {
                    colorPrimary = "#1abc9c";
                }
                setActivityBgColor();
                setTheme();
            }
            initResponse();
            loadImageFromUrl();
            loadLanguage();
            getStaticTextTransaltions();
            setStaticTextes();
            showSubView(false);
            hideProgress();

            //Handles Orientation change
            if (questionNumber > 0 && campaign.getStringId().equals(currentCampaignID)) {
                if (questionNumber > 1) {
                    btnPrev.setVisibility(VISIBLE);
                }
                btnStartCampaign.setVisibility(GONE);
                rrbottomButtonContainer.setVisibility(VISIBLE);
                llWidgetcontainer.setVisibility(VISIBLE);
                showQuestion(true);
                QuestionStatus.getInstance().setQuestionNumber(questionNumber);
                QuestionStatus.getInstance().setCampaignId(campaign.getStringId());
            } else {
                Boolean isWelcomeEnabled = campaign.getWelcomeEnabled();
                if (isWelcomeEnabled != null && isWelcomeEnabled) {
                    initWelcomeTextView();
                    txtWelcomeMessage.setText(getWelcomeMessage());
                    llWidgetcontainerMain.addView(txtWelcomeMessage);
                    llWidgetcontainerMain.addView(btnStartCampaign);
                    btnStartCampaign.setVisibility(VISIBLE);
                    txtWelcomeMessage.setVisibility(VISIBLE);
                } else if (isRepeatable || isPreview) {
                    llWidgetcontainerMain.addView(btnStartCampaign);
                    btnStartCampaign.setVisibility(VISIBLE);
                } else {
                    btnStartCampaign.setVisibility(GONE);
                    showCampaignWithDelay();
                }
            }
            return;
        }
        showEmptyView();

    }

    /**
     * Empty view if campaign is not loaded
     */
    public void showEmptyView() {
        if (colorPrimary == null) {
            colorPrimary = "#1abc9c";
            setTheme();
            if (isPreview || isRepeatable) {
                llActivityBg.setBackgroundColor(Color.parseColor((colorPrimary)));
            }
        }
        showSubView(false);
        hideProgress();
    }

    /**
     * Shows campaign with a 100 milli seconds delay
     */
    public void showCampaignWithDelay() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (noOfQuestions > 0) {
                    rrbottomButtonContainer.setVisibility(VISIBLE);
                    llWidgetcontainer.setVisibility(VISIBLE);
                    questionNumber = 1;
                    showQuestion(true);
                } else {
                    showAlert("No questions found for this Campaign");
                }
            }

        }, 100);
    }

    public void setCampaignDialog(DialogFragment dialog) {
        campaignDialog = dialog;
    }

    public void setActivityBg(LinearLayout llActivityBg) {
        this.llActivityBg = llActivityBg;
        if (colorPrimary != null) {
            llActivityBg.setBackgroundColor(Color.parseColor((colorPrimary)));
        }
    }

    public void setActivityBgColor() {
        llActivityBg.setBackgroundColor(Color.parseColor((colorPrimary)));
    }

    public void setCampaignType(int value) {
        widgetType = value;
    }

    /**
     * Shows a progress bar while loading
     */
    public void showProgress() {
        progressBar = new ProgressBar(currentContext, null, android.R.attr.progressBarStyleSmall);
        progressBar.bringToFront();
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
        progressBar.setLayoutParams(params);
        rrCamapignView.addView(progressBar);
        String progressColor;
        if (colorPrimary != null) {
            progressColor = colorPrimary;
        } else {
            progressColor = "#999999";
        }
        progressBar.getIndeterminateDrawable().setColorFilter(
                Color.parseColor(progressColor), android.graphics.PorterDuff.Mode.SRC_IN);
    }


    public void submitCampaign() {

//        if (isFollowUpEnabled) {
//            if (submitPressListener != null) {
//                submitPressListener.onSubmitPressed();
//                btnNext.setText(staticTextes.get("CAMPAIGN_MODE_SUBMIT_BUTTON_TEXT"));
//            }
//            isFollowUpEnabled = false;
//            return;
//        }
        btnNext.setVisibility(GONE);
        try {
            QuestionStatus.getInstance().resetQuestionStats();
            btnPrev.setVisibility(GONE);
            questionCount.setVisibility(INVISIBLE);
            showThankyou();
            if (!isPreview) {
                // getResponseFromPreference();
                Gson gson = new Gson();
                response.setEndedAt(System.currentTimeMillis());
                response.setLanguage(currentLanguage.getCode());
                String jsonResponse = gson.toJson(response);
                JSONObject jsonObject = new JSONObject(jsonResponse);
                JSONObject sendJson = new JSONObject();
                sendJson.put("data", jsonObject);
                submitCampaigntoLoyagram(sendJson.toString());
                LoyagramCampaignManager.sendPendingResponses(currentContext);
            }
        } catch (Exception ignored) {
            refreshCampaignButtonStyle();
            if (isRepeatable || isPreview) {
                resetCampaign();
            } else {
                exitCampaign();
            }
        }
    }

    public void refreshCampaignButtonStyle() {
        int stroke = getResources().getDimensionPixelSize(R.dimen.stroke_width);
        ((GradientDrawable) btnPrev.getBackground()).setStroke(stroke, Color.parseColor(colorPrimary));
        ((GradientDrawable) btnNext.getBackground()).setStroke(stroke, Color.parseColor(colorPrimary));
        btnPrev.setTextColor(Color.parseColor(colorPrimary));
        btnNext.setTextColor(Color.parseColor(colorPrimary));
        ((GradientDrawable) btnPrev.getBackground()).setColor(Color.parseColor("#FFFFFF"));
        ((GradientDrawable) btnNext.getBackground()).setColor(Color.parseColor("#FFFFFF"));

    }

    /**
     * Method to send the response answers to the loyagram server
     *
     * @param sendJson Json Data to be send to server
     */
    public void submitCampaigntoLoyagram(String sendJson) {
        AsyncTaskSendResponse asyncTasKRequestQuestion = new AsyncTaskSendResponse();
        asyncTasKRequestQuestion.setListener(new APIResultCallback<Boolean>() {
            @Override
            public void onSuccess(Boolean result) {
                removeResponseFromPreference();
                //initResponse();
                if (listener != null) {
                    listener.onSuccess();
                }
            }

            @Override
            public void onError(List<ErrorModel> errors) {
                if (isRepeatable) {
                    saveFailedResponse();
                    removeResponseFromPreference();
                }
                if (listener != null) {
                    listener.onError();
                }

            }
        });
        asyncTasKRequestQuestion.execute(sendJson);
    }

    /**
     * Hides progress bar once the Widget is loaded completely
     */
    public void hideProgress() {
        if (progressBar != null) {
            rrCamapignView.removeView(progressBar);
            progressBar = null;
        }
    }

    public void overrideBackPress() {
        this.setOnKeyListener(new OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() != KeyEvent.ACTION_DOWN)
                    return true;
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    if (isRepeatable && campaign != null) {
                        showPasswordView();
                    } else {
                        exitCampaign();
                    }
                    return true;
                }
                return false;
            }
        });
    }

    /**
     * Loads logo from server
     */
    public void loadImageFromUrl() {
        //Gson gson = new Gson();
        //String json = gson.toJson(campaign);
        try {
            //JSONObject jsonObject = new JSONObject(json);
            //String bizString = jsonObject.getJSONObject("biz").toString();
            //Biz biz = gson.fromJson(bizString, Biz.class);
            // String logoUrl = biz.getImgUrl();
            String logoUrl = campaign.getLogoUrl();
            String brandName = campaign.getBrandTitle();
            if (brandName != null) {
                txtBrandName.setText(brandName);
            }
            if (logoUrl != null) {
                if (!ApiBase.isEverythingOK(currentContext)) {
                    Toast.makeText(currentContext, "No internet connection.",
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                AsyncTaskGetImage asyncTaskGetImage = new AsyncTaskGetImage(imageViewLogo, txtBrandName);
                asyncTaskGetImage.execute(logoUrl);
            } else {
                txtBrandName.setVisibility(VISIBLE);
            }
        } catch (Exception ex) {
            //Log.e("Exception", Log.getStackTraceString(ex));
        }

    }

    /**
     * resets campaign view once campaign is finished.
     */
    public void resetCampaign() {
        removeResponseFromPreference();
        response = null;
        initResponse();
        llWidgetcontainer.removeAllViews();
        questionCount.setVisibility(INVISIBLE);
        ((GradientDrawable) btnNext.getBackground()).setColor(Color.parseColor("#FFFFFF"));
        int stroke = getResources().getDimensionPixelSize(R.dimen.stroke_width);
        ((GradientDrawable) btnNext.getBackground()).setStroke(stroke, Color.parseColor(colorPrimary));
        btnNext.setTextColor(Color.parseColor(colorPrimary));
        if (noOfQuestions == 1) {
            btnNext.setText(staticTextes.get("CAMPAIGN_MODE_SUBMIT_BUTTON_TEXT"));
        } else {
            btnNext.setText(staticTextes.get("CAMPAIGN_MODE_NEXT_BUTTON_TEXT"));
        }
        rrbottomButtonContainer.setVisibility(GONE);
        llWidgetcontainer.setVisibility(GONE);
        btnStartCampaign.setVisibility(VISIBLE);
        if (txtWelcomeMessage != null) {
            txtWelcomeMessage.setVisibility(VISIBLE);
        }
        questionNumber = -1;
        QuestionStatus.getInstance().resetQuestionStats();
    }

    /**
     * Slides campaign View on Next and Previous Button Clicks
     *
     * @param isFromRight either true or false depending on next or preious button click
     */
    public void animateContentView(Boolean isFromRight) {
        TranslateAnimation slideAniamtion;
        if (isFromRight) {
            slideAniamtion = new TranslateAnimation(
                    Animation.RELATIVE_TO_PARENT, 1f,
                    Animation.RELATIVE_TO_PARENT, 0,
                    Animation.RELATIVE_TO_PARENT, 0,
                    Animation.RELATIVE_TO_PARENT, 0);

        } else {
            slideAniamtion = new TranslateAnimation(
                    Animation.RELATIVE_TO_PARENT, -1f,
                    Animation.RELATIVE_TO_PARENT, 0,
                    Animation.RELATIVE_TO_PARENT, 0,
                    Animation.RELATIVE_TO_PARENT, 0);
        }
        slideAniamtion.setDuration(600);
        slideAniamtion.setFillAfter(true);
        llWidgetcontainer.startAnimation(slideAniamtion);
    }

    /**
     * Loads languages in spinner for the current campaign and sets primary language as current language.
     */
    public void loadLanguage() {
        int loopCounter = 0;
        int spinnerIndex = loopCounter;
        spinnerLang.setSupportBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FFFFFF")));
        LanguageBase languageBase = campaign.getLanguageBase();
        if (languageBase != null) {
            final List<Language> languages = languageBase.getLanguage();
            if (languages != null) {
                if (languages.size() == 1) {
                    this.currentLanguage = languages.get(0);
                    this.primaryLanguage = languages.get(0);
                    return;
                }
                String[] langs = new String[languages.size()];
                int i = 0;
                for (Language lang : languages) {
                    langs[i] = lang.getName();
                    i++;
                    if (lang.getPrimary() != null && lang.getPrimary()) {
                        this.currentLanguage = lang;
                        this.primaryLanguage = lang;
                        spinnerIndex = loopCounter;
                    }
                    loopCounter++;
                }

                spinnerLang.setAdapter(new SpinnerAdapter(currentContext, R.layout.loyagram_spinnertext, langs, colorPrimary, typeFace));
                spinnerLang.setSelection(spinnerIndex);
                spinnerLang.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                        if (position > -1 && isSpinnerChanged) {
                            changeLanguage(languages.get(position));
                        }
                        isSpinnerChanged = true;
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parentView) {
                        // your code here
                    }

                });
            }
        }
    }

    /**
     * Change campaign language when spinner item changes.
     *
     * @param language Currently selected language
     */
    public void changeLanguage(Language language) {
        this.currentLanguage = language;
        getStaticTextTransaltions();
        setStaticTextes();
        if (txtWelcomeMessage != null) {
            txtWelcomeMessage.setText(getWelcomeMessage());
        }
        if (languageChangeListener != null) {
            languageChangeListener.onLanguageChanged(currentLanguage);
        }

    }

    /**
     * Shows thank you once the campaign is completed.
     * Custom thank you messages for NPS
     */
    public void showThankyou() {
        String thankYouString = null;
        //Sets thank you message and redirect links if campaign type is NPS
        Boolean isThankYouEnabled = campaign.getThankYouEnabled();
        HashMap<String, SpannableString> thankYou = new HashMap<>();
        if (isThankYouEnabled != null && isThankYouEnabled) {

            switch (campaign.getType()) {
                case "NPS":
                    thankYou = getNPSThankYou();
                    break;
                case "CSAT":
                    thankYou = getCSATCESThankYou();
                    break;
                case "CES":
                    thankYou = getCSATCESThankYou();
                    break;
                case "SURVEY":
                    thankYou = getSurveyThankYou();
                    break;
            }
            if (thankYou.get("thankyouString") != null) {
                thankYouString = thankYou.get("thankyouString").toString();
            }
               /*
                    scrollView = new ScrollView(currentContext);
                    LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
                    params.gravity = CENTER;
                    scrollView.setLayoutParams(params);
                    scrollView.addView(initNpsThankYouLayout(thankYouString, thankYou.get("links"), 1));
                    llWidgetcontainerMain.addView(scrollView); */
            //break;


        }
           /* final LinearLayout txtThankYou = initNpsThankYouLayout(thankYouString, null, 0);
            llWidgetcontainerMain.addView(txtThankYou);
            final Handler thankyouHandler = new Handler();
            thankyouHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    llWidgetcontainerMain.removeView(txtThankYou);
                    if (isRepeatable || isPreview) {
                        resetCampaign();
                        return;
                    }
                    exitCampaign();
                }
            }, 3000); */


        if (thankYouString != null && !thankYouString.isEmpty()) {
            llWidgetcontainer.removeAllViews();
            rrbottomButtonContainer.setVisibility(GONE);
            llWidgetcontainer.setVisibility(GONE);
            final TextView txtThankYou = new TextView(currentContext);
            LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
            txtThankYou.setGravity(CENTER);
            float scale = getContext().getResources().getDisplayMetrics().density;
            int txtMargin = (int) (5 * scale + 0.5f);
            layoutParams.setMargins(txtMargin, txtMargin, txtMargin, 0);
            txtThankYou.setLayoutParams(layoutParams);
            txtThankYou.setText(thankYouString);
            txtThankYou.setTypeface(typeFace);
            txtThankYou.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
            llWidgetcontainerMain.addView(txtThankYou);
            final Handler thankyouHandler = new Handler();
            thankyouHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    llWidgetcontainerMain.removeView(txtThankYou);
                    if (isRepeatable || isPreview) {
                        resetCampaign();
                        return;
                    }
                    exitCampaign();
                }
            }, 3000);
        } else {
            if (isRepeatable || isPreview) {
                resetCampaign();
                return;
            }
            exitCampaign();
        }
    }


    public HashMap<String, SpannableString> getNPSThankYou() {
        HashMap<String, SpannableString> thankYou = new HashMap<>();
        List<ThankYouTranslation> thankYouTranslations = campaign.getThankYouMessages();
        String thankYouString = null;
        List<Link> links = null;
        SpannableString spannableStringLinks = null;
        String lang = currentLanguage.getCode();
        if (thankYouTranslations != null) {
            for (ThankYouTranslation thankYouTranslation : thankYouTranslations) {
                if (thankYouTranslation.getCode() != null && thankYouTranslation.getCode().equals(lang)) {
                    ThankYouAndRedirectSettings thankYouAndRedirectSettings = thankYouTranslation.getThankYouAndRedirectSettings();
                    if (thankYouAndRedirectSettings != null && thankYouAndRedirectSettings.getType() != null) {
                        switch (thankYouAndRedirectSettings.getType()) {
                            case "all":
                                if (thankYouAndRedirectSettings.getAll() != null) {
                                    thankYouString = thankYouAndRedirectSettings.getAll().getMessage();
                                    // links = thankYouAndRedirectSettings.getAll().getLinks();
                                }
                                break;
                            case "score":
                                CustomThankYouAndRedirectSettings cTY = thankYouAndRedirectSettings.getCustom();
                                if (cTY != null) {
                                    if (npsRating <= 6) {
                                        thankYouString = cTY.getDetractors().getMessage();
                                        //  links = cTY.getDetractors().getLinks();
                                    } else if (npsRating <= 8) {
                                        thankYouString = cTY.getPassives().getMessage();
                                        // links = cTY.getPassives().getLinks();
                                    } else {
                                        thankYouString = cTY.getPromoters().getMessage();
                                        //links = cTY.getPromoters().getLinks();
                                    }
                                }
                                break;
                        }
                    }

                    break;
                }
            }

            //Get thank you from primary language
            if (thankYouString == null || thankYouString.isEmpty()) {
                lang = primaryLanguage.getCode();
                for (ThankYouTranslation thankYouTranslation : thankYouTranslations) {
                    if (thankYouTranslation.getCode() != null && thankYouTranslation.getCode().equals(lang)) {
                        ThankYouAndRedirectSettings thankYouAndRedirectSettings = thankYouTranslation.getThankYouAndRedirectSettings();
                        if (thankYouAndRedirectSettings != null && thankYouAndRedirectSettings.getType() != null) {
                            switch (thankYouAndRedirectSettings.getType()) {
                                case "all":
                                    if (thankYouAndRedirectSettings.getAll() != null) {
                                        thankYouString = thankYouAndRedirectSettings.getAll().getMessage();
                                        // links = thankYouAndRedirectSettings.getAll().getLinks();
                                    }
                                    break;
                                case "score":
                                    CustomThankYouAndRedirectSettings cTY = thankYouAndRedirectSettings.getCustom();
                                    if (cTY != null) {
                                        if (npsRating <= 6) {
                                            thankYouString = cTY.getDetractors().getMessage();
                                            // links = cTY.getDetractors().getLinks();
                                        } else if (npsRating <= 8) {
                                            thankYouString = cTY.getPassives().getMessage();
                                            // links = cTY.getPassives().getLinks();
                                        } else {
                                            thankYouString = cTY.getPromoters().getMessage();
                                            //links = cTY.getPromoters().getLinks();
                                        }
                                    }
                                    break;
                            }
                        }
                    }
                }
            }
//        if (links != null && links.size() > 0) {
//            spannableStringLinks = getSpannableString(links);
//        }


        }
        if (thankYouString != null) {
            SpannableString thankYouStringSpannable = new SpannableString(thankYouString);
            thankYou.put("thankyouString", thankYouStringSpannable);
            // thankYou.put("links", spannableStringLinks);
        }
        return thankYou;

    }

    public HashMap<String, SpannableString> getCSATCESThankYou() {
        HashMap<String, SpannableString> thankYou = new HashMap<>();
        List<ThankYouTranslation> thankYouTranslations = campaign.getThankYouMessages();
        String thankYouString = null;
        List<Link> links = null;
        SpannableString spannableStringLinks = null;
        String lang = currentLanguage.getCode();
        if (thankYouTranslations != null) {
            for (ThankYouTranslation thankYouTranslation : thankYouTranslations) {
                if (thankYouTranslation.getCode() != null && thankYouTranslation.getCode().equals(lang)) {
                    ThankYouAndRedirectSettings thankYouAndRedirectSettings = thankYouTranslation.getThankYouAndRedirectSettings();
                    if (thankYouAndRedirectSettings != null && thankYouAndRedirectSettings.getType() != null) {
                        switch (thankYouAndRedirectSettings.getType()) {
                            case "all":
                                if (thankYouAndRedirectSettings.getAll() != null) {
                                    thankYouString = thankYouAndRedirectSettings.getAll().getMessage();
                                    // links = thankYouAndRedirectSettings.getAll().getLinks();
                                }
                                break;
                            case "custom":
                                CustomThankYouAndRedirectSettings cTY = thankYouAndRedirectSettings.getCustom();
                                if (cTY != null) {
                                    switch (csatCesOption) {
                                        case "very_dissatisfied":
                                            thankYouString = cTY.getVeryDissatisfied().getMessage();
                                            //  links = cTY.getVeryDissatisfied().getLinks();
                                            break;
                                        case "somewhat_dissatisfied":
                                            thankYouString = cTY.getSomewhatDissatisfied().getMessage();
                                            // links = cTY.getVeryDissatisfied().getLinks();
                                            break;
                                        case "neither_satisfied_nor_dissatisfied":
                                            thankYouString = cTY.getNeitherSatisfiedNorDissatisfied().getMessage();
                                            //  links = cTY.getVeryDissatisfied().getLinks();
                                            break;
                                        case "somewhat_satisfied":
                                            thankYouString = cTY.getSomewhatSatisfied().getMessage();
                                            //  links = cTY.getVeryDissatisfied().getLinks();
                                            break;
                                        case "very_satisfied":
                                            thankYouString = cTY.getVerySatisfied().getMessage();
                                            // links = cTY.getVeryDissatisfied().getLinks();
                                            break;

                                        case "neither_easy_nor_difficult":
                                            thankYouString = cTY.getNeither().getMessage();
                                            //  links = cTY.getVeryDissatisfied().getLinks();
                                            break;
                                        case "easy":
                                            thankYouString = cTY.getVeryEasy().getMessage();
                                            //  links = cTY.getVeryDissatisfied().getLinks();
                                            break;
                                        case "difficult":
                                            thankYouString = cTY.getVeryDifficult().getMessage();
                                            // links = cTY.getVeryDissatisfied().getLinks();
                                            break;
                                        case "dissatisfied":
                                            thankYouString = cTY.getUnsatisfied().getMessage();
                                            // links = cTY.getVeryDissatisfied().getLinks();
                                            break;
                                        case "neutral":
                                            thankYouString = cTY.getNeutral().getMessage();
                                            // links = cTY.getVeryDissatisfied().getLinks();
                                            break;
                                        case "satisfied":
                                            thankYouString = cTY.getSatisfied().getMessage();
                                            // links = cTY.getVeryDissatisfied().getLinks();
                                            break;
                                        case "disagree":
                                            thankYouString = cTY.getDisagree().getMessage();
                                            // links = cTY.getVeryDissatisfied().getLinks();
                                            break;
                                        case "neither_disagree_or_agree":
                                            thankYouString = cTY.getNeitherDisagreeOrAgree().getMessage();
                                            // links = cTY.getVeryDissatisfied().getLinks();
                                            break;
                                        case "agree":
                                            thankYouString = cTY.getAgree().getMessage();
                                            // links = cTY.getVeryDissatisfied().getLinks();
                                            break;


                                    }

                                }
                        }
                    }
                    break;
                }
            }

            //Get thank you from primary language
            if (thankYouString == null || thankYouString.isEmpty()) {
                lang = primaryLanguage.getCode();
                for (ThankYouTranslation thankYouTranslation : thankYouTranslations) {
                    if (thankYouTranslation.getCode() != null && thankYouTranslation.getCode().equals(lang)) {
                        ThankYouAndRedirectSettings thankYouAndRedirectSettings = thankYouTranslation.getThankYouAndRedirectSettings();
                        if (thankYouAndRedirectSettings != null && thankYouAndRedirectSettings.getType() != null) {
                            switch (thankYouAndRedirectSettings.getType()) {
                                case "all":
                                    if (thankYouAndRedirectSettings.getAll() != null) {
                                        thankYouString = thankYouAndRedirectSettings.getAll().getMessage();
                                        // links = thankYouAndRedirectSettings.getAll().getLinks();
                                    }
                                    break;
                                case "custom":
                                    CustomThankYouAndRedirectSettings cTY = thankYouAndRedirectSettings.getCustom();
                                    if (cTY != null) {
                                        switch (csatCesOption) {
                                            case "very_dissatisfied":
                                                thankYouString = cTY.getVeryDissatisfied().getMessage();
                                                // links = cTY.getVeryDissatisfied().getLinks();
                                                break;
                                            case "somewhat_dissatisfied":
                                                thankYouString = cTY.getSomewhatDissatisfied().getMessage();
                                                // links = cTY.getVeryDissatisfied().getLinks();
                                                break;
                                            case "neither_satisfied_nor_dissatisfied":
                                                thankYouString = cTY.getNeitherSatisfiedNorDissatisfied().getMessage();
                                                //links = cTY.getVeryDissatisfied().getLinks();
                                                break;
                                            case "somewhat_satisfied":
                                                thankYouString = cTY.getSomewhatSatisfied().getMessage();
                                                //links = cTY.getVeryDissatisfied().getLinks();
                                                break;
                                            case "very_satisfied":
                                                thankYouString = cTY.getVerySatisfied().getMessage();
                                                // links = cTY.getVeryDissatisfied().getLinks();
                                                break;
                                            case "neither_easy_nor_difficult":
                                                thankYouString = cTY.getNeither().getMessage();
                                                //  links = cTY.getVeryDissatisfied().getLinks();
                                                break;
                                            case "easy":
                                                thankYouString = cTY.getEasy().getMessage();
                                                //  links = cTY.getVeryDissatisfied().getLinks();
                                                break;
                                            case "difficult":
                                                thankYouString = cTY.getDifficult().getMessage();
                                                // links = cTY.getVeryDissatisfied().getLinks();
                                                break;
                                            case "very_easy":
                                                thankYouString = cTY.getVeryEasy().getMessage();
                                                //  links = cTY.getVeryDissatisfied().getLinks();
                                                break;
                                            case "very_difficult":
                                                thankYouString = cTY.getVeryDifficult().getMessage();
                                                // links = cTY.getVeryDissatisfied().getLinks();
                                                break;

                                        }
                                    }
                            }
                        }
                        break;
                    }
                }
            }
//        if (links != null && links.size() > 0) {
//            spannableStringLinks = getSpannableString(links);
//        }
        }
        if (thankYouString != null) {
            SpannableString thankYouStringSpannable = new SpannableString(thankYouString);
            thankYou.put("thankyouString", thankYouStringSpannable);
            //thankYou.put("links", spannableStringLinks);
        }
        return thankYou;
    }


    public HashMap<String, SpannableString> getSurveyThankYou() {
        HashMap<String, SpannableString> thankYou = new HashMap<>();
        List<ThankYouTranslation> thankYouTranslations = campaign.getThankYouMessages();
        String thankYouString = null;
        List<Link> links = null;
        SpannableString spannableStringLinks = null;
        String lang = currentLanguage.getCode();
        if(thankYouTranslations != null) {
            for (ThankYouTranslation thankYouTranslation : thankYouTranslations) {
                if (thankYouTranslation.getCode() != null && thankYouTranslation.getCode().equals(lang)) {
                    ThankYouAndRedirectSettings thankYouAndRedirectSettings = thankYouTranslation.getThankYouAndRedirectSettings();
                    if (thankYouAndRedirectSettings != null && thankYouAndRedirectSettings.getAll() != null) {
                        thankYouString = thankYouAndRedirectSettings.getAll().getMessage();
                        // links = thankYouAndRedirectSettings.getAll().getLinks();
                    }

                    break;
                }
            }

            //Get thank you from primary language
            if (thankYouString == null || thankYouString.isEmpty()) {
                lang = primaryLanguage.getCode();
                for (ThankYouTranslation thankYouTranslation : thankYouTranslations) {
                    if (thankYouTranslation.getCode() != null && thankYouTranslation.getCode().equals(lang)) {
                        ThankYouAndRedirectSettings thankYouAndRedirectSettings = thankYouTranslation.getThankYouAndRedirectSettings();
                        if (thankYouAndRedirectSettings != null && thankYouAndRedirectSettings.getAll() != null) {
                            thankYouString = thankYouAndRedirectSettings.getAll().getMessage();
                            // links = thankYouAndRedirectSettings.getAll().getLinks();
                        }
                        break;
                    }
                }
            }
//        if (links != null && links.size() > 0) {
//            spannableStringLinks = getSpannableString(links);
//        }
        }
        if (thankYouString != null) {
            SpannableString thankYouStringSpannable = new SpannableString(thankYouString);
            thankYou.put("thankyouString", thankYouStringSpannable);
            // thankYou.put("links", spannableStringLinks);
        }
        return thankYou;
    }

    /**
     * Checks whether current question is attended before going to next question.
     *
     * @param question current question
     * @return true if attended else false
     */
    public Boolean questionAttended(Question question) {
        List<ResponseAnswer> responseAnswers = response.getResponseAnswers();
        Boolean isAttended = false;
        String questionType = question.getType();
        switch (questionType) {
            case "SINGLE_SELECT":
                for (ResponseAnswer responseAnswer : responseAnswers) {
                    if (question.getId().equals(responseAnswer.getQuestionId())) {
                        if (responseAnswer.getValue().compareTo(BigDecimal.ZERO) > 0) {
                            isAttended = true;
                            break;
                        }
                    }
                }
                break;

            case "MULTI_SELECT":
                Boolean isChecked = false;
                for (QuestionLabel questionLabel : question.getLabels()) {
                    for (ResponseAnswer responseAnswer : responseAnswers) {
                        if (questionLabel.getId().equals(responseAnswer.getQuestionLabelId())) {
                            if (responseAnswer.getValue().compareTo(BigDecimal.ZERO) > 0) {
                                isChecked = true;
                                break;
                            }
                        }
                    }
                    if (isChecked) {
                        break;
                    }
                }
                if (isChecked) {
                    isAttended = true;
                }
                break;

            case "RATING":
                int ratingLabelCount = 0;
                for (QuestionLabel questionLabel : question.getLabels()) {
                    for (ResponseAnswer responseAnswer : responseAnswers) {
                        if (questionLabel.getId().equals(responseAnswer.getQuestionLabelId())) {
                            if (responseAnswer.getValue().compareTo(BigDecimal.ZERO) >= 0) {
                                ratingLabelCount++;
                            }
                        }
                    }
                }
                if (ratingLabelCount == question.getLabels().size()) {
                    isAttended = true;
                }
                break;

            case "NPS":
                for (ResponseAnswer responseAnswer : responseAnswers) {
                    if (question.getId().equals(responseAnswer.getQuestionId())) {
                        isAttended = true;
                        break;
                    }
                }
                break;

            case "TEXT":
                int textLabelCount = 0;
                for (QuestionLabel questionLabel : question.getLabels()) {
                    for (ResponseAnswer responseAnswer : responseAnswers) {
                        if (questionLabel.getId().equals(responseAnswer.getQuestionLabelId())) {
                            if (responseAnswer.getResponseAnswerText() != null && responseAnswer.getResponseAnswerText().getText() != null) {
                                String text = responseAnswer.getResponseAnswerText().getText();
                                if (text.length() > 0) {
                                    textLabelCount++;
                                }
                            }
                        }
                    }
                }
                if (textLabelCount == question.getLabels().size()) {
                    isAttended = true;
                }
                break;
        }
        return isAttended;
    }

    /**
     * shows Alert Dialog
     *
     * @param message Alert message
     */
    public void showAlertDialog(String message) {
        AlertDialog dialog = new AlertDialog.Builder(currentContext)
                .setMessage(message)
                .setCancelable(true)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //do things
                    }
                }).create();
        dialog.setOnShowListener(new DialogInterface.OnShowListener() {

            @Override
            public void onShow(final DialogInterface dialog) {
                Button oKButton = ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_POSITIVE);
                oKButton.setTextColor(Color.parseColor(colorPrimary));
                oKButton.setTypeface(typeFace);
            }
        });

        dialog.show();
    }

    /**
     * Sets Custom font for campaign
     */
    public void setFont() {
        typeFace = Typeface.createFromAsset(currentContext.getAssets(), "fonts/ProximaNova-Regular.otf");
        txtFooterCredit.setTypeface(typeFace);
        questionCount.setTypeface(typeFace);
        txtBrandName.setTypeface(typeFace);
        btnNext.setTypeface(typeFace);
        btnPrev.setTypeface(typeFace);
        btnStartCampaign.setTypeface(typeFace);
    }

    /**
     * Save failed response to preference and submit it back next time widget loads
     */
    public void saveFailedResponse() {
        Gson gson = new Gson();
        String prefName = getAppname(currentContext) + "responseList";
        String responses = sharedPreferences.getString(prefName, null);
        ArrayList<Response> arrayList = new ArrayList<>();
        if (responses == null) {
            arrayList.add(response);
        } else {
            Type type = new TypeToken<ArrayList<Response>>() {
            }.getType();
            arrayList = gson.fromJson(responses, type);
            arrayList.add(response);
        }
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        String json = gson.toJson(arrayList);
        prefsEditor.putString(prefName, json);
        prefsEditor.apply();
    }

    /**
     * send authentication request to Loyagram.
     *
     * @param password password
     */
    public void sendAuthRequest(String password) {
        if (token != null) {
            BigDecimal bizId = campaign.getBizId();
            AsyncTaskAuth asyncTaskAuth = new AsyncTaskAuth(bizId, token);
            asyncTaskAuth.setListener(new APIResultCallback<Boolean>() {
                @Override
                public void onSuccess(Boolean result) {
                    if (result) {
                        removeResponseFromPreference();
                        dismisDialog();
                        exitCampaign();
                    } else {
                        showAlert("Invalid password please try again");
                    }
                }

                @Override
                public void onError(List<ErrorModel> errors) {
                    showAlert("An error occured, please try after some time");

                }
            });
            asyncTaskAuth.execute(password);
        } else {
            dismisDialog();
            exitCampaign();
        }
    }

    /**
     * Shows Password view when on backpress only if repeatmode is true.
     */
    public void showPasswordView() {
        View view = LayoutInflater.from(currentContext).inflate(R.layout.loyagram_textview_pwd, null);
        final AppCompatEditText txtPassword = (AppCompatEditText) view.findViewById(R.id.editText);
        txtPassword.setBackgroundColor(Color.TRANSPARENT);
        dialog = new AlertDialog.Builder(currentContext)
                .setView(view)
                .setMessage("Please enter password to continue")
                .setCancelable(true)
                .setPositiveButton("OK", null)
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                }).create();

        dialog.setOnShowListener(new DialogInterface.OnShowListener() {

            @Override
            public void onShow(final DialogInterface dialog) {
                Button oKButton = ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_POSITIVE);
                oKButton.setTextColor(Color.parseColor(colorPrimary));
                oKButton.setTypeface(typeFace);
                Button cancelButton = ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_NEGATIVE);
                cancelButton.setTextColor(Color.parseColor(colorPrimary));
                cancelButton.setTypeface(typeFace);
                oKButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (!ApiBase.isEverythingOK(currentContext)) {
                            Toast.makeText(currentContext, "No internet connection.",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            String password = txtPassword.getText().toString();
                            sendAuthRequest(password);
                        }
                    }
                });
            }
        });
        dialog.show();
    }

    /**
     * Dismiss authentication dialog
     */
    public void dismisDialog() {
        dialog.dismiss();
    }

    /**
     * Returns application name
     *
     * @param context context
     * @return app name
     */
    private static String getAppname(Context context) {
        return context.getResources().getString(R.string.app_name);
    }

    /**
     * Remove response saved from preference
     */
    public void removeResponseFromPreference() {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(currentContext);
        String prefName = getAppname(currentContext) + "response";
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        prefsEditor.remove(prefName).apply();
    }

    /**
     * Get spannable String from the API response refer links
     *
     * @param links List of refer links
     * @return spanable string with keywords and url
     */
    public SpannableString getSpannableString(List<Link> links) {
        String urlString = "";
        String[] urls = new String[links.size()];
        String[] urlKeywords = new String[links.size()];
        for (int i = 0; i < links.size(); i++) {
            if (i == links.size() - 1) {
                urlString += links.get(i).getLabel();
            } else {
                urlString += links.get(i).getLabel() + "\n";
            }
            urls[i] = links.get(i).getUrl();
            urlKeywords[i] = links.get(i).getLabel();
        }
        SpannableString ss = new SpannableString(
                urlString);
        int startIndex = 0;
        for (int j = 0; j < links.size(); j++) {
            ss.setSpan(new URLSpan(urls[j]), startIndex, startIndex + urlKeywords[j].length(),
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            startIndex += urlKeywords[j].length() + 1;
        }
        return ss;
    }

    /**
     * Sets the layout and click listener for the Refer Friends submit buttton
     *
     * @param btnReferFriends Refer Friends submit button
     */
    public void initReferFriendsButton(Button btnReferFriends) {
        int pxButtonHeight = (int) getResources().getDimension(R.dimen.button_height);
        int pxButtonWidth = (int) getResources().getDimension(R.dimen.button_width);
        int pxPadding = (int) getResources().getDimension(R.dimen.button_padding);
        btnReferFriends.setText("Refer Friends");
        btnReferFriends.setAllCaps(false);
        btnReferFriends.setTextColor(Color.parseColor("#FFFFFF"));
        btnReferFriends.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
        btnReferFriends.setPadding(pxPadding, pxPadding, pxPadding, pxPadding);
        btnReferFriends.setBackgroundResource(R.drawable.lg_surveybuttons);
        int stroke = getResources().getDimensionPixelSize(R.dimen.stroke_width);
        ((GradientDrawable) btnReferFriends.getBackground()).setColor(Color.parseColor(colorPrimary));
        ((GradientDrawable) btnReferFriends.getBackground()).setStroke(stroke, Color.parseColor(colorPrimary));
        btnReferFriends.setTypeface(typeFace);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(pxButtonWidth, pxButtonHeight);
        params.gravity = CENTER;
        float scale = getContext().getResources().getDisplayMetrics().density;
        final int txtMargin = (int) (10 * scale + 0.5f);
        params.setMargins(txtMargin, txtMargin, txtMargin, txtMargin);
        btnReferFriends.setLayoutParams(params);
        btnReferFriends.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                llWidgetcontainerMain.removeView(scrollView);
                final TextView txtThankYou = new TextView(currentContext);
                LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
                txtThankYou.setGravity(CENTER);
                layoutParams.setMargins(txtMargin, txtMargin, txtMargin, 0);
                txtThankYou.setLayoutParams(layoutParams);
                txtThankYou.setText("Thank you for your time");
                txtThankYou.setTypeface(typeFace);
                txtThankYou.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
                llWidgetcontainerMain.addView(txtThankYou);
                final Handler thankyouHandler = new Handler();
                thankyouHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        llWidgetcontainerMain.removeView(txtThankYou);
                        if (isRepeatable || isPreview) {
                            resetCampaign();
                            return;
                        }
                        exitCampaign();
                    }
                }, 3000);
            }
        });
    }

    /**
     * Initialize NPS thank you message and Refer Friends View.
     *
     * @param msg             Thank You Message
     * @param spannableString Spannable String with URL
     * @param type            1 for NPS and 0 for survey
     * @return Layout with thank you and refer friends
     */
    public LinearLayout initNpsThankYouLayout(String msg, SpannableString spannableString, int type) {
        llWidgetcontainer.removeAllViews();
        rrbottomButtonContainer.setVisibility(GONE);
        llWidgetcontainer.setVisibility(GONE);
        LinearLayout linearLayout = new LinearLayout(currentContext);
        linearLayout.setOrientation(VERTICAL);
        linearLayout.setGravity(CENTER);
        LayoutParams llparams = new LayoutParams(LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        linearLayout.setLayoutParams(llparams);
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        float scale = getContext().getResources().getDisplayMetrics().density;
        int txtMargin = (int) (5 * scale + 0.5f);
        int editTextHeight = (int) (40 * scale + 0.5f);
        params.setMargins(txtMargin, txtMargin, txtMargin, 0);
        TextView txtThankyou = new TextView(currentContext);
        txtThankyou.setTextColor(Color.parseColor("#000000"));
        txtThankyou.setGravity(CENTER);
        txtThankyou.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.text_medium));
        txtThankyou.setLayoutParams(params);
        txtThankyou.setText(msg);
        txtThankyou.setTypeface(typeFace);
        if (type == 1) {
            linearLayout.addView(txtThankyou);
            EditText txtReferFriends = new EditText(currentContext);
            txtReferFriends.setHint("eg:friend@domain.com");
            txtReferFriends.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.text_medium));
            txtReferFriends.setTypeface(typeFace);
            LayoutParams editTextParams = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, editTextHeight);
            editTextParams.setMargins(txtMargin, txtMargin, txtMargin, 0);
            txtReferFriends.setLayoutParams(editTextParams);
            txtReferFriends.setBackgroundResource(R.drawable.lg_npssquare);
            int stroke = getResources().getDimensionPixelSize(R.dimen.stroke_width);
            ((GradientDrawable) txtReferFriends.getBackground()).setStroke(stroke, Color.parseColor(colorPrimary));
            TextView referFriendsText = new TextView(currentContext);
            referFriendsText.setTextColor(Color.parseColor("#000000"));
            referFriendsText.setGravity(CENTER);
            referFriendsText.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.text_medium));
            referFriendsText.setLayoutParams(params);
            referFriendsText.setTypeface(typeFace);
            referFriendsText.setText("Please refer us to your friends and win gifts!");
            AppCompatButton btnReferFriends = new AppCompatButton(currentContext);
            initReferFriendsButton(btnReferFriends);
            if (isPreview || isRepeatable) {
                linearLayout.addView(referFriendsText);
                linearLayout.addView(txtReferFriends);
                linearLayout.addView(btnReferFriends);
            } else {
                TextView txtLinkView = new TextView(currentContext);
                txtLinkView.setTextColor(Color.parseColor("#000000"));
                txtLinkView.setGravity(CENTER);
                txtLinkView.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.text_medium));
                txtLinkView.setLayoutParams(params);
                txtLinkView.setText(spannableString);
                txtLinkView.setMovementMethod(LinkMovementMethod.getInstance());
                txtLinkView.setTypeface(typeFace);
                linearLayout.addView(txtLinkView);
                linearLayout.addView(referFriendsText);
                linearLayout.addView(txtReferFriends);
                linearLayout.addView(btnReferFriends);
            }
        } else {
            linearLayout.addView(txtThankyou);
        }
        return linearLayout;
    }

    public String getWelcomeMessage() {
        String langcode = currentLanguage.getCode();
        String welcomeString = "";
        List<WelcomeTranslation> welcomeTranslations = campaign.getWelcomeMessages();
        Boolean isTextChanged = false;
        for (WelcomeTranslation welcomeTranslation : welcomeTranslations) {
            if (welcomeTranslation.getCode() != null && welcomeTranslation.getCode().equals(langcode)) {
                if (welcomeTranslation.getTranslation() == null || welcomeTranslation.getTranslation().isEmpty()) {
                    break;
                }
                isTextChanged = true;
                welcomeString = welcomeTranslation.getTranslation();
                break;
            }
        }

        if (!isTextChanged) {
            langcode = primaryLanguage.getCode();
            welcomeTranslations = campaign.getWelcomeMessages();
            for (WelcomeTranslation welcomeTranslation : welcomeTranslations) {
                if (welcomeTranslation.getCode() != null && welcomeTranslation.getCode().equals(langcode)) {
                    welcomeString = welcomeTranslation.getTranslation();
                    break;
                }
            }
        }
        return welcomeString;
    }

    public void getStaticTextTransaltions() {

        List<StaticTextTransalation> staticTextTransalations = campaign.getStaticTextTranslations();
        String transalatedString;
        if(staticTextTransalations != null) {
            for (StaticTextTransalation staticTextTransalation : staticTextTransalations) {
                if (currentLanguage != null && staticTextTransalation.getCode().equals(currentLanguage.getCode())) {
                    transalatedString = staticTextTransalation.getTranslation();
                    switch (staticTextTransalation.getStaticTextId()) {

                        case "CAMPAIGN_MODE_BACK_BUTTON_TEXT":
                            staticTextes.put("CAMPAIGN_MODE_BACK_BUTTON_TEXT", transalatedString);
                            break;
                        case "CAMPAIGN_MODE_NEXT_BUTTON_TEXT":
                            staticTextes.put("CAMPAIGN_MODE_NEXT_BUTTON_TEXT", transalatedString);
                            break;
                        case "CAMPAIGN_MODE_START_BUTTON_TEXT":
                            staticTextes.put("CAMPAIGN_MODE_START_BUTTON_TEXT", transalatedString);
                            break;
                        case "CAMPAIGN_MODE_SUBMIT_BUTTON_TEXT":
                            staticTextes.put("CAMPAIGN_MODE_SUBMIT_BUTTON_TEXT", transalatedString);
                            break;
                        case "CHANGE_SCORE_BUTTON_TEXT":
                            staticTextes.put("CHANGE_SCORE_BUTTON_TEXT", transalatedString);
                            break;
                        case "POWERED_BY":
                            staticTextes.put("POWERED_BY", transalatedString);
                            break;
                        case "SCORE_MESSAGE_TEXT":
                            staticTextes.put("SCORE_MESSAGE_TEXT", transalatedString);
                            break;
                        case "CAMPAIGN_MODE_EXIT_DIALOG_TEXT":
                            staticTextes.put("CAMPAIGN_MODE_EXIT_DIALOG_TEXT", transalatedString);
                            break;
                        case "CAMPAIGN_MODE_ANSWER_REQUIRED_DIALOG_TEXT":
                            staticTextes.put("CAMPAIGN_MODE_ANSWER_REQUIRED_DIALOG_TEXT", transalatedString);
                            break;
                        default:
                            break;
                    }

                }
            }
        }
        //Log.i("Static texts", staticTextes.toString());
    }

    /**
     * Set static texts based on current language
     */
    public void setStaticTextes() {
        if (noOfQuestions == 1 || questionNumber == noOfQuestions) {
            btnNext.setText(staticTextes.get("CAMPAIGN_MODE_SUBMIT_BUTTON_TEXT"));
        } else if (isFollowUpEnabled) {
            if (followUpIterator == 2) {
                btnNext.setText(staticTextes.get("CAMPAIGN_MODE_SUBMIT_BUTTON_TEXT"));
            } else {
                btnNext.setText(staticTextes.get("CAMPAIGN_MODE_NEXT_BUTTON_TEXT"));
            }
        } else {
            btnNext.setText(staticTextes.get("CAMPAIGN_MODE_NEXT_BUTTON_TEXT"));
        }
        btnPrev.setText(staticTextes.get("CAMPAIGN_MODE_BACK_BUTTON_TEXT"));
        txtFooterCredit.setText(staticTextes.get("POWERED_BY"));
        if (btnStartCampaign != null) {
            btnStartCampaign.setText(staticTextes.get("CAMPAIGN_MODE_START_BUTTON_TEXT"));
        }

    }
}