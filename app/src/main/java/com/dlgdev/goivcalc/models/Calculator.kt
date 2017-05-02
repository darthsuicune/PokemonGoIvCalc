package com.dlgdev.goivcalc.models

import javax.inject.Inject

class Calculator @Inject constructor() {
    val pokemon = Pokemon(1, 1, 1, 1)
    var usedPowerUp = false
    var hpIsMax = false
    var atkIsMax = false
    var defIsMax = false
}