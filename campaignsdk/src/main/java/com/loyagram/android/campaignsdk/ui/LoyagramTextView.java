package com.loyagram.android.campaignsdk.ui;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
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

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Layout class for question type text. corresponding text input will be created for each question labels.
 * Responses will be saved whenever the rating changes.
 */

@SuppressWarnings("SpellCheckingInspection")
public class LoyagramTextView extends LinearLayout {
    interface LoyagramTextviewListener {
        void onTextviewBackPress();

        void onTextviewSubmit();
    }

    Language language = null;
    Response response;
    Question question;
    Context currentContext;
    LoyagramTextviewListener listener;
    TextView txtQuestion;
    LinearLayout llTextviewcontainer;
    Boolean isKeyboardShown = false;
    LoyagramCampaignView loyagramCampaignView = null;
    String colorPrimary = null;
    Typeface typeface;
    Language primaryLanguage;

    public LoyagramTextView(Context context, Question question, Response response, LoyagramCampaignView loyagramCampaignView, String colorPrimary, Language language, Language primaryLanguage) {
        super(context);
        this.response = response;
        this.question = question;
        this.loyagramCampaignView = loyagramCampaignView;
        this.colorPrimary = colorPrimary;
        this.language = language;
        this.primaryLanguage = primaryLanguage;
        init(context);
    }

    public LoyagramTextView(Context context, AttributeSet attr) {
        super(context, attr);
        init(context);
    }

    public LoyagramTextView(Context context, AttributeSet attr, int defStyle) {
        super(context, attr, defStyle);
        init(context);
    }

    public void setTextViewListener(LoyagramTextviewListener listener) {
        this.listener = listener;
    }

    public void init(Context context) {
        View.inflate(context, R.layout.loyagram_textview, this);
        currentContext = context;
        typeface = Typeface.createFromAsset(currentContext.getAssets(), "fonts/ProximaNova-Regular.otf");
        initLayouts();
        setLanguageListener();
    }

    public void initLayouts() {
        txtQuestion = (TextView) findViewById(R.id.qstnTitle);
        llTextviewcontainer = (LinearLayout) findViewById(R.id.textViewContainer);
        if (getTypeFace() != null) {
            txtQuestion.setTypeface(typeface);
        }
        this.setFocusableInTouchMode(true);
        this.requestFocus();
        showquestion();
    }

    /**
     * show text type question and dynamicaly creates text input types corresponding to label types.
     */
    public void showquestion() {
        txtQuestion.setVisibility(VISIBLE);
        setQuestionTitle();
        List<QuestionLabel> questionLabels = question.getLabels();
        int i = 0;
        for (final QuestionLabel ql : questionLabels) {
            LinearLayout linearLayout = new LinearLayout(currentContext);
            linearLayout.setOrientation(LinearLayout.HORIZONTAL);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(0, 10, 0, 0);
            linearLayout.setLayoutParams(params);

            EditText editText = new EditText(currentContext);
            editText.setBackgroundResource(R.drawable.lg_npssquare);
            ((GradientDrawable) editText.getBackground()).setStroke(getResources().getDimensionPixelSize(R.dimen.stroke_width), Color.parseColor(colorPrimary));
            LinearLayout.LayoutParams editTextParams;
            Boolean isLabelSet = false;
            String labelText = null;
            for (LabelTranslation labelTranslation : ql.getLabelTranslations()) {
                if (language != null && language.getCode().equals(labelTranslation.getCode())) {
                    labelText = labelTranslation.getTranslation();
                    isLabelSet = true;
                    break;
                }
            }
            if (!isLabelSet) {
                for (LabelTranslation labelTranslation : ql.getLabelTranslations()) {
                    if (primaryLanguage != null && primaryLanguage.getCode().equals(labelTranslation.getCode())) {
                        labelText = labelTranslation.getTranslation();
                        break;
                    }
                }
            }

            float scale = getContext().getResources().getDisplayMetrics().density;
            if (labelText != null && !labelText.equals("")) {
                linearLayout.setWeightSum(100);
                int txtLeftMargin = (int) (15 * scale + 0.5f);
                TextView txtLabel = new TextView(currentContext);
                txtLabel.setTag(ql.getId());
                txtLabel.setTextColor(Color.parseColor("#000000"));
                txtLabel.setGravity(Gravity.CENTER_VERTICAL);
                txtLabel.setText(labelText);
                LinearLayout.LayoutParams txtparams = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 30);
                txtparams.setMargins(txtLeftMargin, 0, 10, 0);
                txtparams.gravity = Gravity.CENTER_VERTICAL;
                String fieldType = ql.getFieldType();
                editText.setId(R.id.txtWidgetText + i);
                editTextParams = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 65);
                editText.setLayoutParams(editTextParams);
                txtLabel.setLayoutParams(txtparams);
                linearLayout.addView(txtLabel);
                if (getTypeFace() != null) {
                    editText.setTypeface(typeface);
                    txtLabel.setTypeface(typeface);
                }

                switch (fieldType) {
                    case "number":
                        editText.setMaxLines(1);
                        editText.setInputType(InputType.TYPE_CLASS_PHONE);
                        break;
                    case "email":
                        editText.setMaxLines(1);
                        editText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
                        break;
                    default:
                        editText.setMaxLines(3);
                        editText.setMinLines(3);
                        break;
                }
            } else {
                params.gravity = Gravity.CENTER;
                int editTextMargin = (int) (10 * scale + 0.5f);
                editTextParams = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
                editTextParams.gravity = Gravity.CENTER;
                editTextParams.setMargins(editTextMargin, editTextMargin, editTextMargin, editTextMargin);
                editText.setLayoutParams(editTextParams);
                editText.setLines(3);
                editText.setMaxLines(3);
                editText.setMinLines(3);
            }
            i++;
            ResponseAnswer ra = getResponseAnswer(ql.getId());
            if (ra != null && ra.getQuestionLabelId().equals(ql.getId())) {
                if (ra.getResponseAnswerText() != null && ra.getResponseAnswerText().getText() != null)
                    editText.setText(ra.getResponseAnswerText().getText());
            }

