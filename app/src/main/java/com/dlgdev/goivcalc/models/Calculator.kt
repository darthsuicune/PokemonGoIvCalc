package com.dlgdev.goivcalc.models

import javax.inject.Inject

class Calculator @Inject constructor() {
    val pokemon = Pokemon(1, 1, 1, 1, "Missingno")
    var usedPowerUp = false
    var hpIsMax = false
    var atkIsMax = false
    var defIsMax = false
    var dust = 200
    var cp = 10
    var hp = 10
    var maxValue = LeaderSayings.AVERAGE

    enum class LeaderSayings {
        AVERAGE, GOOD, VERY_GOOD, PERFECT
    }
}