package com.dlgdev.goivcalc.models

import javax.inject.Inject

class Calculator @Inject constructor() {
    var pokemon = Pokemon(0, 1, 1, 1)
    var usedPowerUp = false
    var hpIsMax = false
    var atkIsMax = false
    var defIsMax = false
    var dust = 200
    var cp = 10
    var hp = 10
    var maxValue = LeaderSayings.AVERAGE

    fun calculate(): List<CalcResults> {
        val results = mutableListOf<CalcResults>()
        var minHp = 0
        var minAtk = 0
        var minDef = 0
        if (hpIsMax) {
            minHp = maxValue.min
        }
        if (atkIsMax) {
            minAtk = maxValue.min
        }
        if (defIsMax) {
            minDef = maxValue.min
        }

        for (level in 1..30) {
            for (hp in minHp..maxValue.max) {
                for (atk in minAtk..maxValue.max) {
                    (minDef..maxValue.max)
                            .filter { calcCp(level, hp, atk, it) == cp }
                            .mapTo(results) { CalcResults(level, hp, atk, it) }
                }
            }
        }
        return results
    }

    /**
     * Poke Assistant:
     * HP = (Base Stam + Stam IV) * Lvl(CPScalar)
     * CP = (Base atk + Atk IV) * (Base def + def iv)^0.5 * (Base stam + StamIV)^0.5 * Lvl(CPScalar)^2 / 10
     */
    private fun calcCp(level: Int, hp: Int, atk: Int, def: Int): Int {
        return hp * atk * def
    }

    enum class LeaderSayings(val min: Int, val max: Int) {
        AVERAGE(0, 7), GOOD(8, 12), VERY_GOOD(13, 14), PERFECT(15, 15);

        companion object {
            fun fromSpinner(position: Int): LeaderSayings {
                when (position) {
                    0 -> return AVERAGE
                    1 -> return GOOD
                    2 -> return VERY_GOOD
                    else -> return PERFECT
                }
            }
        }
    }
}