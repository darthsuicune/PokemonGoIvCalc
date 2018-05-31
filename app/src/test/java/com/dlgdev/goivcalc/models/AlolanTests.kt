package com.dlgdev.goivcalc.models

import com.dlgdev.goivcalc.models.PokemonIvCalculator.LeaderStatSayings.*
import org.junit.Test

class AlolanTests {
    val calc = PokemonIvCalculator()
    val prov = PokemonProvider()

    val eggy = prov.get(103, 1)

    @Test fun findEggy() {
        testEggy(2033, 132, 4500, defMax = true, statRange = GOOD)
        testEggy(620, 73, 800, defMax = true, statRange = PERFECT)
        testEggy(38, 19, 200, defMax = true, hpMax = true, totalRange = PokemonIvCalculator.LeaderTotalSayings.GOOD,statRange = VERY_GOOD)
        testEggy(862, 89, 1300, atkMax = true, hpMax = true, statRange = GOOD)
        testEggy(298, 52, 400, defMax = true, statRange = PERFECT)
        testEggy(912, 90, 1300, defMax = true, statRange = GOOD)
        testEggy(1304, 110, 2200, hpMax = true, statRange = GOOD)
        testEggy(1298, 109, 1900, defMax = true, statRange = PERFECT)
        testEggy(1836, 126, 3500, defMax = true, statRange = PERFECT)
        testEggy(38, 18, 200, atkMax = true, statRange = VERY_GOOD, totalRange = PokemonIvCalculator.LeaderTotalSayings.GOOD)
        testEggy(1029, 98, 1600, hpMax = true, statRange = PERFECT)
    }

    private fun testEggy(cp: Int, hp: Int, dust: Int,
                         atkMax: Boolean = false, defMax: Boolean = false, hpMax: Boolean = false,
                         totalRange: PokemonIvCalculator.LeaderTotalSayings = PokemonIvCalculator.LeaderTotalSayings.UNKNOWN,
                         statRange: PokemonIvCalculator.LeaderStatSayings = UNKNOWN) {
        calc.cp = cp
        calc.hp = hp
        calc.dust = dust
        calc.atkIsMax = atkMax
        calc.defIsMax = defMax
        calc.hpIsMax = hpMax
        calc.statRange = statRange
        calc.totalRange = totalRange
        val res = calc.calculate(eggy)
        println("Eggy: $cp")
        res.forEach {println("Atk: ${it.attack}, def: ${it.defense}, hp: ${it.stamina}, level: ${it.level}")}
    }
}