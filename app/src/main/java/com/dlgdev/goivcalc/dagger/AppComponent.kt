package com.dlgdev.goivcalc.dagger

import com.dlgdev.goivcalc.App
import dagger.Component
import dagger.android.AndroidInjectionModule

@Component(modules = arrayOf(AndroidInjectionModule::class, CalculatorActivityModule::class))
interface AppComponent {
    fun inject(app: App)

}
