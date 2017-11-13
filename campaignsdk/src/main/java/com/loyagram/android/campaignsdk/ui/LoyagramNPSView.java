package com.loyagram.android.campaignsdk.ui;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.widget.AppCompatCheckBox;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.loyagram.android.campaignsdk.R;
import com.loyagram.android.campaignsdk.models.LabelTranslation;
import com.loyagram.android.campaignsdk.models.Language;
import com.loyagram.android.campaignsdk.models.Question;
import com.loyagram.android.campaignsdk.models.QuestionLabel;
import com.loyagram.android.campaignsdk.models.QuestionTranslations;
import com.loyagram.android.campaignsdk.models.Response;
import com.loyagram.android.campaignsdk.models.ResponseAnswer;
import com.loyagram.android.campaignsdk.models.ResponseAnswerText;
import com.loyagram.android.campaignsdk.models.npssettings.NpsSettings;
import com.loyagram.android.campaignsdk.models.npssettings.RequestReasonSettings;
import com.loyagram.android.campaignsdk.models.npssettings.Settings;
import com.loyagram.android.campaignsdk.models.npssettings.SettingsTranslation;
import com.loyagram.android.campaignsdk.models.npssettings.Widget;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * Class for NPS Type campaign. Layout will be loaded based on the type of NPS.
 * Response will be saved each time user submit nps
 */

@SuppressWarnings("SpellCheckingInspection")
public class LoyagramNPSView extends LinearLayout implements View.OnClickListener {

    interface LoyagramNPSListener {
        void onNPSBackPress();

        void setRating(int rating);

        void hideSubmitButton(Boolean show);

        void onNPSSubmit(Boolean isSubmit);

        void enableFollowUp(Boolean enable);

        void setFollowUpemail(String email);
        void hideValidationMessage();
    }

    Language language = null;
    Question question;
    Question followUpQuestion = null;
    Response response;
    ResponseAnswer responseAnswer;
    LoyagramNPSListener listener;
    TextView txtNPSQuestion;
    TextView txtFeedbackQestion;
    TextView txtNotLikely;
    TextView txtVeryLikely;
    TextView txtFollowUpQstn;
    LinearLayout llRating;
    //LinearLayout llNPSFeedback;
    LinearLayout llRatingContainer;
    LinearLayout lloptionsContainer;
    LinearLayout llFollowUpContainer;
    LinearLayout llEmailFollowUpContainer;
    RelativeLayout rrReasonContainer;
    EditText txtReason;
    EditText txtEmail;
    AppCompatCheckBox chkEmail;
    String currentRating;
    int ratingViewType = 0; // 0 for circle
    String campaignType = null;
    Boolean isKeyboardShown = false;
    Boolean isEmailFollowUpEnabled = false;
    LoyagramCampaignView loyagramCampaignView = null;
    String colorPrimary = null;
    Context currentContext = null;
    Typeface typeface;
    HashMap<String, String> statictextes = null;
    Language primaryLanguage;


    public LoyagramNPSView(Context context, String campaigntype, Question question, Question followUpQuestion, Boolean isEmailFollowUpEnabled, Response response, LoyagramCampaignView loyagramCampaignView, String colorPrimary, Language language, HashMap<String, String> staticTextes, Language primaryLanguage) {
        super(context);
        this.response = response;
        this.question = question;
        this.followUpQuestion = followUpQuestion;
        this.isEmailFollowUpEnabled = isEmailFollowUpEnabled;
        this.campaignType = campaigntype;
        this.loyagramCampaignView = loyagramCampaignView;
        this.colorPrimary = colorPrimary;
        this.language = language;
        this.primaryLanguage = primaryLanguage;
        this.statictextes = staticTextes;
        init(context);
    }

    public LoyagramNPSView(Context context, AttributeSet attr) {
        super(context, attr);
        init(context);
    }

    public LoyagramNPSView(Context context, AttributeSet attr, int defStyle) {
        super(context, attr, defStyle);
        init(context);
    }

    public void setNPSListener(LoyagramNPSListener listener) {
        this.listener = listener;
    }

    public void init(Context context) {
        View.inflate(context, R.layout.loyagram_npsview, this);
        this.currentContext = context;
        typeface = Typeface.createFromAsset(currentContext.getAssets(), "fonts/ProximaNova-Regular.otf");
        initLayout();
        setTheme();
        setLanguageListener();
        setSubmitListener();
        this.setFocusableInTouchMode(true);
        this.requestFocus();
    }

