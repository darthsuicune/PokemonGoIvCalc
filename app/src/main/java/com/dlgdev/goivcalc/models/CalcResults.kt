package com.dlgdev.goivcalc.models

class CalcResults(val level:Int, val stamina: Int, val attack: Int, val defense: Int) {
    override fun toString(): String {
        return "level: $level, stamina: $stamina, attack: $attack, defense: $defense"
    }
}