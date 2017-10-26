package com.loyagram.android.campaignsdk.ui;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.LayerDrawable;
import android.support.v7.widget.AppCompatRatingBar;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RatingBar;
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
import java.util.List;
import java.util.UUID;

/**
 * Layout class for question type rating. Includes methods for initialize layouts and show questions.
 * Responses will be saved whenever the rating changes
 */

@SuppressWarnings("SpellCheckingInspection")
public class LoyagramRatingView extends LinearLayout {

    interface LoyagramRatingListener {
        void onRatingBackPress();

        void onRatingSubmit(Response response);
    }

    Language language = null;
    Question question;
    Response response;
    ResponseAnswer responseAnswer;
    LoyagramRatingListener listener;
    LinearLayout llratingContainer;
    TextView txtQuestion;
    Context currentContext;
    LoyagramCampaignView loyagramCampaignView = null;
    String colorprimary = null;
    Typeface typeface;
    Language primaryLanguage;

    public LoyagramRatingView(Context context, Question question, Response response, LoyagramCampaignView loyagramCampaignView, String colorPrimary, Language language, Language primaryLanguage) {
        super(context);
        this.question = question;
        this.response = response;
        this.loyagramCampaignView = loyagramCampaignView;
        this.colorprimary = colorPrimary;
        this.language = language;
        this.primaryLanguage = primaryLanguage;
        init(context);
    }

    public LoyagramRatingView(Context context, AttributeSet attr) {
        super(context, attr);
        init(context);
    }

    public LoyagramRatingView(Context context, AttributeSet attr, int defStyle) {
        super(context, attr, defStyle);
        init(context);
    }

    public void setRatingListener(LoyagramRatingListener listener) {
        this.listener = listener;
    }

    public void init(Context context) {
        View.inflate(context, R.layout.loyagram_ratingview, this);
        currentContext = context;
        typeface = Typeface.createFromAsset(currentContext.getAssets(), "fonts/ProximaNova-Regular.otf");
        initLayouts();
        setLanguageListener();
        this.setFocusableInTouchMode(true);
        this.requestFocus();
        overrideBackPress();
    }

    public void initLayouts() {

        llratingContainer = (LinearLayout) findViewById(R.id.ratingBarContainer);
        txtQuestion = (TextView) findViewById(R.id.qstnTitle);
        if (getTypeFace() != null) {
            txtQuestion.setTypeface(typeface);
        }
        showQuestion();
    }

    /**
     * shows question as well as rating bar with corresponding labels.
     * dynamicaly create rating bars and their labels based on questions labels.
     */
    public void showQuestion() {
        llratingContainer.removeAllViews();
        List<QuestionLabel> questionLabels = question.getLabels();
        setQuestion();
        //Converting Dp to pixels
        float scale = getContext().getResources().getDisplayMetrics().density;
        int pixelsTxtWidth = (int) (65 * scale + 0.5f);
        int ratingbarHieght = (int) (45 * scale + 0.5f);
        int txtLeftMargin = (int) (15 * scale + 0.5f);
        int i = 0;
        if(questionLabels != null) {
            for (final QuestionLabel ql : questionLabels) {
                LinearLayout linearLayout = new LinearLayout(currentContext);
                linearLayout.setOrientation(LinearLayout.HORIZONTAL);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, ratingbarHieght);
                linearLayout.setLayoutParams(params);
                TextView txtratignTitle = new TextView(currentContext);
                LinearLayout.LayoutParams txtparams = new LinearLayout.LayoutParams(pixelsTxtWidth, LinearLayout.LayoutParams.WRAP_CONTENT);
                txtparams.setMargins(txtLeftMargin, 0, 0, 0);
                txtparams.gravity = Gravity.CENTER_VERTICAL;
                txtratignTitle.setLayoutParams(txtparams);
                txtratignTitle.setTag(ql.getId());
                txtratignTitle.setTextColor(Color.parseColor("#000000"));
                txtratignTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.text_medium));
                if (getTypeFace() != null) {
                    txtratignTitle.setTypeface(typeface);
                }

                Boolean isLabelSet = false;
                for (LabelTranslation labelTranslation : ql.getLabelTranslations()) {
                    if (language != null && language.getCode().equals(labelTranslation.getCode())) {
                        txtratignTitle.setText(labelTranslation.getTranslation());
                        isLabelSet = true;
                        break;
                    }
                }
                if (!isLabelSet) {
                    for (LabelTranslation labelTranslation : ql.getLabelTranslations()) {
                        if (primaryLanguage != null && primaryLanguage.getCode().equals(labelTranslation.getCode())) {
                            txtratignTitle.setText(labelTranslation.getTranslation());
                            break;
                        }
                    }
                }

