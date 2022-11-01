package it.phoenixspa.inbank.theming.auth.password2.net

import com.google.gson.annotations.SerializedName
import it.phoenixspa.inbank.catalog.messages.RuleInterface
import it.phoenixspa.inbank.catalog.messages.ScoreInterface
import java.util.regex.Pattern

interface PasswordScoreResponseInterface {
    val passwordScore: ScoreInterface?
    val rules: List<RuleInterface>?
    val isValid: Boolean

    fun getPercentScore(): Int? = passwordScore?.score
    fun getTextScore(): String? = passwordScore?.text
}

class DataForPasswordChange(
    val isValid: Boolean,
    val percentScore: Int,
    val textScore: String,
    val listRules: List<OtherRule>,
    val ruleNotPassed: String
    ) {

}

class OtherRule(val value: String, val passed: Boolean, val isVisible: Boolean)

class PasswordScoreResponse(
    /**
     * Lo score della password
     */
    @SerializedName("passwordScore")
    override val passwordScore: ScoreInterface? = null,

    /**
     * La lista di regole del cambio password
     */
    @SerializedName("rules")
    override val rules: List<RuleInterface>? = null,

    /**
     * Indica se la password è valida e ha superato tutte le regole
     */
    @SerializedName("isValid")
    override val isValid: Boolean = false
) : PasswordScoreResponseInterface {

}

