package it.phoenixspa.inbank.theming.auth.password2

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.widget.CheckBox
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import it.phoenixspa.inbank.catalog.messages.Rule
import it.phoenixspa.inbank.catalog.messages.RuleInterface
import it.phoenixspa.inbank.catalog.messages.Score
import it.phoenixspa.inbank.theming.R
import it.phoenixspa.inbank.theming.auth.password2.net.DataForPasswordChange
import it.phoenixspa.inbank.theming.auth.password2.net.PasswordScoreResponse
import it.phoenixspa.inbank.theming.auth.password2.net.PasswordScoreResponseInterface
import it.phoenixspa.inbank.theming.databinding.ViewChangePasswordRulesMaterialBinding
import java.util.*
import java.util.regex.Pattern
import java.util.regex.PatternSyntaxException

class ChangePasswordRulesLayout : ConstraintLayout {
    private lateinit var binding: ViewChangePasswordRulesMaterialBinding

    private var mCheckBoxMarginTop = 0
    private var mCheckBoxLeftPadding = 0
    private var mValidPassword = false
    private var mRules: List<RuleInterface>? = null
    private var mHasErrors = false

    constructor(context: Context) : super(context) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet) : super(
        context, attrs
    ) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context, attrs, defStyleAttr
    ) {
        init(context)
    }

    fun init(context: Context) {
        binding = ViewChangePasswordRulesMaterialBinding.inflate(LayoutInflater.from(context), this, true)

        val density = this.resources.displayMetrics.density
        mCheckBoxMarginTop = (density * MARGIN_TOP_FROM_PREV_CHECKBOX).toInt()
        mCheckBoxLeftPadding = (density * CHECKBOX_LEFT_PADDING).toInt()

        mValidPassword = false
        binding.scoreProgressBar.setBackgroundResource(R.drawable.change_password_progress_background)
        binding.securityScoreTextView.text = "--"
        binding.securityScoreTextView.setTextColor(
            ContextCompat.getColor(
                getContext(),
                R.color.tra_profits_losses_line_chart_gray
            )
        )
        binding.scoreProgressBar.progress = 0
        mHasErrors = false

//        val scoreResponse = PasswordScoreResponse(
//            Score(
//                60, "BUONA"
//            ),
//            listOf(
//                Rule("Password lunga almeno 8 caratteri", false, true, "^.{8,}$", false),
//
//                Rule("Almeno una lettera maiuscola1", false, true, "^.*[A-Z].*\$", false),
//                Rule("Almeno una lettera maiuscola2", false, true, "^.*[A-Z].*\$", false),
//                Rule("Almeno una lettera maiuscola3", false, true, "^.*[A-Z].*\$", false),
//
//                Rule("Almeno una lettera minuscola", true, true, "^.*[a-z].*\$", false),
//                Rule(
//                    "Almeno un numero o un carattere speciale",
//                    true,
//                    true,
//                    "^.*[\\\\dàèéìòùäöüßčšž\\\\[\\\\(\\\\)\\\\]_\\\\-\\\\+\\\\/\\\\*=|!?\$%&.,@#].*\$",
//                    false
//                ),
//                Rule(
//                    "Due volte z",
//                    true,
//                    false,
//                    "^(?!.*(.)\\\\1{4,}).*\$",
//                    true
//                )
//            ),
//            true
//        )
//        updateUIAfterResponse(scoreResponse, "")
    }

    /**
     * Chiamato subito dopo che l'utente ha inserito un nuovo carattere, senza attendere la risposta del server (tanto tutti i controlli delle regexp sono locali).
     * @param newPassword password inserita dall'utente
     */
    fun createAndCheckRules(newPassword: String) {
        if (mRules == null) {
            binding.rulesLayout.isVisible = false
            return
        }
        val errorRulesNotPassed = StringBuilder()
        var prevCheckBoxId = ConstraintSet.PARENT_ID
        binding.rulesLayout.removeAllViewsInLayout()
        // consideriamo solo le regole settate come visibili lato server
        for (rule in mRules?.filter { it.isVisible } ?: listOf()) {
            try {
                val regexp = rule.regexp ?: ""
                val pattern = Pattern.compile(regexp)
                val matcher = pattern.matcher(newPassword)
                val matchFound = matcher.find()
                if (!rule.isError) {
                    val checkBox =
                        createCheckBox(UUID.randomUUID().hashCode(), rule.text ?: "", matchFound)
                    binding.rulesLayout.addView(checkBox)
                    applyCheckBoxConstraints(prevCheckBoxId, checkBox.id)
                    prevCheckBoxId = checkBox.id
                } else if (!matchFound) {
                    var ruleText = rule.text
                    if (ruleText?.length ?: 0 > 1) {
                        ruleText = ruleText?.substring(0, 1)
                            ?.toUpperCase(Locale.getDefault()) + ruleText?.substring(
                            1,
                            ruleText.length
                        )
                    }
                    errorRulesNotPassed.appendLine(ruleText)
                }
            } catch (ex: PatternSyntaxException) {
                Log.d("INBANK_DEBUG", "error compiling " + rule.regexp + "; " + ex.localizedMessage)
            }
        }
        binding.rulesLayout.isVisible = binding.rulesLayout.childCount > 0

        if (errorRulesNotPassed.isNotEmpty() && newPassword.isNotEmpty()) {
            binding.errorsTextView.apply {
                visibility = View.VISIBLE
                if(errorRulesNotPassed.isNotEmpty()) {
                    text = errorRulesNotPassed.substring(0, errorRulesNotPassed.length - 1)
                }
            }
            binding.textViewScoreLabel.visibility = View.GONE
            binding.securityScoreTextView.visibility = View.GONE
            mHasErrors = true
        } else {
            binding.errorsTextView.apply {
                visibility = View.GONE
                text = ""
            }
            binding.textViewScoreLabel.visibility = View.VISIBLE
            binding.securityScoreTextView.visibility = View.VISIBLE
            mHasErrors = false
        }
    }

    private fun createCheckBox(id: Int, text: String, checked: Boolean): CheckBox {
        val checkBox = CheckBox(context)
        checkBox.id = id
        checkBox.text =
            text.substring(0, 1).toUpperCase(Locale.getDefault()) + text.substring(1, text.length)
        checkBox.isChecked = checked
        checkBox.setButtonDrawable(R.drawable.change_password_checkbox_selector)
        checkBox.setTextSize(
            TypedValue.COMPLEX_UNIT_PX,
            resources.getDimension(R.dimen.text_small)
        )
        checkBox.setTextColor(ContextCompat.getColor(context, R.color.grigio_1))
        checkBox.isEnabled = false
        checkBox.setPadding(
            checkBox.paddingLeft + mCheckBoxLeftPadding,
            checkBox.paddingTop,
            checkBox.paddingRight,
            checkBox.paddingBottom
        )
        return checkBox
    }

