package com.loyagram.android.campaignsdk.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.AppCompatRadioButton;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Layout class for question type survey. Survey layout will be either single choice or multiple choice.
 * Responses will be saved whenever the rating changes
 */

@SuppressWarnings("SpellCheckingInspection")
public class LoyagramSurveyView extends RelativeLayout {

    interface LoyagramSurveyListener {
        void onSurveyBackPress();

        void onSurveySubmit(Response response);
    }

    Language language = null;
    Question question;
    Response response;
    ResponseAnswer responseAnswer;
    LoyagramSurveyListener listener;
    LinearLayout lloptionsContainer;
    TextView txtQuestion;
    Context currentContext;
    LoyagramCampaignView loyagramCampaignView = null;
    String colorPrimary = null;
    Typeface typeface;
    Language primaryLanguage;

    public LoyagramSurveyView(Context context, Question question, Response response, LoyagramCampaignView loyagramCampaignView, String colorPrimary, Language language, Language primaryLanguage) {
        super(context);
        this.response = response;
        this.question = question;
        this.loyagramCampaignView = loyagramCampaignView;
        this.colorPrimary = colorPrimary;
        this.language = language;
        this.primaryLanguage = primaryLanguage;
        init(context);
    }

    public LoyagramSurveyView(Context context, AttributeSet attr) {
        super(context, attr);
        init(context);
    }

    public LoyagramSurveyView(Context context, AttributeSet attr, int defStyle) {
        super(context, attr, defStyle);
        init(context);
    }

    public void setSurveyListener(LoyagramSurveyListener eventListener) {
        listener = eventListener;
    }

    public void init(Context context) {
        View.inflate(context, R.layout.loyagram_surveyview, this);
        currentContext = context;
        typeface = Typeface.createFromAsset(currentContext.getAssets(), "fonts/ProximaNova-Regular.otf");
        this.setFocusableInTouchMode(true);
        this.requestFocus();
        initLayouts();
        setLanguageListener();
        overrideBackPress();
    }

    public void initLayouts() {
        lloptionsContainer = findViewById(R.id.optionsContainer);
        txtQuestion = findViewById(R.id.qstnTitle);
        if (getTypeFace() != null) {
            txtQuestion.setTypeface(typeface);
        }
        String questionType = question.getType();
        if (questionType.equals("SINGLE_SELECT")) {
            showSingleSelect();
        } else {
            showMultiSelect();
        }
    }

