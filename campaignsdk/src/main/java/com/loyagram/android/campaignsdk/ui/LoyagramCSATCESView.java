package com.loyagram.android.campaignsdk.ui;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Handler;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.AppCompatRadioButton;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
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
import com.loyagram.android.campaignsdk.models.csatcezsettings.CsatCesCustomSettings;
import com.loyagram.android.campaignsdk.models.csatcezsettings.CsatCesReasonSettings;
import com.loyagram.android.campaignsdk.models.npssettings.SettingsTranslation;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;


public class LoyagramCSATCESView extends LinearLayout {

    interface LoyagramCSATCESListener {
        void onCSATCESBackPress();

        void setOptions(String option);

        void hideSubmitButton(Boolean show);

        void onCSATCESSubmit(Boolean isSubmit);
    }

    Response response;
    Question question;
    Question followUpQuestion;
    Context currentContext;
    Language primaryLanguage;
    Language currentLanguage;
    String colorPrimary;
    LoyagramCampaignView loyagramCampaignView;
    LoyagramCSATCESListener listener;
    TextView txtquestion;
    TextView txtRetry;
    TextView txtOptions;
    TextView txtFollowUpQstn;
    EditText txtReason;
    TextView txtFeedbackQuestion;
    LinearLayout llFeedbackContainer;
    LinearLayout llOptionsContainer;
    LinearLayout llfolloUpOptions;
    LinearLayout llfollowUpContainer;
    RelativeLayout rrReasonFooter;
    Typeface typeface;
    Boolean isKeyboardShown = false;
    Boolean isCsat;
    Boolean isFollowUpEnabled = false;
    String currentOption;
    HashMap<String, String> staticTextes;
    ScrollView topOptionsContainer;

    public LoyagramCSATCESView(Context context, AttributeSet attr) {
        super(context, attr);
        init(context);
    }

    public LoyagramCSATCESView(Context context, Question question, Question followUpQuestion, Boolean isFollowUpEnabled, Response response, LoyagramCampaignView loyagramCampaignView, String colorPrimary, Language language, Language primaryLanguage, Boolean isCsat, HashMap<String, String> staticTextes) {
        super(context);
        this.response = response;
        this.question = question;
        this.followUpQuestion = followUpQuestion;
        this.isFollowUpEnabled = isFollowUpEnabled;
        this.colorPrimary = colorPrimary;
        this.loyagramCampaignView = loyagramCampaignView;
        this.primaryLanguage = primaryLanguage;
        this.currentLanguage = language;
        this.isCsat = isCsat;
        this.staticTextes = staticTextes;
        init(context);

    }

    public void setCSATCESListener(LoyagramCSATCESListener listener) {
        this.listener = listener;
    }

    public void init(Context context) {
        View.inflate(context, R.layout.loyagram_csat_cesview, this);
        this.currentContext = context;
        initLayout();
        setTheme();
    }

    /**
     * Method to set the layout theme. Theme will either from API request or pass via argument.
     */
    public void setTheme() {
        if (colorPrimary != null) {
            int stroke = getResources().getDimensionPixelSize(R.dimen.stroke_width);
            ((GradientDrawable) txtReason.getBackground()).setStroke(stroke, Color.parseColor(colorPrimary));
            txtRetry.setTextColor(Color.parseColor(colorPrimary));
        }

    }

