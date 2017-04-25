package com.dlgdev.goivcalc

import android.app.Activity
import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class App : Application(), HasActivityInjector {

    @Inject lateinit var activityInjector: AndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
    }

    override fun activityInjector(): AndroidInjector<Activity>? {
        return activityInjector
    }
}