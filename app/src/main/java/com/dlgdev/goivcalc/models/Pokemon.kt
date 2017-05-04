package com.dlgdev.goivcalc.models

class Pokemon(val id: Int, val baseAtk: Int, val baseDef: Int, val baseStamina: Int, val name: String = "a") {

    override fun toString(): String {
        return name
    }
}