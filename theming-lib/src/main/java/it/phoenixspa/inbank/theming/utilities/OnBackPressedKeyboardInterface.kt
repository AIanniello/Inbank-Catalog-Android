package it.phoenixspa.inbank.theming.utilities

/**
 * Classe spostata dal progetto originario solo per ottenere il medesimo effetto grafico con la scrambledKeyboard
 */
interface OnBackPressedKeyboardInterface {
    /**
     * returns true if we want to intercept onBackPressed of [android.app.Activity] (when [it.phoenixspa.inbank.theming.ScrambledKeyboard] is open)
     */
    fun onBackPressedKeyboard(): Boolean
}