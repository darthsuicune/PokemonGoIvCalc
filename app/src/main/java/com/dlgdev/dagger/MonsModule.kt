package com.dlgdev.dagger

import android.content.Context
import com.dlgdev.goivcalc.R
import com.dlgdev.goivcalc.models.Pokemon
import com.dlgdev.goivcalc.models.PokemonStats
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import java.io.InputStreamReader

@Module
class MonsModule {

    @Provides fun provideMons(context: Context): List<Pokemon> {
        val names = context.resources.getStringArray(R.array.mon_names)
        val monStats = Gson().fromJson<PokemonStats>(InputStreamReader(context.assets.open("mon_stats")),
                PokemonStats::class.java)
        monStats.mons.forEach { it.name = names[it.id] }
        return monStats.mons
    }
}