            editText.setOnTouchListener(new OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    isKeyboardShown = true;
                    return false;
                }
            });

            editText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                /**
                 * Response asnwer text will be saved in preference
                 * @param s REsponse text
                 * @param start start index
                 * @param before before index
                 * @param count count
                 */
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if (s != null) {
                        ResponseAnswer ra = setTextResponse(ql.getId(), new BigDecimal(1));
                        if (ra.getResponseAnswerText() != null) {
                            ra.getResponseAnswerText().setText(s.toString());
                        }
                        if (listener != null)
                            listener.onTextviewSubmit();
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });

            linearLayout.addView(editText);
            llTextviewcontainer.addView(linearLayout);
        }
        if (loyagramCampaignView != null) {
            loyagramCampaignView.showSubView(true);
            loyagramCampaignView.hideProgress();
        }
    }

    /**
     * Sets response answer
     *
     * @param qsid question Id
     * @param val  value
     * @return response answer
     */
    private ResponseAnswer setTextResponse(BigDecimal qsid, BigDecimal val) {
        ResponseAnswer ra = getResponseAnswer(qsid);
        if (ra != null) {
            ra.setValue(val);
            return ra;
        } else {
            ResponseAnswer campaignedAnswer = getNewResponseAnswer();
            campaignedAnswer.setQuestionLabelId(qsid);
            campaignedAnswer.setValue(val);
            campaignedAnswer.setAt(System.currentTimeMillis());
            ResponseAnswerText txt = new ResponseAnswerText();
            txt.setCampaignedAnswerId(campaignedAnswer.getId());
            campaignedAnswer.setResponseAnswerText(txt);
            response.getResponseAnswers().add(campaignedAnswer);
            return campaignedAnswer;
        }
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
     * Handles Android back press.
     * If keybaord is shown hides the keybaord else backpress call back will be invoked.
     *
     * @param event Key Event
     * @return Boolean
     */
    @Override
    public boolean dispatchKeyEventPreIme(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
            if (!isKeyboardShown) {
                if (listener != null) {
                    listener.onTextviewBackPress();
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
        setQuestionTitle();
        changeLabelLanguage();
    }

    /**
     * Sets Question titile with selected language
     */
    public void setQuestionTitle() {
        String langcode = language.getCode();
        Boolean isTextChanged = false;
        List<QuestionTranslations> questionTranslations = question.getTranslations();
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
            setQuestionTitleToPrimary();
        }
    }

    public void setQuestionTitleToPrimary() {
        String langcode = primaryLanguage.getCode();
        List<QuestionTranslations> questionTranslations = question.getTranslations();
        for (QuestionTranslations questionTranslation : questionTranslations) {
            if (questionTranslation.getCode() != null && questionTranslation.getCode().equals(langcode)) {
                txtQuestion.setText(questionTranslation.getTranslation());
            }
        }
    }

    /**
     * Changes Languge of the text label fields.
     */
    public void changeLabelLanguage() {
        Boolean isTextChanged = false;
        List<QuestionLabel> questionLabel = question.getLabels();
        for (QuestionLabel ql : questionLabel) {
            for (LabelTranslation labelTranslation : ql.getLabelTranslations()) {
                if (language != null && language.getCode().equals(labelTranslation.getCode())) {
                    if (labelTranslation.getTranslation() == null || labelTranslation.getTranslation().isEmpty()) {
                        break;
                    }
                    isTextChanged = true;
                    TextView txtratignTitle = (TextView) findViewWithTag(ql.getId());
                    txtratignTitle.setText(labelTranslation.getTranslation());
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
                    TextView txtLabelTitle = (TextView) findViewWithTag(ql.getId());
                    if (txtLabelTitle != null) {
                        txtLabelTitle.setText(labelTranslation.getTranslation());
                    }
                }
            }
        }
    }

    /**
     * Method returns type face
     *
     * @return typeface
     */
    public Typeface getTypeFace() {
        return typeface;
    }
}
