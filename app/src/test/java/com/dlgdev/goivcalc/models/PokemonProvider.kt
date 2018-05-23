package com.dlgdev.goivcalc.models

import com.google.gson.Gson
import java.nio.file.Files
import java.nio.file.Paths

class PokemonProvider {
    fun get(id: Int): Pokemon {
        val stats =
                Gson().fromJson<Helper>(Files.newBufferedReader(Paths.get("src/main/assets/mon_stats")),
                        Helper::class.java)
        val mon = stats.mons.filter { it.id == id }[0]
        return Pokemon(mon.id, mon.stamina, mon.attack, mon.defense)
    }
}

class Helper(@com.google.gson.annotations.SerializedName("mon_stats")
             val mons: Array<PokemonHelper>)

class PokemonHelper(val id: Int, val stamina: Int, val attack: Int, val defense: Int)