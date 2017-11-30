package com.loyagram.android.campaignsdk.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
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
import java.util.HashMap;
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

        void onTextviewSubmit(String text);
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
    HashMap<String, String> staticTextes;
    AppCompatEditText txtAnswer = null;
    String fieldType = null;

    public LoyagramTextView(Context context, Question question, Response response, LoyagramCampaignView loyagramCampaignView, String colorPrimary, Language language, HashMap<String, String> staticTextes, Language primaryLanguage) {
        super(context);
        this.response = response;
        this.question = question;
        this.loyagramCampaignView = loyagramCampaignView;
        this.colorPrimary = colorPrimary;
        this.language = language;
        this.staticTextes = staticTextes;
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
        txtQuestion = findViewById(R.id.qstnTitle);
        llTextviewcontainer = findViewById(R.id.textViewContainer);
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
    @SuppressLint("RestrictedApi")
    public void showquestion() {
        txtQuestion.setVisibility(VISIBLE);
        setQuestionTitle();
        List<QuestionLabel> questionLabels = question.getLabels();
        final QuestionLabel ql = questionLabels.get(0);
        if (ql != null) {
            txtAnswer = new AppCompatEditText(currentContext);
            txtAnswer.setImeOptions(EditorInfo.IME_ACTION_DONE);
            LinearLayout.LayoutParams editTextParams;
            fieldType = ql.getFieldType();
            if (fieldType == null) {
                loyagramCampaignView.showSubView(true);
                loyagramCampaignView.hideProgress();
                return;
            }

            int txtHeight;
            if (fieldType.matches("SHORT_ANSWER|EMAIL|NUMBER")) {
                txtHeight = getResources().getDimensionPixelSize(R.dimen.edit_text_height);
                txtAnswer.setSupportBackgroundTintList(getColorStateList("#d9d9d9"));
            } else {
                txtAnswer.setBackgroundResource(R.drawable.lg_npssquare);
                ((GradientDrawable) txtAnswer.getBackground()).setStroke(getResources().getDimensionPixelSize(R.dimen.stroke_width), Color.parseColor("#d9d9d9"));
                txtHeight = getResources().getDimensionPixelSize(R.dimen.edit_text__paragraph_height);
            }
            float scale = getContext().getResources().getDisplayMetrics().density;
            int editTextMargin = (int) (20 * scale + 0.5f);
            editTextParams = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, txtHeight);
            editTextParams.gravity = Gravity.CENTER;
            editTextParams.setMargins(editTextMargin, 0, editTextMargin, editTextMargin);
            txtAnswer.setLayoutParams(editTextParams);
            if (getTypeFace() != null) {
                txtQuestion.setTypeface(typeface);
            }
            txtAnswer.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
            txtAnswer.setPadding(30, 10, 30, 10);
            txtAnswer.setFilters(new InputFilter[]{new InputFilter.LengthFilter(150)});
            switch (fieldType) {
                case "SHORT_ANSWER":
                    txtAnswer.setSingleLine(true);
                    txtAnswer.setHint(staticTextes.get("INPUT_PLACEHOLDER_TEXT"));
                    break;
                case "PARAGRAPH":
                    txtAnswer.setMaxLines(3);
                    txtAnswer.setMinLines(3);
                    txtAnswer.setHint(staticTextes.get("INPUT_PLACEHOLDER_TEXT"));
                    txtAnswer.setGravity(Gravity.START);
                    break;
                case "EMAIL":
                    txtAnswer.setSingleLine(true);
                    txtAnswer.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
                    txtAnswer.setHint(staticTextes.get("EMAIL_ADDRESS_PLACEHOLDER_TEXT"));
                    break;
                case "NUMBER":
                    txtAnswer.setSingleLine(true);
                    txtAnswer.setInputType(InputType.TYPE_CLASS_PHONE);
                    txtAnswer.setHint(staticTextes.get("INPUT_PLACEHOLDER_TEXT"));
                    break;
                default:
                    txtAnswer.setMaxLines(3);
                    txtAnswer.setMinLines(3);
                    txtAnswer.setHint(staticTextes.get("INPUT_PLACEHOLDER_TEXT"));
                    break;
            }

            ResponseAnswer ra = getResponseAnswer(ql.getId());
            if (ra != null && ra.getQuestionLabelId().equals(ql.getId())) {
                if (ra.getResponseAnswerText() != null && ra.getResponseAnswerText().getText() != null)
                    txtAnswer.setText(ra.getResponseAnswerText().getText());
            }

            txtAnswer.setOnTouchListener(new OnTouchListener() {
                @SuppressLint("ClickableViewAccessibility")
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    isKeyboardShown = true;
                    return false;
                }
            });

            txtAnswer.setOnFocusChangeListener(new OnFocusChangeListener() {
                @Override
                public void onFocusChange(View view, boolean hasFocus) {

                    if (hasFocus) {
                        if (!fieldType.equals("PARAGRAPH")) {
                            txtAnswer.setSupportBackgroundTintList(getColorStateList(colorPrimary));
                        } else {
                            ((GradientDrawable) txtAnswer.getBackground()).setStroke(getResources().getDimensionPixelSize(R.dimen.stroke_width), Color.parseColor(colorPrimary));
                        }
                    } else {
                        if (!fieldType.equals("PARAGRAPH")) {
                            txtAnswer.setSupportBackgroundTintList(getColorStateList("#d9d9d9"));
                        } else {
                            ((GradientDrawable) txtAnswer.getBackground()).setStroke(getResources().getDimensionPixelSize(R.dimen.stroke_width), Color.parseColor("#d9d9d9"));
                        }
                    }
                }
            });

            txtAnswer.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                /**
                 * Response asnwer text will be saved in preference
                 *
                 * @param s      REsponse text
                 * @param start  start index
                 * @param before before index
                 * @param count  count
                 */
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if (s != null) {
                        ResponseAnswer ra = setTextResponse(ql.getId(), new BigDecimal(1));
                        if (ra.getResponseAnswerText() != null) {
                            ra.getResponseAnswerText().setText(s.toString());
                        }
                        if (listener != null)
                            listener.onTextviewSubmit(s.toString());
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
            llTextviewcontainer.addView(txtAnswer);
        }
        if (loyagramCampaignView != null) {
            loyagramCampaignView.showSubView(true);
            loyagramCampaignView.hideProgress();
        }
    }

    /**
     * create color state list from theme color and return.
     *
     * @return color state list
     */
    public ColorStateList getColorStateList(String color) {
        int[][] states = new int[][]{
                new int[]{android.R.attr.state_enabled},
                new int[]{android.R.attr.state_focused}
        };
        int[] colors = new int[]{
                Color.parseColor(color),
                Color.parseColor(color)
        };
        return new ColorStateList(states, colors);
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
        setPlaceHolderText();
    }

    public void setPlaceHolderText() {
        List<QuestionLabel> questionLabels = question.getLabels();
        final QuestionLabel ql = questionLabels.get(0);
        String fieldType = ql.getFieldType();
        if (fieldType == null || txtAnswer == null) {
            return;
        }
        switch (fieldType) {
            case "SHORT_ANSWER":
            case "PARAGRAPH":
            case "NUMBER":
                txtAnswer.setHint(staticTextes.get("INPUT_PLACEHOLDER_TEXT"));
                break;
            case "EMAIL":
                txtAnswer.setHint(staticTextes.get("EMAIL_ADDRESS_PLACEHOLDER_TEXT"));
                break;
            default:
                txtAnswer.setHint(staticTextes.get("INPUT_PLACEHOLDER_TEXT"));
                break;
        }

    }

    /**
     * Sets Question titile with selected language
     */
    public void setQuestionTitle() {
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
                    txtQuestion.setText(questionTranslation.getTranslation());
                    break;
                }
            }
            if (!isTextChanged) {
                setQuestionTitleToPrimary();
            }
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
                            TextView txtratignTitle = findViewWithTag(ql.getId());
                            txtratignTitle.setText(labelTranslation.getTranslation());
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
                    TextView txtLabelTitle = findViewWithTag(ql.getId());
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
