package it.phoenixspa.inbank.catalog.messages

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

interface ScoreInterface{
    /**
     * Percent Score
     */
    val score: Int

    /**
     * Text Score
     */
    val text: String
}

class Score(
    /**
     * Lo score della password in percentuale (su 100)
     */
    @SerializedName("score")
    override val score: Int = 0,

    /**
     * Lo score della password in formato testuale
     */
    @SerializedName("text")
    override val text: String = ""
) : ScoreInterface