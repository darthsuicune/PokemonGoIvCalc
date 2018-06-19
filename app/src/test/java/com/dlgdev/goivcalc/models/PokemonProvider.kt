package com.dlgdev.goivcalc.models

import com.google.gson.Gson
import java.nio.file.Files
import java.nio.file.Paths

class PokemonProvider {
    val file = "src/main/assets/mon_stats.json"
    val stats by lazy { Gson().fromJson<PokemonStats>(Files.newBufferedReader(Paths.get(file)),
            PokemonStats::class.java) }

    fun get(id: Int, form: Int = 0): Pokemon {
        val mon = stats.mons.filter { it.id == id && it.form == form}[0]
        return Pokemon(mon.id, mon.stamina, mon.attack, mon.defense)
    }

}