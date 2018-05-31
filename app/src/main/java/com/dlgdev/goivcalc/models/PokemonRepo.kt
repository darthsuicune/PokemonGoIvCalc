package com.dlgdev.goivcalc.models

interface PokemonRepo {
    fun get(id: Int, form: Int = 0): Pokemon
    fun getAll(): Map<Pair<Int, Int>, Pokemon>

}