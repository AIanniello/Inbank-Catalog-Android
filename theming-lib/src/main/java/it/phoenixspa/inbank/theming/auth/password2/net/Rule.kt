package it.phoenixspa.inbank.catalog.messages

import com.google.gson.annotations.SerializedName

interface RuleInterface {
    /**
     * Il testo della regola
     */
    val text: String?

    /**
     * Indica se la password ha passato positivamente la regola (true) o meno (false)
     */
    val isPassed: Boolean

    /**
     * Indica se il testo della regola va mostrato all'utente (true) o meno (false)
     */
    val isVisible: Boolean

    /**
     * La regular expression per verificare lato client il campo
     */
    val regexp: String?

    /**
     * Indica se la regola deve essere trattata come errore (true) o meno (false)
     */
    val isError: Boolean
}

class Rule(
    /**
     * Il testo della regola
     */
    @SerializedName("text")
    override val text: String? = "",

    /**
     * Indica se la password ha passato positivamente la regola (true) o meno (false)
     */
    @SerializedName("passed")
    override val isPassed: Boolean = false,

    /**
     * Indica se il testo della regola va mostrato all'utente (true) o meno (false)
     */
    @SerializedName("visible")
    override val isVisible: Boolean = false,

    /**
     * La regular expression per verificare lato client il campo
     */
    @SerializedName("regexp")
    override val regexp: String? = "",

    /**
     * Indica se la regola deve essere trattata come errore (true) o meno (false)
     */
    @SerializedName("isError")
    override val isError: Boolean = false

) : RuleInterface {


    override fun hashCode(): Int {
        var result = if (text != null) text.hashCode() else 0
        result = 31 * result + if (isPassed) 1 else 0
        result = 31 * result + if (isVisible) 1 else 0
        result = 31 * result + if (regexp != null) regexp.hashCode() else 0
        result = 31 * result + if (isError) 1 else 0
        return result
    }

    override fun equals(other: Any?): Boolean {
        if (text == null && (other as Rule?)!!.text != null) {
            return false
        }
        return if (regexp == null && (other as Rule?)!!.regexp != null) {
            false
        } else text != null && text == (other as Rule?)!!.text && regexp != null && regexp == other!!.regexp && isError == other.isError
    }
}