
package com.dlgdev.goivcalc.ui

import android.os.Bundle
import com.dlgdev.goivcalc.R
import com.dlgdev.goivcalc.tools.Calculator
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_calculator.*
import javax.inject.Inject

class CalculatorActivity : DaggerAppCompatActivity() {
    @Inject lateinit var calc: Calculator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)

    }
}
