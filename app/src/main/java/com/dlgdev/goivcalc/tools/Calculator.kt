package com.dlgdev.goivcalc.tools

import javax.inject.Inject

class Calculator @Inject constructor() {
    fun add(a: Int, b: Int): Int {
        return a + b
    }

}