package com.dlgdev.goivcalc.models

import javax.inject.Inject

class PokemonRepoImpl @Inject constructor(val mons: Map<Pair<Int, Int>, Pokemon>) : PokemonRepo {
    override fun getAll(): Map<Pair<Int, Int>, Pokemon> {
        return mons
    }

    override fun get(id: Int, form: Int): Pokemon {
        return mons[id to form]!!
    }
}