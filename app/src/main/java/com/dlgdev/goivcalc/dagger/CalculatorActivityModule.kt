package com.dlgdev.goivcalc.dagger

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

@Module
class CalculatorActivitySubModule {
    @Provides fun provideResultsLayoutManager(context: Context): RecyclerView.LayoutManager {
        return LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }
}
