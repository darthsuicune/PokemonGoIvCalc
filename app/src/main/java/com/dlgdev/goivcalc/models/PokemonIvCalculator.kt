package com.dlgdev.goivcalc.models

import javax.inject.Inject

class PokemonIvCalculator @Inject constructor() {
    private val cp_multiplier = arrayOf(0.094, 0.16639787, 0.21573247, 0.25572005, 0.29024988,
            0.3210876, 0.34921268, 0.37523559, 0.39956728, 0.42250001,
            0.44310755, 0.46279839, 0.48168495, 0.49985844, 0.51739395,
            0.53435433, 0.55079269, 0.56675452, 0.58227891, 0.59740001,
            0.61215729, 0.62656713, 0.64065295, 0.65443563, 0.667934,
            0.68116492, 0.69414365, 0.70688421, 0.71939909, 0.7317,
            0.73776948, 0.74378943, 0.74976104, 0.75568551, 0.76156384,
            0.76739717, 0.7731865, 0.77893275, 0.78463697, 0.79030001)
    private val dustToLevel = mapOf(
            Pair(200, 1..2),
            Pair(400, 3..4),
            Pair(600, 5..6),
            Pair(800, 7..8),
            Pair(1000, 9..10),
            Pair(1300, 11..12),
            Pair(1600, 13..14),
            Pair(1900, 15..16),
            Pair(2200, 17..18),
            Pair(2500, 19..20),
            Pair(3000, 21..22),
            Pair(3500, 23..24),
            Pair(4000, 25..26),
            Pair(4500, 27..28),
            Pair(5000, 29..30),
            Pair(6000, 31..32),
            Pair(7000, 33..34),
            Pair(8000, 35..36),
            Pair(9000, 37..38),
            Pair(10000, 39..40)
    )
    var usedPowerUp = false
    var hpIsMax = false
    var atkIsMax = false
    var defIsMax = false
    var dust = 0
    var cp = 10
    var hp = 10
    var unknownHp = false
    var maxRange = LeaderSayings.UNKNOWN

    fun calculate(pokemon: Pokemon, level: Int): List<CalcResults> {
        val results = mutableListOf<CalcResults>()
        var minHp = 0
        var minAtk = 0
        var minDef = 0
        if (hpIsMax) {
            minHp = maxRange.min
        }
        if (atkIsMax) {
            minAtk = maxRange.min
        }
        if (defIsMax) {
            minDef = maxRange.min
        }

        for (hp in minHp..maxRange.max) {
            for (atk in minAtk..maxRange.max) {
                if (unknownHp) {
                    (minDef..maxRange.max)
                            .filter { calcCp(pokemon, level, hp, atk, it) == this.cp }
                            .filter { maxStatsFilter(hp, atk, it) }
                            .mapTo(results) { CalcResults(level, hp, atk, it, usedPowerUp) }
                } else {
                    (minDef..maxRange.max)
                            .filter { calcCp(pokemon, level, hp, atk, it) == this.cp }
                            .filter { calcHp(pokemon, level, hp) == this.hp }
                            .filter { maxStatsFilter(hp, atk, it) }
                            .mapTo(results) { CalcResults(level, hp, atk, it, usedPowerUp) }
                }

            }
        }
        return results
    }

    private fun maxStatsFilter(hp: Int, atk: Int, def: Int): Boolean {
        return when {
            atkIsMax && defIsMax && hpIsMax -> hp == atk && atk == def
            atkIsMax && defIsMax -> atk == def && atk > hp
            atkIsMax && hpIsMax -> atk == hp && atk > def
            defIsMax && hpIsMax -> def == hp && def > atk
            atkIsMax -> atk > def && atk > hp
            defIsMax -> def > atk && def > hp
            hpIsMax -> hp > atk && hp > def
            else -> true
        }
    }

    fun calculate(pokemon: Pokemon): List<CalcResults> {
        val results = mutableListOf<CalcResults>()
        for (level in dustToLevel[dust]!!) {
            results.addAll(calculate(pokemon, level))
        }
        return results
    }

    /**
     * https://www.reddit.com/r/TheSilphRoad/comments/4t7r4d/exact_pokemon_cp_formula/
     * Poke Assistant:
     * CP = (Base atk + Atk IV) * (Base def + def iv)^0.5 * (Base stam + StamIV)^0.5 * Lvl(CPMultiplier)^2 / 10
     */
    fun calcCp(pokemon: Pokemon, level: Int, hp: Int, atk: Int, def: Int): Int {
        val stamina = (pokemon.stamina + hp) * cpMultiplier(level)
        val attack = (pokemon.attack + atk) * cpMultiplier(level)
        val defense = (pokemon.defense + def) * cpMultiplier(level)
        val result = attack * Math.pow(defense, 0.5) * Math.pow(stamina, 0.5) / 10
        return Math.max(10, Math.floor(result).toInt())
    }

    /**
     * HP = (Base Stam + Stam IV) * Lvl(CPMultiplier)
     */
    fun calcHp(pokemon: Pokemon, level: Int, hp: Int): Int {
        val result = (pokemon.stamina + hp) * cpMultiplier(level)
        return Math.max(10, Math.floor(result).toInt())
    }


    /**
     * https://www.reddit.com/r/pokemongodev/comments/4t1zty/how_cpmultiplier_and_additionalcpmultiplier_work/
     * https://www.reddit.com/r/pokemongodev/comments/4t7xb4/exact_cp_formula_from_stats_and_cpm_and_an_update/
     */
    private fun cpMultiplier(level: Int): Double {
        if (usedPowerUp) {
            val scalar = cp_multiplier[level - 1]
            return Math.sqrt(Math.pow(scalar, 2.0) + acpm(level))
        }
        return cp_multiplier[level - 1]
    }

    private fun acpm(level: Int): Double {
        when (level) {
            in 1..9 -> return 0.009426125469
            in 10..19 -> return 0.008919025675
            in 20..29 -> return 0.008924905903
            in 30..40 -> return 0.00445946079
            else -> return 0.0
        }
    }

    enum class LeaderSayings(val min: Int, val max: Int) {
        AVERAGE(0, 7), GOOD(8, 12), VERY_GOOD(13, 14), PERFECT(15, 15), UNKNOWN(0, 15);

        companion object {
            fun fromSpinner(position: Int): LeaderSayings {
                return when (position) {
                    1 -> AVERAGE
                    2 -> GOOD
                    3 -> VERY_GOOD
                    4 -> PERFECT
                    else -> UNKNOWN
                }
            }
        }
    }
}