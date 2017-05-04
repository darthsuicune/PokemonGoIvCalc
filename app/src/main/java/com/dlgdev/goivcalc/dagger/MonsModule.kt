package com.dlgdev.goivcalc.dagger

import com.dlgdev.goivcalc.models.Pokemon
import dagger.Module
import dagger.Provides

@Module
class MonsModule {
    @Provides fun provideMons(): List<Pokemon> {
        return listOf(Pokemon(1,1,1,1,"mon"))
    }
}