//    private fun createCheckBox2(id: Int, text: String, checked: Boolean): CheckBoxStyled1 {
//        val checkBox = CheckBoxStyled1(context)
//        checkBox.id = id
//        checkBox.text = text
//        checkBox.isChecked = checked
//        checkBox.isEnabled = false
//        return checkBox
//    }

    private fun applyCheckBoxConstraints(prevViewId: Int, currentViewId: Int) {
        val topMargin = if (prevViewId == ConstraintSet.PARENT_ID) 0 else mCheckBoxMarginTop
        ConstraintSet().apply {
            clone(binding.rulesLayout)
            connect(prevViewId, ConstraintSet.TOP, currentViewId, ConstraintSet.BOTTOM, topMargin)
            connect(currentViewId, ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START)
            connect(currentViewId, ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END)

            setHorizontalBias(currentViewId, 0f)
            applyTo(binding.rulesLayout)
        }
    }

    fun setValidPassword(validPassword: Boolean) {
        if (mValidPassword != validPassword) {
            mValidPassword = validPassword
            binding.scoreProgressBar.progressDrawable = ContextCompat.getDrawable(
                context,
                if (validPassword) R.drawable.change_password_valid_clip_drawable else R.drawable.change_password_invalid_clip_drawable
            )
        }
    }

    /**
     * Per aggiornare lo scoring dobbiamo aspettare la risposta del server.
     * In più controlliamo anche che ci mandi le stesse regole che abbiamo già in locale, altrimenti ricreiamo i checkBox e riverifichiamo le condizioni.
     * @param scoreResponse
     * @param newPassword
     */
    fun updateUIAfterResponse(scoreResponse: PasswordScoreResponseInterface, newPassword: String) {
        val rules = scoreResponse.rules ?: return
        // Se le regole del server sono uguali a quelle locali non serve ricrearle (il componente risulta già aggiornato)
        if (mRules == null || mRules != rules)
        {
            mRules = rules
            createAndCheckRules(newPassword)
        }
        mRules = rules
        createAndCheckRules(newPassword)
        updateScoring(scoreResponse, newPassword)
    }



    fun updateUIAfterResponse(data: DataForPasswordChange, newPassword: String) {

        //region updateScoring
        // progressBar: aggiorna il colore della parte evidenziata
        binding.scoreProgressBar.progressDrawable = ContextCompat.getDrawable(
            context,
            if (data.isValid) R.drawable.change_password_valid_clip_drawable else R.drawable.change_password_invalid_clip_drawable
        )
        binding.scoreProgressBar.progress =
            (if (mHasErrors) 100 else data.percentScore ?: 0)

        binding.securityScoreTextView.text = data.textScore
        binding.securityScoreTextView.setTextColor(
            ContextCompat.getColor(
                context,
                if (data.isValid) R.color.positive else R.color.negative
            )
        )
        //endregion

        var prevCheckBoxId = ConstraintSet.PARENT_ID
        binding.rulesLayout.removeAllViewsInLayout()
        for (rule in data.listRules.filter { it.isVisible }) {
            val checkBox =
                createCheckBox(UUID.randomUUID().hashCode(), rule.value ?: "", rule.passed)
            binding.rulesLayout.addView(checkBox)
            applyCheckBoxConstraints(prevCheckBoxId, checkBox.id)
            prevCheckBoxId = checkBox.id
        }

        binding.rulesLayout.isVisible = binding.rulesLayout.childCount > 0

        if (data.ruleNotPassed.isNotEmpty() && newPassword.isNotEmpty()) {
            binding.errorsTextView.apply {
                visibility = View.VISIBLE
                text = data.ruleNotPassed.capitalize()
            }
            binding.textViewScoreLabel.visibility = View.GONE
            binding.securityScoreTextView.visibility = View.GONE
            mHasErrors = true
        } else {
            binding.errorsTextView.apply {
                visibility = View.GONE
                text = ""
            }
            binding.textViewScoreLabel.visibility = View.VISIBLE
            binding.securityScoreTextView.visibility = View.VISIBLE
            mHasErrors = false
        }

    }

    fun updateScoring(scoreResponse: PasswordScoreResponseInterface, newPassword: String?) {
        setValidPassword(scoreResponse.isValid)
        binding.securityScoreTextView.text = scoreResponse.getTextScore()
        binding.securityScoreTextView.setTextColor(
            ContextCompat.getColor(
                context,
                if (scoreResponse.isValid) R.color.positive else R.color.negative
            )
        )
        binding.scoreProgressBar.progress =
            (if (mHasErrors) 100 else scoreResponse.getPercentScore() ?: 0)
    }

    companion object {
        private const val MARGIN_TOP_FROM_PREV_CHECKBOX = 6
        private const val CHECKBOX_LEFT_PADDING = 8
    }
}
