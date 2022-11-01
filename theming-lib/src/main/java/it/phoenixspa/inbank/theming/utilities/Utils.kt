package it.phoenixspa.inbank.theming.utilities

import java.security.SecureRandom
import java.util.*

/**
 * Classe copiata dal progetto originario solo per ottenere il medesimo effetto grafico con la scrambledKeyboard
 */
class Utils {
    companion object{
        /**
         * restituisce la descrizione della tipologia di conto
         */
        @JvmStatic
        fun generaNumeriCasualiDistinti(
            max: Int,
            increment1: Boolean
        ): ArrayList<Int> {
            return generaNumeriCasualiDistinti(max, max, increment1)
        }

        @JvmStatic
        private fun generaNumeriCasualiDistinti(
            dimArray: Int,
            max: Int, increment1: Boolean
        ): ArrayList<Int> {
            val random = SecureRandom()
            val arrayTmp = ArrayList<Int>()
            var i = 0
            while (i < dimArray && i < max) {
                while (true) {
                    var next = random.nextInt(max)
                    next = if (increment1) next + 1 else next
                    if (!arrayTmp.contains(next)) {
                        // Done for this iteration
                        arrayTmp.add(next)
                        break
                    }
                }
                i++
            }
            return arrayTmp
        }
    }
}