    public void initLayout() {
        txtquestion = (TextView) findViewById(R.id.csatcesQuestion);
        txtRetry = (TextView) findViewById(R.id.btnRetry);
        txtReason = (EditText) findViewById(R.id.txtReason);
        txtOptions = (TextView) findViewById(R.id.txtOptions);
        txtFeedbackQuestion = (TextView) findViewById(R.id.txtFeedbackQstn);
        txtFollowUpQstn = (TextView) findViewById(R.id.followupQstn);
        llFeedbackContainer = (LinearLayout) findViewById(R.id.feedbackHeader);
        rrReasonFooter = (RelativeLayout) findViewById(R.id.reasonFooter);
        llOptionsContainer = (LinearLayout) findViewById(R.id.optionsContainer);
        llfollowUpContainer = (LinearLayout) findViewById(R.id.followupContainer);
        llfolloUpOptions = (LinearLayout) findViewById(R.id.followUpOptionsContainer);
        topOptionsContainer = (ScrollView) findViewById(R.id.topOptionsContainer);
        if (getTypeface() != null) {
            txtRetry.setTypeface(typeface);
            txtFeedbackQuestion.setTypeface(typeface);
            txtquestion.setTypeface(typeface);
            txtReason.setTypeface(typeface);
            txtOptions.setTypeface(typeface);
        }
        setQuestion();
        showOptions();
        setLanguageListener();
        setSubmitListener();
//        txtRetry.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (listener != null) {
//                    listener.hideSubmitButton(true);
//                }
//                changeOptions();
//            }
//        });
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
                    listener.onCSATCESSubmit(true);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


    }

    public Typeface getTypeface() {
        if (typeface == null) {
            typeface = Typeface.createFromAsset(currentContext.getAssets(), "fonts/ProximaNova-Regular.otf");
            return typeface;
        } else {
            return typeface;
        }
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
            }

