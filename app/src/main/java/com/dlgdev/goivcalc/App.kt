package com.dlgdev.goivcalc

import com.dlgdev.goivcalc.dagger.DaggerAppComponent
import com.dlgdev.goivcalc.dagger.MonsModule
import dagger.android.AndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.support.DaggerApplication

class App : DaggerApplication(), HasActivityInjector {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder()
                .monsModule(MonsModule(applicationContext)).build()
    }
}
