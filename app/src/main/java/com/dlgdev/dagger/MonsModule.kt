package com.dlgdev.dagger

import android.content.Context
import com.dlgdev.goivcalc.R
import com.dlgdev.goivcalc.models.Pokemon
import com.dlgdev.goivcalc.models.PokemonRepo
import com.dlgdev.goivcalc.models.PokemonRepoImpl
import com.dlgdev.goivcalc.models.PokemonStats
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import java.io.InputStreamReader

@Module
class MonsModule {
    val mon_stats= "mon_stats.json"

    @Provides
    fun provideSavedMons(context: Context): Map<Pair<Int,Int>, Pokemon> {
        val names = context.resources.getStringArray(R.array.mon_names)
        val monStats =
                Gson().fromJson<PokemonStats>(InputStreamReader(context.assets.open(mon_stats)),
                        PokemonStats::class.java)
        return monStats.mons.map { it.name = names[it.id]; it.id to it.form to it }.toMap()
    }

    @Provides
    fun provideMonRepo(mons: Map<Pair<Int, Int>, Pokemon>): PokemonRepo {
        return PokemonRepoImpl(mons)
    }
}