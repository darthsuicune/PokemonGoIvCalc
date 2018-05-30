package com.dlgdev.goivcalc.models

import org.junit.Test

class AlolanTests {
    val prov = PokemonProvider()
    val calc = PokemonIvCalculator()
    val eggy = prov.get(103, 1)

    @Test fun checkAlolanExeggutor() {
        println("Eggy: 525")
        testEggy(true, false, false, 800, 525, 67,
                PokemonIvCalculator.LeaderSayings.GOOD)
        println("Eggy: 2225")
        testEggy(false, true, false, 5000, 2225, 142,
                PokemonIvCalculator.LeaderSayings.GOOD)
        println("Eggy: 818")
        testEggy(false, false, true, 1300, 818, 89,
                PokemonIvCalculator.LeaderSayings.GOOD)
        println("Eggy: 771")
        testEggy(true, false, false, 1000, 771, 84,
                PokemonIvCalculator.LeaderSayings.VERY_GOOD)
        println("Eggy: 790")
        testEggy(false, true, false, 1000, 790, 83,
                PokemonIvCalculator.LeaderSayings.VERY_GOOD)
        println("Eggy: 1292")
        testEggy(false, false, true, 2200, 1292, 108,
                PokemonIvCalculator.LeaderSayings.AVERAGE)
        println("Eggy: 1479")
        testEggy(true, false, false, 2500, 1479, 115,
                PokemonIvCalculator.LeaderSayings.AVERAGE)
        println("Eggy: 2301")
        testEggy(false, false, true, 5000, 2301, 148,
                PokemonIvCalculator.LeaderSayings.VERY_GOOD)
        println("Eggy: 1239")
        testEggy(false, true, false, 1900, 1239, 103,
                PokemonIvCalculator.LeaderSayings.GOOD)
        println("Eggy: 1816")
        testEggy(true, false, false, 3500, 1816, 129,
                PokemonIvCalculator.LeaderSayings.GOOD)

    }

    private fun testEggy(atk: Boolean, def: Boolean, hpM: Boolean, dust: Int, cp: Int, hp: Int,
                         maxRange: PokemonIvCalculator.LeaderSayings) {
        calc.atkIsMax = atk
        calc.defIsMax = def
        calc.hpIsMax = hpM
        calc.dust = dust
        calc.cp = cp
        calc.hp = hp
        calc.maxRange = maxRange
        val result = calc.calculate(eggy)
        result.forEach {
            println("Atk: ${it.attack}, Def: ${it.defense}, HP: ${it.stamina}, Level: ${it.level}")
        }
    }
}