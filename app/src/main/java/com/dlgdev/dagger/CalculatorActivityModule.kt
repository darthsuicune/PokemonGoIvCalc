package com.dlgdev.dagger

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.dlgdev.goivcalc.ui.CalculatorActivity
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

@Module
abstract class CalculatorActivityModule {
    @ContributesAndroidInjector(modules = arrayOf(CalculatorActivitySubModule::class))
    abstract fun contributeInjector(): CalculatorActivity
}

@Module(includes = [(MonsModule::class),(PokemonServerModule::class)])
class CalculatorActivitySubModule {
    @Provides fun provideResultsLayoutManager(context: Context): RecyclerView.LayoutManager {
        return LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }

    @Provides fun provideContext(activity: CalculatorActivity): Context {
        return activity
    }
}
