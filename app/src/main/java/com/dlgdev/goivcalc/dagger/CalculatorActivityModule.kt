package com.dlgdev.goivcalc.dagger

import com.dlgdev.goivcalc.ui.CalculatorActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class CalculatorActivityModule {
    @ContributesAndroidInjector
    abstract fun contributeInjector(): CalculatorActivity
}
