package com.dlgdev.goivcalc.ui

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.dlgdev.goivcalc.R
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CalculatorActivityTest {

    @JvmField
    @Rule val rule = ActivityTestRule(CalculatorActivity::class.java, true, true)

    @Before
    fun setUp() {
        rule.activity
    }

    @Test
    fun testSomeStuff() {
        onView(withId(R.id.dust_label)).check(matches(withText("Dust")))
    }
}