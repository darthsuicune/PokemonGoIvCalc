package com.dlgdev.goivcalc.ui

import android.content.Context
import android.widget.ArrayAdapter
import com.dlgdev.goivcalc.models.Pokemon
import javax.inject.Inject

class PokemonNameAdapter @Inject constructor(context: Context, mons: List<Pokemon>) :
        ArrayAdapter<Pokemon>(context, android.R.layout.simple_dropdown_item_1line, mons)