    /**
     * Method to set the layout theme. Theme will either from API request or pass via argument.
     */
    public void setTheme() {
        if (colorPrimary != null) {
            int stroke = getResources().getDimensionPixelSize(R.dimen.stroke_width);
            ((GradientDrawable) txtReason.getBackground()).setStroke(stroke, Color.parseColor(colorPrimary));
            ((GradientDrawable) txtEmail.getBackground()).setStroke(stroke, Color.parseColor(colorPrimary));
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                chkEmail.setButtonTintList(getColorStateList());
            } else {
                chkEmail.setSupportButtonTintList(getColorStateList());
            }
        }

    }


    /**
     * Intialize NPS layout
     */
    public void initLayout() {
        initRatingButtons();
        // llNPSFeedback = (LinearLayout) findViewById(R.id.npsFeedbackHeader);
        llRatingContainer = (LinearLayout) findViewById(R.id.topRatingContainer);
        txtReason = (EditText) findViewById(R.id.txtReason);
        txtFeedbackQestion = (TextView) findViewById(R.id.txtFeedbackQstn);
        txtNPSQuestion = (TextView) findViewById(R.id.npsquestion);
        txtNotLikely = (TextView) findViewById(R.id.txtNotLikely);
        txtVeryLikely = (TextView) findViewById(R.id.txtVeryLikely);
        llRating = (LinearLayout) findViewById(R.id.ratingContainer);
        rrReasonContainer = (RelativeLayout) findViewById(R.id.reasonFooter);
        lloptionsContainer = (LinearLayout) findViewById(R.id.optionsContainer);
        llFollowUpContainer = (LinearLayout) findViewById(R.id.followupContainer);
        txtFollowUpQstn = (TextView) findViewById(R.id.followupQstn);
        llEmailFollowUpContainer = (LinearLayout) findViewById(R.id.emailFollowUpContainer);
        txtEmail = (EditText) findViewById(R.id.txtEmail);
        chkEmail = (AppCompatCheckBox) findViewById(R.id.chkEmail);
        if (getTypeFace() != null) {
            txtReason.setTypeface(typeface);
            txtNotLikely.setTypeface(typeface);
            txtVeryLikely.setTypeface(typeface);
            txtFeedbackQestion.setTypeface(typeface);
            txtNPSQuestion.setTypeface(typeface);
            chkEmail.setTypeface(typeface);
            txtEmail.setTypeface(typeface);
        }
        Settings settings = question.getSettings();
        String shape = settings.getNpsSettings().getShape();
        if (shape.equals("square")) {
            ratingViewType = 1;
        } else {
            ratingViewType = 0;
        }
        //  btnRetry.setText(statictextes.get("CHANGE_SCORE_BUTTON_TEXT"));
        setQuestion();
        setLikelyText();
        ResponseAnswer ra = getResponseAnswer(question.getId());
        setRatingViewStyle(ra);
        if (loyagramCampaignView != null) {
            loyagramCampaignView.showSubView(true);
            loyagramCampaignView.hideProgress();
        }
        txtReason.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                isKeyboardShown = true;
                return false;
            }
        });

        txtReason.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                ResponseAnswer ra = getResponseAnswer(question.getId());
                if (ra != null) {
                    ra.getResponseAnswerText().setText(charSequence.toString());
                    listener.onNPSSubmit(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        chkEmail.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (chkEmail.isChecked()) {
                    txtEmail.setVisibility(VISIBLE);
                    if (listener != null) {
                        listener.enableFollowUp(true);
                    }
                } else {
                    txtEmail.setVisibility(GONE);
                    if (listener != null) {
                        listener.enableFollowUp(false);
                        listener.hideValidationMessage();
                    }
                }

            }
        });
        txtEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                listener.setFollowUpemail(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        txtEmail.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                isKeyboardShown = true;
                return false;
            }
        });


    }

    /**
     * Sets RatingView style. It will be either rounded view or square view based on the settings.
     *
     * @param ra Saved response answer to show already answered ones
     */
    public void setRatingViewStyle(ResponseAnswer ra) {
        int selectedRating = -1;
        int stroke = getResources().getDimensionPixelSize(R.dimen.stroke_width);
        for (int i = 0; i < llRating.getChildCount(); i++) {
            TextView txtView = (TextView) llRating.getChildAt(i);
            if (ra != null && ra.getValue() != null) {
                selectedRating = Integer.parseInt(ra.getValue().toString());
            }
            if (ra != null && ra.getQuestionId().equals(question.getId()) && i == selectedRating) {
                if (ratingViewType == 0) {
                    txtView.setBackgroundResource(R.drawable.lg_npscircleselected);
                } else {
                    txtView.setBackgroundResource(R.drawable.lg_npssquareselected);
                }
                ((GradientDrawable) txtView.getBackground()).setColor(Color.parseColor(colorPrimary));
                ((GradientDrawable) txtView.getBackground()).setStroke(stroke, Color.parseColor(colorPrimary));
                txtView.setTextColor(Color.parseColor("#FFFFFF"));
            } else {
                txtView.setTextColor(Color.parseColor(colorPrimary));
                if (ratingViewType == 0) {
                    txtView.setBackgroundResource(R.drawable.lg_npscircle);
                } else {
                    txtView.setBackgroundResource(R.drawable.lg_npssquare);
                }
                ((GradientDrawable) txtView.getBackground()).setStroke(stroke, Color.parseColor(colorPrimary));
            }
        }
    }


    /**
     * sets click event listeners for the rating view buttons
     */
    public void initRatingButtons() {
        TextView txtRating0 = (TextView) findViewById(R.id.ratingView0);
        txtRating0.setOnClickListener(this);
        TextView txtRating1 = (TextView) findViewById(R.id.ratingView1);
        txtRating1.setOnClickListener(this);
        TextView txtRating2 = (TextView) findViewById(R.id.ratingView2);
        txtRating2.setOnClickListener(this);
        TextView txtRating3 = (TextView) findViewById(R.id.ratingView3);
        txtRating3.setOnClickListener(this);
        TextView txtRating4 = (TextView) findViewById(R.id.ratingView4);
        txtRating4.setOnClickListener(this);
        TextView txtRating5 = (TextView) findViewById(R.id.ratingView5);
        txtRating5.setOnClickListener(this);
        TextView txtRating6 = (TextView) findViewById(R.id.ratingView6);
        txtRating6.setOnClickListener(this);
        TextView txtRating7 = (TextView) findViewById(R.id.ratingView7);
        txtRating7.setOnClickListener(this);
        TextView txtRating8 = (TextView) findViewById(R.id.ratingView8);
        txtRating8.setOnClickListener(this);
        TextView txtRating9 = (TextView) findViewById(R.id.ratingView9);
        txtRating9.setOnClickListener(this);
        TextView txtRating10 = (TextView) findViewById(R.id.ratingView10);
        txtRating10.setOnClickListener(this);
        if (getTypeFace() != null) {
            txtRating0.setTypeface(typeface);
            txtRating1.setTypeface(typeface);
            txtRating2.setTypeface(typeface);
            txtRating3.setTypeface(typeface);
            txtRating4.setTypeface(typeface);
            txtRating5.setTypeface(typeface);
            txtRating6.setTypeface(typeface);
            txtRating7.setTypeface(typeface);
            txtRating8.setTypeface(typeface);
            txtRating9.setTypeface(typeface);
            txtRating10.setTypeface(typeface);
        }
    }

    /**
     * Hanlde click events
     *
     * @param v Currently clicked rating button
     */
    @Override
    public void onClick(View v) {

        switch ((String) v.getTag()) {
            case "0":
                setRating(0);
                break;
            case "1":
                setRating(1);
                break;
            case "2":
                setRating(2);
                break;
            case "3":
                setRating(3);
                break;
            case "4":
                setRating(4);
                break;
            case "5":
                setRating(5);
                break;
            case "6":
                setRating(6);
                break;
            case "7":
                setRating(7);
                break;
            case "8":
                setRating(8);
                break;
            case "9":
                setRating(9);
                break;
            case "10":
                setRating(10);
                break;
            default:
                break;
        }
    }

    /**
     * Seting will be saved to the response and feedback view will be shown, if campain type is NPS.
     *
     * @param rating current rating
     */
    public void setRating(int rating) {
        currentRating = "" + rating;
        String txtTag = "" + rating;
        refreshNPS();
        TextView selectedTextView = (TextView) findViewWithTag(txtTag);
        selectedTextView.setTextColor(Color.parseColor("#FFFFFF"));
        int stroke = getResources().getDimensionPixelSize(R.dimen.stroke_width);
        if (ratingViewType == 0) {
            selectedTextView.setBackgroundResource(R.drawable.lg_npscircleselected);
            ((GradientDrawable) selectedTextView.getBackground()).setColor(Color.parseColor(colorPrimary));
            ((GradientDrawable) selectedTextView.getBackground()).setStroke(stroke, Color.parseColor(colorPrimary));
        } else {
            selectedTextView.setBackgroundResource(R.drawable.lg_npssquareselected);
            ((GradientDrawable) selectedTextView.getBackground()).setColor(Color.parseColor(colorPrimary));
            ((GradientDrawable) selectedTextView.getBackground()).setStroke(stroke, Color.parseColor(colorPrimary));

        }
        responseAnswer = setNpsResponse(question.getId(), new BigDecimal(rating));
        if (listener != null) {
            listener.setRating(rating);
            //listener.onNPSSubmit(isSubmit);
        }
        //NPS Settings based on the current rating

        /*
        Boolean isSubmit = false;
        if (campaignType.equals("NPS")) {
            String requestReasonType = question.getSettings().getNpsSettings().getRequestReasonSettings().getType();
            switch (requestReasonType) {
                case "all":
                    showReasonView(rating);
                    setFeedbackQuestion();
                    if (listener != null) {
                        listener.hideSubmitButton(true);
                    }
                    break;
                case "disabled":
                    isSubmit = true;
                    break;
                case "score":
                    if (rating <= 6) {
                        ReasonSetting reasonSetting = question.getSettings().getNpsSettings().getRequestReasonSettings().getCustom().getDetractors();
                        if (!reasonSetting.isDisabled()) {
                            showReasonView(rating);
                            setFeedbackQuestion();
                            if (listener != null) {
                                listener.hideSubmitButton(true);
                            }
                        } else {
                            isSubmit = true;
                        }
                    } else if (rating <= 8) {
                        ReasonSetting reasonSetting = question.getSettings().getNpsSettings().getRequestReasonSettings().getCustom().getPassives();
                        if (!reasonSetting.isDisabled()) {
                            showReasonView(rating);
                            setFeedbackQuestion();
                            if (listener != null) {
                                listener.hideSubmitButton(true);
                            }
                        } else {
                            isSubmit = true;
                        }
                    } else {
                        ReasonSetting reasonSetting = question.getSettings().getNpsSettings().getRequestReasonSettings().getCustom().getPromoters();
                        if (!reasonSetting.isDisabled()) {
                            showReasonView(rating);
                            setFeedbackQuestion();
                            if (listener != null) {
                                listener.hideSubmitButton(true);
                            }
                        } else {
                            isSubmit = true;
                        }
                    }
                    break;
            }
        }

        */
        //showFollowUp();
    }

    public ResponseAnswer getMulResponseAnswer(BigDecimal id) {
        if (response.getResponseAnswers() != null)
            for (ResponseAnswer as : response.getResponseAnswers()) {
                if (as != null && as.getQuestionLabelId() != null && as.getQuestionLabelId().equals(id)) {
                    return as;
                }
            }
        return null;
    }

    public ResponseAnswer getResponseAnswer(BigDecimal id) {
        if (response.getResponseAnswers() != null)
            for (ResponseAnswer as : response.getResponseAnswers()) {
                if (as != null && as.getQuestionId() != null && as.getQuestionId().equals(id)) {
                    return as;
                }
            }
        return null;
    }

    /**
     * sets current questions answer to the response answer list. if response answer is null create new one or update existing.
     *
     * @param qid question Id
     * @param val current answer
     * @return response answer
     */
    private ResponseAnswer setNpsResponse(BigDecimal qid, BigDecimal val) {
        ResponseAnswer ra = getResponseAnswer(qid);
        if (ra != null) {
            ra.setValue(val);
            return ra;
        } else {
            ResponseAnswer responseAnswer = getNewResponseAnswer();
            responseAnswer.setQuestionId(qid);
            responseAnswer.setValue(val);
            responseAnswer.setAt(System.currentTimeMillis());
            ResponseAnswerText txt = new ResponseAnswerText();
            txt.setCampaignedAnswerId(responseAnswer.getId());
            responseAnswer.setResponseAnswerText(txt);
            response.getResponseAnswers().add(responseAnswer);
            return responseAnswer;
        }
    }

    /**
     * Set multiple choice response answer
     *
     * @param questionId question ID
     * @param val        value
     * @return response answer
     */
    public ResponseAnswer setMultiselectResponse(BigDecimal questionId, BigDecimal val) {
        ResponseAnswer resAnswer = getMulResponseAnswer(questionId);
        if (resAnswer != null) {
            if (val.compareTo(BigDecimal.ONE) == 1) {
                resAnswer.setValue(questionId);
                return resAnswer;
            } else {
                response.getResponseAnswers().remove(resAnswer);
                return null;
            }
        } else {
            ResponseAnswer responseAnswer = getNewMulResponseAnswer();
            responseAnswer.setQuestionLabelId(questionId);
            responseAnswer.setValue(questionId);
            responseAnswer.setAt(System.currentTimeMillis());
            response.getResponseAnswers().add(responseAnswer);
            return responseAnswer;
        }
    }


    /**
     * create new response answer and returns.
     *
     * @return Response answer
     */
    public ResponseAnswer getNewResponseAnswer() {
        ResponseAnswer newAnswer = new ResponseAnswer();
        newAnswer.setBizId(response.getBizId());
        newAnswer.setBizLocId(response.getLocationId());
        newAnswer.setBizUserId(response.getUserId());
        newAnswer.setCampaignId(response.getCampaignId());
        newAnswer.setCampaignedId(response.getId());
        newAnswer.setQuestionId(question.getId());
        newAnswer.setAt(System.currentTimeMillis());
        newAnswer.setId(UUID.randomUUID().toString());
        return newAnswer;
    }

    public ResponseAnswer getNewMulResponseAnswer() {
        ResponseAnswer newAnswer = new ResponseAnswer();
        newAnswer.setBizId(response.getBizId());
        newAnswer.setBizLocId(response.getLocationId());
        newAnswer.setBizUserId(response.getUserId());
        newAnswer.setCampaignId(response.getCampaignId());
        newAnswer.setCampaignedId(response.getId());
        newAnswer.setQuestionId(followUpQuestion.getId());
        newAnswer.setAt(System.currentTimeMillis());
        newAnswer.setId(UUID.randomUUID().toString());
        return newAnswer;
    }

    /**
     * Refresh NPS when rating or change rating is tapped
     */
    public void refreshNPS() {
        int stroke = getResources().getDimensionPixelSize(R.dimen.stroke_width);
        for (int i = 0; i < llRating.getChildCount(); i++) {
            TextView txt = (TextView) llRating.getChildAt(i);
            if (currentRating.equals(txt.getTag().toString())) {
                continue;
            }
            txt.setTextColor(Color.parseColor(colorPrimary));
            if (ratingViewType == 0) {
                txt.setBackgroundResource(R.drawable.lg_npscircle);
                ((GradientDrawable) txt.getBackground()).setStroke(stroke, Color.parseColor(colorPrimary));
            } else {
                txt.setBackgroundResource(R.drawable.lg_npssquare);
                ((GradientDrawable) txt.getBackground()).setStroke(stroke, Color.parseColor(colorPrimary));
            }
        }
    }

    /**
     * shows reason view if the campaign type is NPS
     */
    public void showReasonView() {
        setFeedbackQuestion();
        if (isEmailFollowUpEnabled) {
            llEmailFollowUpContainer.setVisibility(VISIBLE);
        }
        llFollowUpContainer.setVisibility(GONE);
        rrReasonContainer.setVisibility(VISIBLE);

    }



    public void showFollowUp() {
        setFollowUpQuestion();
        llRatingContainer.setVisibility(GONE);
        txtNPSQuestion.setVisibility(GONE);
        llFollowUpContainer.setVisibility(VISIBLE);
        lloptionsContainer.removeAllViews();
        List<QuestionLabel> questionLabels = followUpQuestion.getLabels();
        if (questionLabels != null) {
            for (final QuestionLabel ql : questionLabels) {
                final AppCompatCheckBox chk = new AppCompatCheckBox(currentContext);
                LinearLayout.LayoutParams buttonLayoutParams = new LinearLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                buttonLayoutParams.setMargins(0, 0, 0, 0);
                chk.setLayoutParams(buttonLayoutParams);
                chk.setTag(ql.getId());
                if (getTypeFace() != null) {
                    chk.setTypeface(typeface);
                }
                Boolean isLabelSet = false;
                for (LabelTranslation labelTranslation : ql.getLabelTranslations()) {
                    if (language != null && language.getCode().equals(labelTranslation.getCode())) {
                        chk.setText(labelTranslation.getTranslation());
                        isLabelSet = true;
                        break;
                    }
                }

                if (!isLabelSet) {
                    for (LabelTranslation labelTranslation : ql.getLabelTranslations()) {
                        if (primaryLanguage != null && primaryLanguage.getCode().equals(labelTranslation.getCode())) {
                            chk.setText(labelTranslation.getTranslation());
                            break;
                        }
                    }
                }
                chk.setId(Integer.parseInt(ql.getId().toString()));
                ResponseAnswer responseAnswer = getMulResponseAnswer(ql.getId());
                if (responseAnswer != null && responseAnswer.getQuestionLabelId().equals(ql.getId())) {
                    if (responseAnswer.getValue() != null && responseAnswer.getValue().equals(ql.getId())) // 1 equals true
                        chk.setChecked(true);
                }
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    chk.setButtonTintList(getColorStateList());
                } else {
                    chk.setSupportButtonTintList(getColorStateList());
                }
                chk.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ResponseAnswer respAns;
                        if (chk.isChecked()) {
                            respAns = setMultiselectResponse(ql.getId(), new BigDecimal(1));
                        } else {
                            respAns = setMultiselectResponse(ql.getId(), new BigDecimal(0));
                        }
                        if (listener != null) {
                            listener.onNPSSubmit(false);
                        }
                    }
                });
                lloptionsContainer.addView(chk);
            }
        }
    }


    /**
     * create color state list from theme color and return.
     *
     * @return color state list
     */
    public ColorStateList getColorStateList() {
        int[][] states = new int[][]{
                new int[]{android.R.attr.state_enabled},
                new int[]{android.R.attr.state_pressed}
        };
        int[] colors = new int[]{
                Color.parseColor(colorPrimary),
                Color.parseColor(colorPrimary)
        };
        return new ColorStateList(states, colors);
    }


    /**
     * Handles OS backpress. if softkeyboard is shown it hides other wise backpress call back wil be called.
     *
     * @param event KeyEvent
     * @return false
     */
    @Override
    public boolean dispatchKeyEventPreIme(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
            if (!isKeyboardShown) {
                if (listener != null) {
                    listener.onNPSBackPress();
                    return true;
                }
            } else {
                isKeyboardShown = false;
                return false;
            }

        }
        return false;
    }

    /**
     * Sets language Listener
     */
    public void setLanguageListener() {
        loyagramCampaignView.setLanguageChangeListener(new LoyagramCampaignView.LanguageChangeListener() {
            @Override
            public void onLanguageChanged(Language language) {
                changeLanguage(language);
            }
        });

    }


    public void setSubmitListener() {
        loyagramCampaignView.setSubmitPressListener(new LoyagramCampaignView.SubmitPressListener() {
            @Override
            public void onSubmitPressed(int iterator) {
                switch (iterator) {
                    case 0:
                        showFollowUp();
                        break;
                    case 1:
                        showReasonView();
                        break;
                    case 2:
                        break;
                }
                //showReasonView(6);

            }

            @Override
            public void onPreviousPressed(int iterator) {
                switch (iterator) {
                    case 0:
                        break;
                    case 1:
                        llFollowUpContainer.setVisibility(GONE);
                        llRatingContainer.setVisibility(VISIBLE);
                        txtNPSQuestion.setVisibility(VISIBLE);
                        break;
                    case 2:
                        //llNPSFeedback.setVisibility(GONE);
                        rrReasonContainer.setVisibility(GONE);
                        showFollowUp();
                        break;
                }
            }
        });
    }

    /**
     * Change language when user changes
     *
     * @param language curent language
     */
    public void changeLanguage(Language language) {
        this.language = language;
        setQuestion();
        setLikelyText();
        if (currentRating != null) {
            setRatingText();
        }

        if (campaignType.equals("NPS")) {
            if (responseAnswer != null) {
                setFeedbackQuestion();
            }
        }
        if (followUpQuestion != null) {
            setFollowUpQuestion();
            changeLabelLanguage();
        }
    }

    /**
     * Sets NPS question based on current language
     */
    public void setQuestion() {
        String langcode = language.getCode();
        Boolean isTextChanged = false;
        List<QuestionTranslations> questionTranslations = question.getTranslations();
        if (questionTranslations != null) {
            for (QuestionTranslations questionTranslation : questionTranslations) {
                if (questionTranslation.getCode() != null && questionTranslation.getCode().equals(langcode)) {
                    if (questionTranslation.getTranslation() == null || questionTranslation.getTranslation().isEmpty()) {
                        break;
                    }
                    isTextChanged = true;
                    txtNPSQuestion.setText(questionTranslation.getTranslation());
                    break;
                }
            }
            if (!isTextChanged) {
                setQuestionToPrimaryLang();
            }
        }
    }

    public void setQuestionToPrimaryLang() {
        String langcode = primaryLanguage.getCode();
        List<QuestionTranslations> questionTranslations = question.getTranslations();
        for (QuestionTranslations questionTranslation : questionTranslations) {
            if (questionTranslation.getCode() != null && questionTranslation.getCode().equals(langcode)) {
                txtNPSQuestion.setText(questionTranslation.getTranslation());
                break;
            }
        }
    }

    /**
     * Sets NPS feedback questions based on NPS settings
     */
    public void setFeedbackQuestion() {
        String langcode = language.getCode();
        Boolean isTextChanged = false;
        List<SettingsTranslation> settingsTranslations = question.getSettingsTranslation();
        if (settingsTranslations != null) {
            for (SettingsTranslation sT : settingsTranslations) {
                if (sT.getCode() != null && sT.getCode().equals(langcode)) {
                    RequestReasonSettings rRS = sT.getSettingsBase().getSettings().getNpsSettings().getRequestReasonSettings();
                    if (rRS != null) {
                        if (rRS.getAll() != null && rRS.getAll().getMessage() != null) {
                            txtFeedbackQestion.setText(rRS.getAll().getMessage());
                            isTextChanged = true;
                            break;
                        }
                    }
                    /*
                    if (question.getSettings().getNpsSettings().getRequestReasonSettings().getType().equals("all")) {
                        txtFeedbackQestion.setText(rRS.getAll().getMessage());
                        isTextChanged = true;
                        break;
                    } else if (question.getSettings().getNpsSettings().getRequestReasonSettings().getType().equals("score")) {
                        if (responseAnswer.getValue().intValueExact() <= 6) {
                            txtFeedbackQestion.setText(rRS.getCustom().getDetractors().getMessage());
                            isTextChanged = true;
                            break;
                        } else if (responseAnswer.getValue().intValueExact() <= 8) {
                            txtFeedbackQestion.setText(rRS.getCustom().getPassives().getMessage());
                            isTextChanged = true;
                            break;
                        } else {
                            txtFeedbackQestion.setText(rRS.getCustom().getPromoters().getMessage());
                            isTextChanged = true;
                            break;
                        }
                    }
                    */
                }
            }
            if (!isTextChanged) {
                setFeedbackQuestionToPrimaryLang();
            }
        }
    }

    public void setFeedbackQuestionToPrimaryLang() {
        String langcode = primaryLanguage.getCode();
        List<SettingsTranslation> settingsTranslations = question.getSettingsTranslation();
        for (SettingsTranslation sT : settingsTranslations) {
            if (sT.getCode() != null && sT.getCode().equals(langcode)) {

                RequestReasonSettings rRS = sT.getSettingsBase().getSettings().getNpsSettings().getRequestReasonSettings();
                if (rRS != null) {
                    if (rRS.getAll() != null && rRS.getAll().getMessage() != null) {
                        txtFeedbackQestion.setText(rRS.getAll().getMessage());
                        break;
                    }
                }
                    /*
                    if (rRS != null) {
                        if (question.getSettings().getNpsSettings().getRequestReasonSettings().getType().equals("all")) {
                            txtFeedbackQestion.setText(rRS.getAll().getMessage());
                            break;
                        } else if (question.getSettings().getNpsSettings().getRequestReasonSettings().getType().equals("score")) {
                            if (responseAnswer.getValue().intValueExact() <= 6) {
                                txtFeedbackQestion.setText(rRS.getCustom().getDetractors().getMessage());
                                break;
                            } else if (responseAnswer.getValue().intValueExact() <= 8) {
                                txtFeedbackQestion.setText(rRS.getCustom().getPassives().getMessage());
                                break;
                            } else {
                                txtFeedbackQestion.setText(rRS.getCustom().getPromoters().getMessage());
                                break;
                            }
                        }

                    }
                */
            }
        }
    }


    public void setFollowUpQuestion() {
        String langcode = language.getCode();
        List<QuestionTranslations> questionTranslations = followUpQuestion.getTranslations();
        Boolean isTextChanged = false;
        if (questionTranslations != null) {
            for (QuestionTranslations questionTranslation : questionTranslations) {
                if (questionTranslation.getCode() != null && questionTranslation.getCode().equals(langcode)) {
                    if (questionTranslation.getTranslation() == null || questionTranslation.getTranslation().isEmpty()) {
                        break;
                    }
                    isTextChanged = true;
                    txtFollowUpQstn.setText(questionTranslation.getTranslation());
                    break;
                }
            }
            if (!isTextChanged) {
                setFollowUpQuestiontoPrimary();
            }
        }
    }

    public void setFollowUpQuestiontoPrimary() {
        String langcode = primaryLanguage.getCode();
        List<QuestionTranslations> questionTranslations = followUpQuestion.getTranslations();
        for (QuestionTranslations questionTranslation : questionTranslations) {
            if (questionTranslation.getCode() != null && questionTranslation.getCode().equals(langcode)) {
                txtFollowUpQstn.setText(questionTranslation.getTranslation());
                break;
            }
        }
    }

    /**
     * Sets Likely and not likely text with corresponding language
     */
    public void setLikelyText() {
        String langcode = language.getCode();
        Boolean isTextChanged = false;
        List<SettingsTranslation> settingsTranslations = question.getSettingsTranslation();
        if (settingsTranslations != null) {
            for (SettingsTranslation settingsTranslation : settingsTranslations) {
                if (settingsTranslation.getCode() != null && settingsTranslation.getCode().equals(langcode)) {
                    isTextChanged = true;
                    NpsSettings npsSettings = settingsTranslation.getSettingsBase().getSettings().getNpsSettings();
                    Widget widget = npsSettings.getWidget();
                    if (widget.getVeryLikely() == null || widget.getVeryLikely().isEmpty() || widget.getNotLikely() == null || widget.getNotLikely().isEmpty()) {
                        setLikelyToPrimaryLang();
                        break;
                    }
                    if (widget.getNotLikely() != null && widget.getVeryLikely() != null) {
                        txtNotLikely.setText(widget.getNotLikely());
                        txtVeryLikely.setText(widget.getVeryLikely());
                    }
                    break;
                }
            }
            if (!isTextChanged) {
                setLikelyToPrimaryLang();
            }
        }
    }

    public void setLikelyToPrimaryLang() {
        String langcode = primaryLanguage.getCode();
        List<SettingsTranslation> settingsTranslations = question.getSettingsTranslation();
        for (SettingsTranslation settingsTranslation : settingsTranslations) {
            if (settingsTranslation.getCode() != null && settingsTranslation.getCode().equals(langcode)) {
                NpsSettings npsSettings = settingsTranslation.getSettingsBase().getSettings().getNpsSettings();
                Widget widget = npsSettings.getWidget();
                if (widget.getVeryLikely() == null || widget.getVeryLikely().isEmpty() || widget.getNotLikely() == null || widget.getNotLikely().isEmpty()) {
                    break;
                }
                if (widget.getNotLikely() != null && widget.getVeryLikely() != null) {
                    txtNotLikely.setText(widget.getNotLikely());
                    txtVeryLikely.setText(widget.getVeryLikely());
                }
                break;
            }
        }
    }

    /**
     * Returns type face
     *
     * @return type face
     */
    public Typeface getTypeFace() {
        return typeface;
    }

    public void setRatingText() {
        String ratingMsg = statictextes.get("SCORE_MESSAGE_TEXT");
        ratingMsg = ratingMsg.replaceAll("\\{" + "8" + "\\}", currentRating).replaceAll("\\{" + "10" + "\\}", "10");
//        try {
//            SpannableStringBuilder builder = new SpannableStringBuilder();
//            SpannableString colorSpannable = new SpannableString(ratingMsg);
//            //Get index of rating and max value sub string
//            int index = ratingMsg.lastIndexOf("10");
//            int curratingindex = ratingMsg.indexOf(currentRating);
//            colorSpannable.setSpan(new ForegroundColorSpan(Color.RED), index, index + 2, 0);
//            colorSpannable.setSpan(new ForegroundColorSpan(Color.parseColor("#1abc9c")), curratingindex, curratingindex + currentRating.length(), 0);
//            builder.append(colorSpannable);
//            txtRating.setText(builder, TextView.BufferType.SPANNABLE);
//        } catch (Exception ignored) {
//            txtRating.setText(ratingMsg);
//        }
        //txtRating.setText(ratingMsg);
    }

    /**
     * Changes Languge of the text label fields
     */
    public void changeLabelLanguage() {
        List<QuestionLabel> questionLabel = followUpQuestion.getLabels();
        Boolean isTextChanged = false;
        if (questionLabel != null) {
            for (QuestionLabel ql : questionLabel) {
                for (LabelTranslation labelTranslation : ql.getLabelTranslations()) {
                    if (language != null && language.getCode().equals(labelTranslation.getCode())) {
                        if (labelTranslation.getTranslation() == null || labelTranslation.getTranslation().isEmpty()) {
                            break;
                        }
                        isTextChanged = true;
                        String questionType = followUpQuestion.getType();
                        if (questionType.equals("SINGLE_SELECT")) {
                            RadioButton rdb = (RadioButton) findViewWithTag(ql.getId());
                            if (rdb != null) {
                                rdb.setText(labelTranslation.getTranslation());
                            }
                        } else {
                            AppCompatCheckBox chk = (AppCompatCheckBox) findViewWithTag(ql.getId());
                            if (chk != null) {
                                chk.setText(labelTranslation.getTranslation());
                            }
                        }
                    }
                }
            }
            if (!isTextChanged) {
                changeLabelLanguageToPrimary();
            }
        }
    }

    public void changeLabelLanguageToPrimary() {
        List<QuestionLabel> questionLabel = followUpQuestion.getLabels();
        for (QuestionLabel ql : questionLabel) {
            for (LabelTranslation labelTranslation : ql.getLabelTranslations()) {
                if (primaryLanguage != null && primaryLanguage.getCode().equals(labelTranslation.getCode())) {
                    String questionType = followUpQuestion.getType();
                    if (questionType.equals("SINGLE_SELECT")) {
                        RadioButton rdb = (RadioButton) findViewWithTag(ql.getId());
                        if (rdb != null) {
                            rdb.setText(labelTranslation.getTranslation());
                        }
                    } else {
                        AppCompatCheckBox chk = (AppCompatCheckBox) findViewWithTag(ql.getId());
                        if (chk != null) {
                            chk.setText(labelTranslation.getTranslation());
                        }
                    }
                }
            }
        }
    }

    public boolean isValidEmail(CharSequence target) {
        return target != null && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

}