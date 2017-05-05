package com.dlgdev.goivcalc.models

class Pokemon(val id: Int, val attack: Int, val defense: Int, val stamina: Int) {
    lateinit var name: String

    override fun toString(): String {
        return name
    }
}