    /**
     * shows multiple choice layouts. checkboxes for each labels will be created dynamically.
     */
    @SuppressLint("RestrictedApi")
    public void showMultiSelect() {
        lloptionsContainer.removeAllViews();
        setQuestionText();
        List<QuestionLabel> questionLabels = question.getLabels();
        if (questionLabels != null) {
            for (final QuestionLabel ql : questionLabels) {
                final AppCompatCheckBox chk = new AppCompatCheckBox(currentContext);
                LinearLayout.LayoutParams buttonLayoutParams = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
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
                ResponseAnswer responseAnswer = getResponseAnswer(ql.getId());
                if (responseAnswer != null && responseAnswer.getQuestionLabelId().equals(ql.getId())) {
                    if (responseAnswer.getValue() != null && responseAnswer.getValue().equals(ql.getId())) // 1 eaquals true
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
                            listener.onSurveySubmit(response);
                        }
                    }
                });
                lloptionsContainer.addView(chk);
            }
        }
        if (loyagramCampaignView != null) {
            loyagramCampaignView.showSubView(true);
            loyagramCampaignView.hideProgress();
        }
    }

    /**
     * shows single choice question layouts. Radio buttons for each labels will be created dynamically.
     */
    @SuppressLint("RestrictedApi")
    public void showSingleSelect() {
        lloptionsContainer.removeAllViews();
        setQuestionText();
        RadioGroup radioGroup = new RadioGroup(currentContext);
        List<QuestionLabel> questionLabels = question.getLabels();
        LinearLayout.LayoutParams rdgParams = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        radioGroup.setLayoutParams(rdgParams);
        radioGroup.setOrientation(RadioGroup.VERTICAL);
        rdgParams.setMargins(0, 0, 0, 0);
        if (questionLabels != null) {
            for (final QuestionLabel ql : questionLabels) {
                AppCompatRadioButton rdb = new AppCompatRadioButton(currentContext);
                LinearLayout.LayoutParams buttonLayoutParams = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
                rdb.setLayoutParams(buttonLayoutParams);
                rdb.setTag(ql.getId());
                if (getTypeFace() != null) {
                    rdb.setTypeface(typeface);
                }
                ResponseAnswer ra = getResponseAnswer(ql.getId());
                Boolean isLabelSet = false;
                for (LabelTranslation labelTranslation : ql.getLabelTranslations()) {
                    if (language != null && language.getCode().equals(labelTranslation.getCode())) {
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
                    if (ra.getValue() != null && ra.getValue().equals(ql.getId())) // 1 eaquals true
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
                        ResponseAnswer sa = setSingleSelectResponse(ql.getId(), new BigDecimal(1));
                        if (listener != null) {
                            listener.onSurveySubmit(response);
                        }
                    }
                });
                radioGroup.addView(rdb);
            }
        }
        lloptionsContainer.addView(radioGroup);
        if (loyagramCampaignView != null) {
            loyagramCampaignView.showSubView(true);
            loyagramCampaignView.hideProgress();
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
        ResponseAnswer resAnswer = getResponseAnswer(questionId);
        if (resAnswer != null) {
            if (val.compareTo(BigDecimal.ONE) == 1) {
                resAnswer.setValue(questionId);
                return resAnswer;
            } else {
                response.getResponseAnswers().remove(resAnswer);
                return null;
            }
        } else {
            ResponseAnswer responseAnswer = getNewResponseAnswer();
            responseAnswer.setQuestionLabelId(questionId);
            responseAnswer.setValue(questionId);
            responseAnswer.setAt(System.currentTimeMillis());
            response.getResponseAnswers().add(responseAnswer);
            return responseAnswer;
        }
    }

    /**
     * Set single choice response answer
     *
     * @param qsid question Id
     * @param val  value
     * @return response answer
     */
    private ResponseAnswer setSingleSelectResponse(BigDecimal qsid, BigDecimal val) {
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
            response.getResponseAnswers().add(ra);
            return ra;
        }
    }

    /**
     * @param qid question Id
     * @return response annwer list
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
     * Returns the current response answer
     *
     * @param id response answer id
     * @return response answer
     */
    public ResponseAnswer getResponseAnswer(BigDecimal id) {
        if (response.getResponseAnswers() != null)
            for (ResponseAnswer ra : response.getResponseAnswers()) {
                if (ra != null && ra.getQuestionLabelId() != null) {
                    if (ra.getQuestionLabelId().equals(id)) {
                        return ra;
                    }
                }
            }
        return null;
    }

    /**
     * Return new Response Answer object.
     *
     * @return response answer
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
     * Handles OS backpress. Onbackpress call back will be called if the listener is not null.
     */
    public void overrideBackPress() {
        this.setOnKeyListener(new OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
                    if (listener != null) {
                        listener.onSurveyBackPress();
                    }
                    return true;
                }
                return false;
            }
        });
    }

    /**
     * Sets language change listener
     */
    public void setLanguageListener() {
        loyagramCampaignView.setLanguageChangeListener(new LoyagramCampaignView.LanguageChangeListener() {
            @Override
            public void onLanguageChanged(Language language) {
                changeLanguage(language);
            }
        });

    }

    /**
     * Changes the campaign language
     *
     * @param language selected language
     */
    public void changeLanguage(Language language) {
        this.language = language;
        setQuestionText();
        changeLabelLanguage();
    }

    /**
     * Sets Question titile with selected language
     */

    public void setQuestionText() {
        String langcode = language.getCode();
        List<QuestionTranslations> questionTranslations = question.getTranslations();
        Boolean isTextChanged = false;
        if (questionTranslations != null) {
            for (QuestionTranslations questionTranslation : questionTranslations) {
                if (questionTranslation.getCode() != null && questionTranslation.getCode().equals(langcode)) {
                    if (questionTranslation.getTranslation() == null || questionTranslation.getTranslation().isEmpty()) {
                        break;
                    }
                    isTextChanged = true;
                    txtQuestion.setText(questionTranslation.getTranslation());
                    break;
                }
            }
            if (!isTextChanged) {
                setQuestionTextToPrimary();
            }
        }
    }

    public void setQuestionTextToPrimary() {
        String langcode = primaryLanguage.getCode();
        List<QuestionTranslations> questionTranslations = question.getTranslations();
        for (QuestionTranslations questionTranslation : questionTranslations) {
            if (questionTranslation.getCode() != null && questionTranslation.getCode().equals(langcode)) {
                txtQuestion.setText(questionTranslation.getTranslation());
                break;
            }
        }
    }

    /**
     * Changes Languge of the text label fields
     */
    public void changeLabelLanguage() {
        List<QuestionLabel> questionLabel = question.getLabels();
        Boolean isTextChanged = false;
        if (questionLabel != null) {
            for (QuestionLabel ql : questionLabel) {
                List<LabelTranslation> labelTranslations = ql.getLabelTranslations();
                if (labelTranslations != null) {
                    for (LabelTranslation labelTranslation : labelTranslations) {
                        if (language != null && language.getCode().equals(labelTranslation.getCode())) {
                            if (labelTranslation.getTranslation() == null || labelTranslation.getTranslation().isEmpty()) {
                                break;
                            }
                            isTextChanged = true;
                            String questionType = question.getType();
                            if (questionType.equals("SINGLE_SELECT")) {
                                RadioButton rdb = findViewWithTag(ql.getId());
                                rdb.setText(labelTranslation.getTranslation());
                            } else {
                                CheckBox chk = findViewWithTag(ql.getId());
                                chk.setText(labelTranslation.getTranslation());
                            }
                        }
                    }
                } else {
                    return;
                }
            }
            if (!isTextChanged) {
                changeLabelLanguageToPrimary();
            }
        }
    }

    public void changeLabelLanguageToPrimary() {
        List<QuestionLabel> questionLabel = question.getLabels();
        for (QuestionLabel ql : questionLabel) {
            for (LabelTranslation labelTranslation : ql.getLabelTranslations()) {
                if (primaryLanguage != null && primaryLanguage.getCode().equals(labelTranslation.getCode())) {
                    String questionType = question.getType();
                    if (questionType.equals("SINGLE_SELECT")) {
                        RadioButton rdb = findViewWithTag(ql.getId());
                        if (rdb != null) {
                            rdb.setText(labelTranslation.getTranslation());
                        }
                    } else {
                        CheckBox chk = findViewWithTag(ql.getId());
                        if (chk != null) {
                            chk.setText(labelTranslation.getTranslation());
                        }
                    }
                }
            }
        }
    }


    /**
     * Returns type face
     *
     * @return typeface
     */
    public Typeface getTypeFace() {
        return typeface;
    }
}
