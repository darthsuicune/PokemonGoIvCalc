package com.dlgdev.goivcalc.models

import com.google.gson.annotations.SerializedName

class PokemonStats(@SerializedName("mon_stats.json")val mons: List<Pokemon>, val version: Int)