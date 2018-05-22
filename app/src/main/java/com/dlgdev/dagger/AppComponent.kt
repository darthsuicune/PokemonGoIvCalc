package com.dlgdev.dagger

import com.dlgdev.goivcalc.App
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@Component(modules = arrayOf(AndroidSupportInjectionModule::class, CalculatorActivityModule::class))
interface AppComponent : AndroidInjector<App>
