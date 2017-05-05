package com.dlgdev.goivcalc.dagger

import android.content.Context
import com.dlgdev.goivcalc.R
import com.dlgdev.goivcalc.models.Pokemon
import com.dlgdev.goivcalc.models.PokemonStats
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import java.io.InputStreamReader

@Module
class MonsModule(val context: Context) {
    val names = context.resources.getStringArray(R.array.mon_names)

    @Provides fun provideMons(): List<Pokemon> {
        val monStats = Gson().fromJson<PokemonStats>(InputStreamReader(context.assets.open("mons")),
                PokemonStats::class.java)
        monStats.mons.forEach { it.name = names[it.id] }
        return monStats.mons
    }

    @Provides fun provideContext(): Context {
        return context
    }
}