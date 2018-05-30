package com.dlgdev.goivcalc.models

import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher
import org.junit.Assert.assertThat
import org.junit.Test

class CalculatorTest {
    val calc = PokemonIvCalculator()
    var provider = PokemonProvider()

    val gyarados = provider.get(130)
    val alakazam = provider.get(65)
    val exeggutor = provider.get(103)
    val vaporeon = provider.get(134)
    val exeggcute = provider.get(102)


    /**
     * Gyarados: ID: 130, CP: 2764, HP: 144, Dust 5000,
     *          Atk: 15, Def: 15, Stamina: 8
     */
    @Test fun GyaradosIsRight() {
        calc.atkIsMax = true
        calc.defIsMax = true
        calc.dust = 5000
        calc.cp = 2764
        calc.hp = 144
        calc.maxRange = PokemonIvCalculator.LeaderSayings.PERFECT
        val result = calc.calculate(gyarados)
        assertThat(result, hasExactStats(30, 8, 15, 15))
    }

    private fun hasExactStats(level: Int, hp: Int, atk: Int, def: Int, usedPowerUp: Boolean = false): Matcher<List<CalcResults>>? {
        return object : TypeSafeMatcher<List<CalcResults>>() {
            override fun matchesSafely(item: List<CalcResults>): Boolean {
                return item.size == 1 && item[0].level == level && item[0].stamina == hp
                        && item[0].attack == atk && item[0].defense == def
                        && item[0].usedPowerUp == usedPowerUp
            }

            override fun describeTo(description: Description) {
                description.appendText("Expected pokemon with level ").appendValue(level)
                        .appendText(", hp ").appendValue(hp)
                        .appendText(", atk ").appendValue(atk)
                        .appendText(" and def ").appendValue(def)
            }
        }
    }

    /**
     * Alakazam: ID: 65, CP: 2438, HP: 90, Dust 5000,
     *          Atk: 14, Def 12:, Stamina: 14
     */
    @Test fun AlakazamIsRight() {
        calc.atkIsMax = true
        calc.hpIsMax = true
        calc.dust = 5000
        calc.cp = 2438
        calc.hp = 90
        calc.maxRange = PokemonIvCalculator.LeaderSayings.VERY_GOOD
        val result = calc.calculate(alakazam)
        assertThat(result, hasExactStats(30, 14, 14, 12))
    }

    /**
     * Alakazam: ID: 65, CP: 2438, HP: 90, Dust 5000,
     *          Atk: 14, Def 12:, Stamina: 14
     */
    @Test fun boostedAlakazamIsRight() {
        calc.atkIsMax = true
        calc.hpIsMax = true
        calc.usedPowerUp = true
        calc.dust = 5000
        calc.cp = 2458
        calc.hp = 91
        calc.maxRange = PokemonIvCalculator.LeaderSayings.VERY_GOOD
        val result = calc.calculate(alakazam)
        assertThat(result, hasExactStats(30, 14, 14, 12, true))
    }

    /**
     * Exeggutor: ID: 103, CP: 2471, HP: 149, Dust 5000,
     *          Atk: 15, Def: 11, Stamina: 15
     */
    @Test fun ExeggutorIsRight() {
        calc.atkIsMax = true
        calc.hpIsMax = true
        calc.dust = 5000
        calc.cp = 2471
        calc.hp = 149
        calc.maxRange = PokemonIvCalculator.LeaderSayings.PERFECT
        val result = calc.calculate(exeggutor)
        assertThat(result, hasExactStats(30, 15, 15, 11))
    }

    /**
     * Vaporeon: ID: 134, CP: 2579, HP:197, Dust 5000,
     *          Atk: 14, Def: 12, Stamina: 14
     */
    @Test fun VaporeonIsRight() {
        calc.atkIsMax = true
        calc.hpIsMax = true
        calc.dust = 5000 // But lvl 29!!
        calc.cp = 2579
        calc.hp = 197
        calc.maxRange = PokemonIvCalculator.LeaderSayings.VERY_GOOD
        val result = calc.calculate(vaporeon)
        assertThat(result, hasExactStats(29, 14, 14, 12))
    }

    /**
     * Exeggcute: ID: 102, CP: 706, HP: 86, Dust 3500,
     *          Atk: 9, Def: 13, Stamina: 12
     */
    @Test fun ExeggcuteIsRight() {
        calc.defIsMax = true
        calc.dust = 3500
        calc.cp = 706
        calc.hp = 86
        calc.maxRange = PokemonIvCalculator.LeaderSayings.VERY_GOOD
        val result = calc.calculate(exeggcute)
        assertThat(result, hasStats(24, 12, 9, 13))
    }

    private fun hasStats(level: Int, hp: Int, atk: Int, def: Int): Matcher<in List<CalcResults>>? {
        return object : TypeSafeMatcher<List<CalcResults>>() {
            override fun matchesSafely(item: List<CalcResults>): Boolean {
                return item.filter {
                    it.level == level && it.stamina == hp && it.attack == atk && it.defense == def
                }.isNotEmpty()
            }

            override fun describeTo(description: Description) {
                description.appendText("Expected pokemon with level ").appendValue(level)
                        .appendText(", hp ").appendValue(hp)
                        .appendText(", atk ").appendValue(atk)
                        .appendText(" and def ").appendValue(def)
            }
        }
    }
}