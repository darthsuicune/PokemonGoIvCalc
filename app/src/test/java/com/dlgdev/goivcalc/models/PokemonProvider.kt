package com.dlgdev.goivcalc.models

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import java.nio.file.Files
import java.nio.file.Paths

class PokemonProvider {
    val filename = "src/main/assets/mon_stats.json"
    val stats = Gson().fromJson<Helper>(Files.newBufferedReader(Paths.get(filename)),
            Helper::class.java)

    fun get(id: Int, form: Int = 0): Pokemon {
        val mon = stats.mons.filter { it.id == id && it.form == form }[0]
        return Pokemon(mon.id, mon.stamina, mon.attack, mon.defense, mon.form)
    }
}

class Helper(@SerializedName("mon_stats") val mons: Array<PokemonHelper>)

class PokemonHelper(val id: Int, val form: Int, val stamina: Int, val attack: Int, val defense: Int)