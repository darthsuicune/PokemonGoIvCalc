package com.dlgdev.goivcalc.models

import com.google.gson.annotations.SerializedName

class PokemonStats(@SerializedName("mon_stats")val mons: List<Pokemon>)