                LinearLayout.LayoutParams ratingBarParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                ratingBarParams.gravity = Gravity.CENTER;
                ResponseAnswer responseAnswer = getResponseAnswer(ql.getId());
                //AppCompatRatingBar ratingBar = (AppCompatRatingBar) View.inflate(currentContext, R.layout.loyagram_customratingbar, null);
                AppCompatRatingBar ratingBar = new AppCompatRatingBar(currentContext);
                ratingBar.setId(R.id.ratingBarWidget + i);
                i++;
                params.gravity = Gravity.CENTER;
                if (responseAnswer != null && responseAnswer.getQuestionLabelId().equals(ql.getId()) && responseAnswer.getValue() != null) {
                    ratingBar.setRating(Float.parseFloat(responseAnswer.getValue().toString()));
                } else {
                    ratingBar.setRating(0);
                }
            /* Restricted number of stars to 5 curently need to change this max supported stars should be 10 */
                //ratingBar.setNumStars(ql.getMaxValue().intValue());
                ratingBar.setNumStars(5);
                // ratingBar.setMax(5);
                ratingBar.setPadding(0, 0, 0, 0);
                ratingBar.setScaleX(.6f);
                ratingBar.setScaleY(.6f);
                ratingBar.setStepSize(1);
                ratingBar.setMax(0);
                ratingBar.setLayoutParams(ratingBarParams);

                LayerDrawable stars = (LayerDrawable) ratingBar.getProgressDrawable();
                stars.getDrawable(2).setColorFilter(Color.parseColor(colorprimary), PorterDuff.Mode.SRC_ATOP);
                stars.getDrawable(1).setColorFilter(Color.parseColor(colorprimary), PorterDuff.Mode.SRC_ATOP);
                stars.getDrawable(0).setColorFilter(Color.parseColor("#c4c2c2"), PorterDuff.Mode.SRC_ATOP);
                ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                    @Override
                    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                        ResponseAnswer responseAnswer = setResponseAnswer(ql.getId(), new BigDecimal(rating));
                        if (listener != null) {
                            listener.onRatingSubmit(response);
                        }
                    }
                });
                if (txtratignTitle.getText() != null && !txtratignTitle.getText().equals("")) {
                    linearLayout.addView(txtratignTitle);
                }
                linearLayout.addView(ratingBar);
                llratingContainer.addView(linearLayout);
            }
        }
        if (loyagramCampaignView != null) {
            loyagramCampaignView.showSubView(true);
            loyagramCampaignView.hideProgress();
        }

    }


    /**
     * Sets response answer with rating
     *
     * @param questionId question ID
     * @param rating     rating
     * @return response answer
     */

    public ResponseAnswer setResponseAnswer(BigDecimal questionId, BigDecimal rating) {
        ResponseAnswer resAnswer = getResponseAnswer(questionId);
        if (resAnswer != null) {
            resAnswer.setValue(rating);
            return resAnswer;
        } else {
            ResponseAnswer responseAnswer = getNewResponseAnswer();
            responseAnswer.setQuestionLabelId(questionId);
            responseAnswer.setValue(rating);
            responseAnswer.setAt(System.currentTimeMillis());
            response.getResponseAnswers().add(responseAnswer);
            return responseAnswer;
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
     * Handles OS backpress. Onbackpress call back will be called if the listener is not null.
     */
    public void overrideBackPress() {
        this.setOnKeyListener(new OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
                    if (listener != null) {
                        listener.onRatingBackPress();
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
        setQuestion();
        setLabelLang();
    }

    /**
     * Sets Question titile with selected language
     */
    public void setQuestion() {
        String langcode = language.getCode();
        List<QuestionTranslations> questionTranslations = question.getTranslations();
        Boolean isTextChanged = false;
        if(questionTranslations != null) {
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
                setQuestionToPrimaryLang();
            }
        }
    }

    public void setQuestionToPrimaryLang() {
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
     * Changes Languge of the text label fields.
     */
    public void setLabelLang() {
        List<QuestionLabel> questionLabel = question.getLabels();
        Boolean isTextChanged = false;
        if(questionLabel != null) {
            for (QuestionLabel ql : questionLabel) {
                for (LabelTranslation labelTranslation : ql.getLabelTranslations()) {
                    if (language != null && language.getCode().equals(labelTranslation.getCode())) {
                        if (labelTranslation.getTranslation() == null || labelTranslation.getTranslation().isEmpty()) {
                            break;
                        }
                        isTextChanged = true;
                        TextView txtratignTitle = (TextView) findViewWithTag(ql.getId());
                        if (txtratignTitle != null) {
                            txtratignTitle.setText(labelTranslation.getTranslation());
                        }
                        break;
                    }
                }
            }
            if (!isTextChanged) {
                setLabelLangToPrimary();
            }
        }
    }

    public void setLabelLangToPrimary() {
        List<QuestionLabel> questionLabel = question.getLabels();
        for (QuestionLabel ql : questionLabel) {
            for (LabelTranslation labelTranslation : ql.getLabelTranslations()) {
                if (primaryLanguage != null && primaryLanguage.getCode().equals(labelTranslation.getCode())) {
                    TextView txtratignTitle = (TextView) findViewWithTag(ql.getId());
                    if (txtratignTitle != null) {
                        txtratignTitle.setText(labelTranslation.getTranslation());
                    }
                    break;
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