            @Override
            public void onPreviousPressed(int iterator) {
                switch (iterator) {
                    case 0:
                        break;
                    case 1:
                        llfollowUpContainer.setVisibility(GONE);
                        llOptionsContainer.setVisibility(VISIBLE);
                        txtquestion.setVisibility(VISIBLE);
                        break;
                    case 2:
                        llFeedbackContainer.setVisibility(GONE);
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
        this.currentLanguage = language;
        setQuestion();
        changeLabelLanguage();
        if (currentOption != null) {
            setFeedbackQuestion();
            setOptionText();
        }
        if (isFollowUpEnabled) {
            setFollowUpQuestion();
            changeFollowUPLabelLanguage();
        }
    }

    public void setQuestion() {
        String langcode = currentLanguage.getCode();
        Boolean isTextChanged = false;
        List<QuestionTranslations> questionTranslations = question.getTranslations();
        for (QuestionTranslations questionTranslation : questionTranslations) {
            if (questionTranslation.getCode() != null && questionTranslation.getCode().equals(langcode)) {
                if (questionTranslation.getTranslation() == null || questionTranslation.getTranslation().isEmpty()) {
                    break;
                }
                isTextChanged = true;
                txtquestion.setText(questionTranslation.getTranslation());
                break;
            }
        }
        if (!isTextChanged) {
            setQuestionToPrimaryLang();
        }
    }

    public void setQuestionToPrimaryLang() {
        String langcode = primaryLanguage.getCode();
        List<QuestionTranslations> questionTranslations = question.getTranslations();
        for (QuestionTranslations questionTranslation : questionTranslations) {
            if (questionTranslation.getCode() != null && questionTranslation.getCode().equals(langcode)) {
                txtquestion.setText(questionTranslation.getTranslation());
                break;
            }
        }
    }

    public void showOptions() {
        llOptionsContainer.removeAllViews();
        final RadioGroup radioGroup = new RadioGroup(currentContext);
        List<QuestionLabel> questionLabels = question.getLabels();
        LinearLayout.LayoutParams rdgParams = new LinearLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        radioGroup.setLayoutParams(rdgParams);
        radioGroup.setOrientation(RadioGroup.VERTICAL);
        rdgParams.setMargins(0, 0, 0, 0);
        for (final QuestionLabel ql : questionLabels) {
            AppCompatRadioButton rdb = new AppCompatRadioButton(currentContext);
            LinearLayout.LayoutParams buttonLayoutParams = new LinearLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            rdb.setLayoutParams(buttonLayoutParams);
            rdb.setTag(ql.getId());
            rdb.setGravity(Gravity.CENTER);
            if (getTypeface() != null) {
                rdb.setTypeface(typeface);
            }
            ResponseAnswer ra = getResponseAnswerByLAbel(ql.getId());
            Boolean isLabelSet = false;
            for (LabelTranslation labelTranslation : ql.getLabelTranslations()) {
                if (currentLanguage != null && currentLanguage.getCode().equals(labelTranslation.getCode())) {
                    rdb.setText(labelTranslation.getTranslation());
                    isLabelSet = true;
                    break;
                }
            }

            if (!isLabelSet) {
                for (LabelTranslation labelTranslation : ql.getLabelTranslations()) {
                    if (primaryLanguage != null && primaryLanguage.getCode().equals(labelTranslation.getCode())) {
                        rdb.setText(labelTranslation.getTranslation());
                        break;
                    }
                }
            }

            if (ra != null && ra.getQuestionLabelId().equals(ql.getId())) {
                if (ra.getValue() != null && ra.getValue().equals(new BigDecimal(1))) // 1 equals true
                    rdb.setChecked(true);
            }
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                rdb.setButtonTintList(getColorStateList());
            } else {
                rdb.setSupportButtonTintList(getColorStateList());
            }
            rdb.setId(Integer.parseInt(ql.getId().toString()));
            rdb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    final RadioButton radioButton = (RadioButton) findViewWithTag(ql.getId());
                    ResponseAnswer sa = setCSATCESResponse(ql.getId(), new BigDecimal(1));
                    currentOption = ql.getName();
                    if (listener != null) {
                        listener.onCSATCESSubmit(true);
                        listener.hideSubmitButton(false);

                        String option = "";
                        switch (ql.getName()) {
                            case "very_dissatisfied":
                                option = "dissatisfied";
                                break;
                            case "somewhat_dissatisfied":
                                option = "dissatisfied";
                                break;
                            case "neither_satisfied_nor_dissatisfied":
                                option = "neutral";
                                break;
                            case "somewhat_satisfied":
                                option = "satisfied";
                                break;
                            case "very_satisfied":
                                option = "satisfied";
                                break;
                            case "very_easy":
                                option = "agree";
                                break;
                            case "easy":
                                option = "agree";
                                break;
                            case "neither_easy_nor_difficult":
                                option = "neutral";
                                break;
                            case "difficult":
                                option = "disagree";
                                break;
                            case "very_difficult":
                                option = "disagree";
                                break;
                        }

                        listener.setOptions(option);
                    }

                    //Shows feedback view with a delay
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (radioButton != null) {
                                // showReasontitle(radioButton.getText().toString());
                            }
                            //showReasonView();

                        }
                    }, 100);


                }
            });
            radioGroup.addView(rdb);
        }
        llOptionsContainer.addView(radioGroup);
        if (loyagramCampaignView != null) {
            loyagramCampaignView.showSubView(true);
            loyagramCampaignView.hideProgress();
        }

    }

    public void showFollowUp() {
        setFollowUpQuestion();
        llOptionsContainer.setVisibility(GONE);
        txtquestion.setVisibility(GONE);
        llfolloUpOptions.removeAllViews();
        llfollowUpContainer.setVisibility(VISIBLE);
        List<QuestionLabel> questionLabels = followUpQuestion.getLabels();
        for (final QuestionLabel ql : questionLabels) {
            final AppCompatCheckBox chk = new AppCompatCheckBox(currentContext);
            LinearLayout.LayoutParams buttonLayoutParams = new LinearLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            buttonLayoutParams.setMargins(0, 0, 0, 0);
            chk.setLayoutParams(buttonLayoutParams);
            chk.setTag(ql.getId());
            if (getTypeface() != null) {
                chk.setTypeface(typeface);
            }
            Boolean isLabelSet = false;
            for (LabelTranslation labelTranslation : ql.getLabelTranslations()) {
                if (currentLanguage != null && currentLanguage.getCode().equals(labelTranslation.getCode())) {
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
                if (responseAnswer.getValue() != null && responseAnswer.getValue().equals(new BigDecimal(1))) // 1 eaquals true
                    chk.setChecked(true);
            }
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                chk.setButtonTintList(getColorStateList());
            } else {
                chk.setSupportButtonTintList(getColorStateList());
            }
            if (loyagramCampaignView != null) {
                loyagramCampaignView.showSubView(true);
                loyagramCampaignView.hideProgress();
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
                        listener.onCSATCESSubmit(true);
                    }
                }
            });
            llfolloUpOptions.addView(chk);
        }
    }

    public void setFollowUpQuestion() {
        String langcode = currentLanguage.getCode();
        List<QuestionTranslations> questionTranslations = followUpQuestion.getTranslations();
        Boolean isTextChanged = false;
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


    public void changeLabelLanguage() {
        List<QuestionLabel> questionLabel = question.getLabels();
        Boolean isTextChanged = false;
        for (QuestionLabel ql : questionLabel) {
            for (LabelTranslation labelTranslation : ql.getLabelTranslations()) {
                if (currentLanguage != null && currentLanguage.getCode().equals(labelTranslation.getCode())) {
                    if (labelTranslation.getTranslation() == null || labelTranslation.getTranslation().isEmpty()) {
                        break;
                    }
                    isTextChanged = true;
                    RadioButton rdb = (RadioButton) findViewWithTag(ql.getId());
                    if (rdb != null) {
                        rdb.setText(labelTranslation.getTranslation());
                    }
                }
            }
        }
        if (!isTextChanged) {
            changeLabelLanguageToPrimary();
        }
    }

    public void changeLabelLanguageToPrimary() {
        List<QuestionLabel> questionLabel = question.getLabels();
        for (QuestionLabel ql : questionLabel) {
            for (LabelTranslation labelTranslation : ql.getLabelTranslations()) {
                if (primaryLanguage != null && primaryLanguage.getCode().equals(labelTranslation.getCode())) {
                    RadioButton rdb = (RadioButton) findViewWithTag(ql.getId());
                    if (rdb != null) {
                        rdb.setText(labelTranslation.getTranslation());
                    }
                }
            }
        }
    }

    /**
     * Changes Language of the text label fields
     */
    public void changeFollowUPLabelLanguage() {
        List<QuestionLabel> questionLabel = followUpQuestion.getLabels();
        Boolean isTextChanged = false;
        for (QuestionLabel ql : questionLabel) {
            for (LabelTranslation labelTranslation : ql.getLabelTranslations()) {
                if (currentLanguage != null && currentLanguage.getCode().equals(labelTranslation.getCode())) {
                    if (labelTranslation.getTranslation() == null || labelTranslation.getTranslation().isEmpty()) {
                        break;
                    }
                    isTextChanged = true;
                    String questionType = followUpQuestion.getType();
                    if (questionType.equals("SINGLE_SELECT")) {
                        RadioButton rdb = (RadioButton) findViewWithTag(ql.getId());
                        rdb.setText(labelTranslation.getTranslation());
                    } else {
                        CheckBox chk = (CheckBox) findViewWithTag(ql.getId());
                        if (chk != null) {
                            chk.setText(labelTranslation.getTranslation());
                        }
                    }
                }
            }
        }
        if (!isTextChanged) {
            changeFollowUpLabelLanguageToPrimary();
        }
    }

    public void changeFollowUpLabelLanguageToPrimary() {
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
                        CheckBox chk = (CheckBox) findViewWithTag(ql.getId());
                        if (chk != null) {
                            chk.setText(labelTranslation.getTranslation());
                        }
                    }
                }
            }
        }
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

    public ResponseAnswer getResponseAnswerByLAbel(BigDecimal id) {
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
     * @param qid question Id
     * @return response answer list
     */
    public List<ResponseAnswer> getResponseAnswerByQId(BigDecimal qid) {
        List<ResponseAnswer> answers = new ArrayList<ResponseAnswer>();
        if (response.getResponseAnswers() != null)
            for (ResponseAnswer ra : response.getResponseAnswers()) {
                if (ra != null && ra.getQuestionLabelId() != null) {
                    if (ra.getQuestionId().equals(qid)) {
                        answers.add(ra);
                    }
                }
            }
        return answers;
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

    private ResponseAnswer setCSATCESResponse(BigDecimal qsid, BigDecimal val) {
        List<ResponseAnswer> answers = getResponseAnswerByQId(question.getId());
        if (answers.size() != 0) {
            ResponseAnswer ra = answers.get(0);
            ra.setValue(qsid);
            ra.setQuestionLabelId(qsid);
            return ra;
        } else {
            ResponseAnswer ra = getNewResponseAnswer();
            ra.setQuestionLabelId(qsid);
            ra.setValue(qsid);
            ra.setAt(System.currentTimeMillis());
            ResponseAnswerText txt = new ResponseAnswerText();
            txt.setCampaignedAnswerId(ra.getId());
            ra.setResponseAnswerText(txt);
            response.getResponseAnswers().add(ra);
            return ra;
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
            if(val.compareTo(BigDecimal.ONE)==1) {
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


    public void showReasonView() {
        setFeedbackQuestion();
        txtquestion.setVisibility(GONE);
        llfollowUpContainer.setVisibility(GONE);
        llFeedbackContainer.setVisibility(VISIBLE);
        // topOptionsContainer.setVisibility(VISIBLE);

    }

    public void changeOptions() {
        llFeedbackContainer.setVisibility(GONE);
        //rrReasonFooter.setVisibility(GONE);
        topOptionsContainer.setVisibility(VISIBLE);
        txtquestion.setVisibility(VISIBLE);

    }

    public void showReasontitle(String selectedItem) {

        setFeedbackQuestion();
        // setOptionText();
        //txtOptions.setText(selectedItem);
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
                    listener.onCSATCESBackPress();
                    return true;
                }
            } else {
                isKeyboardShown = false;
                return false;
            }

        }
        return false;
    }

    public void setFeedbackQuestion() {

        String langcode = currentLanguage.getCode();
        Boolean isTextChanged = false;
        String feedback = null;
        List<SettingsTranslation> settingsTranslations = question.getSettingsTranslation();
        for (SettingsTranslation sT : settingsTranslations) {
            if (sT.getCode() != null && sT.getCode().equals(langcode)) {
                CsatCesReasonSettings reasonSettings;
                CsatCesReasonSettings qReasonSettings;
                if (isCsat) {
                    reasonSettings = sT.getSettingsBase().getSettings().getCsatSettings().getRequestReasonSettings();
                    qReasonSettings = question.getSettings().getCsatSettings().getRequestReasonSettings();
                } else {
                    reasonSettings = sT.getSettingsBase().getSettings().getCesSettings().getRequestReasonSettings();
                    qReasonSettings = question.getSettings().getCesSettings().getRequestReasonSettings();
                }

                if (reasonSettings != null && reasonSettings.getAll() != null) {
                    feedback = reasonSettings.getAll().getMessage();
                    isTextChanged = true;
                    txtFeedbackQuestion.setText(feedback);
                }

               /*
                if (qReasonSettings.getType().equals("all")) {
                    if (reasonSettings.getAll() != null) {
                        feedback = reasonSettings.getAll().getMessage();
                        isTextChanged = true;
                        txtFeedbackQuestion.setText(feedback);
                    } else {
                        setFeedbackQuestionToPrimaryLang();
                    }
                    break;
                } else if (qReasonSettings.getType().equals("custom")) {
                    CsatCesCustomSettings customSettings = reasonSettings.getCustom();
                    if (customSettings != null && currentOption != null) {
                        switch (currentOption) {

                            case "very_dissatisfied":
                                feedback = customSettings.getVeryDissatisfied().getMessage();
                                break;

                            case "somewhat_dissatisfied":
                                feedback = customSettings.getSomewhatDissatisfied().getMessage();
                                break;

                            case "neither_satisfied_nor_dissatisfied":
                                feedback = customSettings.getNeitherSatisfiedNorDissatisfied().getMessage();
                                break;

                            case "somewhat_satisfied":
                                feedback = customSettings.getSomewhatSatisfied().getMessage();
                                break;

                            case "very_satisfied":
                                feedback = customSettings.getVerySatisfied().getMessage();
                                break;

                            case "neither_easy_nor_difficult":
                                feedback = customSettings.getNeither().getMessage();
                                break;

                            case "easy":
                                feedback = customSettings.getEasy().getMessage();
                                break;

                            case "difficult":
                                feedback = customSettings.getDifficult().getMessage();
                                break;
                            case "very_easy":
                                feedback = customSettings.getVeryEasy().getMessage();
                                break;

                            case "very_difficult":
                                feedback = customSettings.getVeryDifficult().getMessage();
                                break;

                        }
                        if (feedback != null) {
                            txtFeedbackQuestion.setText(feedback);
                            isTextChanged = true;
                        }
                    }
                    break;
                } */

            }
        }

        if (!isTextChanged) {
            setFeedbackQuestionToPrimaryLang();
        }
    }

    public void setFeedbackQuestionToPrimaryLang() {
        String langcode = primaryLanguage.getCode();
        List<SettingsTranslation> settingsTranslations = question.getSettingsTranslation();
        String feedback = null;
        for (SettingsTranslation sT : settingsTranslations) {
            if (sT.getCode() != null && sT.getCode().equals(langcode)) {
                CsatCesReasonSettings reasonSettings;
                CsatCesReasonSettings qReasonSettings;
                if (isCsat) {
                    reasonSettings = sT.getSettingsBase().getSettings().getCsatSettings().getRequestReasonSettings();
                    qReasonSettings = question.getSettings().getCsatSettings().getRequestReasonSettings();
                } else {
                    reasonSettings = sT.getSettingsBase().getSettings().getCesSettings().getRequestReasonSettings();
                    qReasonSettings = question.getSettings().getCesSettings().getRequestReasonSettings();
                }

                if (reasonSettings != null && reasonSettings.getAll() != null) {
                    feedback = reasonSettings.getAll().getMessage();
                    txtFeedbackQuestion.setText(feedback);
                }
                /*
                if (qReasonSettings.getType().equals("all")) {
                    if (reasonSettings.getAll() != null) {
                        feedback = reasonSettings.getAll().getMessage();
                        txtFeedbackQuestion.setText(feedback);
                        break;
                    }
                } else if (qReasonSettings.getType().equals("custom")) {
                    CsatCesCustomSettings customSettings = reasonSettings.getCustom();
                    if (customSettings != null) {
                        switch (currentOption) {

                            case "very_dissatisfied":
                                feedback = customSettings.getVeryDissatisfied().getMessage();
                                break;

                            case "somewhat_dissatisfied":
                                feedback = customSettings.getSomewhatDissatisfied().getMessage();
                                break;

                            case "neither_satisfied_nor_dissatisfied":
                                feedback = customSettings.getNeitherSatisfiedNorDissatisfied().getMessage();
                                break;

                            case "somewhat_satisfied":
                                feedback = customSettings.getSomewhatSatisfied().getMessage();
                                break;

                            case "very_satisfied":
                                feedback = customSettings.getVerySatisfied().getMessage();
                                break;
                            case "neither_easy_nor_difficult":
                                feedback = customSettings.getNeither().getMessage();
                                break;

                            case "easy":
                                feedback = customSettings.getEasy().getMessage();
                                break;

                            case "difficult":
                                feedback = customSettings.getDifficult().getMessage();
                                break;
                            case "very_easy":
                                feedback = customSettings.getVeryEasy().getMessage();
                                break;

                            case "very_difficult":
                                feedback = customSettings.getVeryDifficult().getMessage();
                                break;
                        }
                    }
                    if (feedback != null) {
                        txtFeedbackQuestion.setText(feedback);
                    }
                    break;

                }
                */
            }
        }
    }

    public void setOptionText() {
        txtRetry.setText(staticTextes.get("CHANGE_SCORE_BUTTON_TEXT"));
        List<QuestionLabel> questionLabels = question.getLabels();
        for (final QuestionLabel ql : questionLabels) {
            if (ql.getName().equals(currentOption)) {
                for (LabelTranslation labelTranslation : ql.getLabelTranslations()) {
                    if (currentLanguage != null && currentLanguage.getCode().equals(labelTranslation.getCode())) {
                        txtOptions.setText(labelTranslation.getTranslation());
                        break;
                    }
                }
                break;
            }
        }

    }

}
