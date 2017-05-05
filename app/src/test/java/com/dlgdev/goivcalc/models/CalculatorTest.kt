package com.dlgdev.goivcalc.models

import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher
import org.junit.Assert.assertThat
import org.junit.Test

class CalculatorTest {
    val calc = Calculator()

    val gyarados = Pokemon(0, 0, 0, 0)
    val alakazam = Pokemon(0, 0, 0, 0)
    val exeggutor = Pokemon(0, 0, 0, 0)
    val vaporeon = Pokemon(0, 0, 0, 0)

    /**
     * Examples:
     * Gyarados: CP: 2764, HP: 144, Dust 5000,
     *          Atk: 15, Def: 15, Stamina: 8
     * Alakazam: CP: 2438, HP: 90, Dust 5000,
     *          Atk: 14, Def 12:, Stamina: 14
     * Exeggutor: CP: 2471, HP: 149, Dust 5000,
     *          Atk:, Def:, Stamina:
     * Vaporeon: CP: 2579, HP:197, Dust 5000,
     *          Atk: 14, Def: 12, Stamina: 14
     */

    @Test fun GyaradosIsRight() {
        calc.pokemon = gyarados
        val result = calc.calculate()
        assertThat(result, hasStats(30, 8, 15, 15))
    }

    private fun hasStats(level: Int, hp: Int, atk: Int, def: Int): Matcher<List<CalcResults>>? {
        return object : TypeSafeMatcher<List<CalcResults>>() {
            override fun matchesSafely(item: List<CalcResults>?): Boolean {
                if (item == null) return false
                return item.size == 1 && item[0].level == level && item[0].stamina == hp
                        && item[0].attack == atk && item[0].defense == def
            }

            override fun describeTo(description: Description) {
                description.appendText("Expected pokemon with level ").appendValue(level)
                        .appendText(", hp ").appendValue(hp)
                        .appendText(", atk ").appendValue(atk)
                        .appendText(" and def ").appendValue(def)
            }
        }
    }

    @Test fun AlakazamIsRight() {
        calc.pokemon = alakazam
        val result = calc.calculate()
        assertThat(result, hasStats(30, 14, 14, 12))
    }

    @Test fun ExeggutorIsRight() {
        calc.pokemon = exeggutor
        val result = calc.calculate()
        assertThat(result, hasStats(30, 15, 15, 11))
    }

    @Test fun VaporeonIsRight() {
        calc.pokemon = vaporeon
        val result = calc.calculate()
        assertThat(result, hasStats(29, 14, 14, 12))
    }

}