package com.dlgdev.goivcalc.models

import org.junit.Test

class OtherRandomTests {
    val calc = PokemonIvCalculator()
    var provider = PokemonProvider()

    val machop = provider.get(66)
    val observedCps = arrayOf(16, 34, 51)

    @Test
    fun showMachopPossibilities() {
        val attack = 15
        for (level in 0..20) {
            calc.usedPowerUp = level % 2 != 0
            val lvl = (level / 2) + 1
            val pup = if (calc.usedPowerUp) "'5" else ""
            println("$lvl$pup")
            for (defense in 8..14) {
                for (hp in 11..14) {
                    if (attack + defense + hp >= 37) {
                        showCurrentCp(lvl, attack, defense, hp)
                    }
                }
            }
        }
    }

    private fun showCurrentCp(level: Int, attack: Int, defense: Int, hp: Int) {
        val cp = calc.calcCp(machop, level, hp, attack, defense)
        if(cp in observedCps) {
            val pup = if (calc.usedPowerUp) "'5" else ""
            println("Machop lvl $level$pup, $attack/$defense/$hp: CP = $cp")
        }
    }

    @Test fun machopThatRunAway() {
        for (level in 25..30) {
            println("Level: $level")
            calc.cp = 925
            calc.unknownHp = true

            val res = calc.calculate(machop, level)
            res.forEach {
                println("${it.attack}/${it.defense}/${it.stamina}")
            }
        }
    }

    var kabuto = provider.get(140)

    @Test fun kabutoThatRunAway() {
        for (level in 25..35) {
            println("Level: $level")
            calc.cp = 1005
            calc.unknownHp = true

            val res = calc.calculate(kabuto, level)
            res.forEach {
                println("${it.attack}/${it.defense}/${it.stamina}")
            }
        }
    }
}
