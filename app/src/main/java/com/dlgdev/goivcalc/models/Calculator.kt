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

        for (level in 1..40) {
            for (hp in minHp..maxValue.max) {
                for (atk in minAtk..maxValue.max) {
                    (minDef..maxValue.max)
                            .filter { calcCp(level, hp, atk, it) == this.cp }
                            .filter { calcHp(level, hp) == this.hp }
                            .mapTo(results) { CalcResults(level, hp, atk, it) }

                }
            }
        }
        return results
    }

    /**
     * https://www.reddit.com/r/TheSilphRoad/comments/4t7r4d/exact_pokemon_cp_formula/
     * Poke Assistant:
     * CP = (Base atk + Atk IV) * (Base def + def iv)^0.5 * (Base stam + StamIV)^0.5 * Lvl(CPScalar)^2 / 10
     */
    fun calcCp(level: Int, hp: Int, atk: Int, def: Int): Int {
        val stamina = (pokemon.stamina + hp) * cpScalar(level)
        val attack = (pokemon.attack + atk) * cpScalar(level)
        val defense = (pokemon.defense + def) * cpScalar(level)
        val result = attack * Math.pow(defense, 0.5) * Math.pow(stamina, 0.5) / 10
        return Math.max(10, Math.floor(result).toInt())
    }

    /**
     * HP = (Base Stam + Stam IV) * Lvl(CPScalar)
     */
    fun calcHp(level: Int, hp: Int): Int {
        val result = (pokemon.stamina + hp) * cpScalar(level)
        return Math.max(10, Math.floor(result).toInt())
    }


    /**
     * https://www.reddit.com/r/pokemongodev/comments/4t1zty/how_cpmultiplier_and_additionalcpmultiplier_work/
     * https://www.reddit.com/r/pokemongodev/comments/4t7xb4/exact_cp_formula_from_stats_and_cpm_and_an_update/
     */
    private fun cpScalar(level: Int): Double {
        val cp_multiplier = arrayOf(0.094, 0.16639787, 0.21573247, 0.25572005, 0.29024988,
        0.3210876 , 0.34921268, 0.37523559, 0.39956728, 0.42250001,
        0.44310755, 0.46279839, 0.48168495, 0.49985844, 0.51739395,
        0.53435433, 0.55079269, 0.56675452, 0.58227891, 0.59740001,
        0.61215729, 0.62656713, 0.64065295, 0.65443563, 0.667934,
        0.68116492, 0.69414365, 0.70688421, 0.71939909, 0.7317,
        0.73776948, 0.74378943, 0.74976104, 0.75568551, 0.76156384,
        0.76739717, 0.7731865, 0.77893275, 0.78463697, 0.79030001)
        if(usedPowerUp) {
            val scalar = cp_multiplier[level - 1]
            when (level) {
                in 1..10 -> return scalar + 0.009426125469
                in 11..20 -> return scalar + 0.008919025675
                in 21..30 -> return scalar + 0.008924905903
                in 31..40 -> return scalar + 0.00445946079
            }

        }
        return cp_multiplier[level - 1]
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