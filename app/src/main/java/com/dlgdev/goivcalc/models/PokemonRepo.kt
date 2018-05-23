package com.dlgdev.goivcalc.models

interface PokemonRepo {
    fun get(id: Int): Pokemon
    fun getAll(): Map<Int, Pokemon>

}