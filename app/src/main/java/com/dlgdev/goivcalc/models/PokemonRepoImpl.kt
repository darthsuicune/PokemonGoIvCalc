package com.dlgdev.goivcalc.models

import javax.inject.Inject

class PokemonRepoImpl @Inject constructor(val mons: Map<Int, Pokemon>) : PokemonRepo {
    override fun getAll(): Map<Int, Pokemon> {
        return mons
    }

    override fun get(id: Int): Pokemon {
        return mons[id]!!
    }
}