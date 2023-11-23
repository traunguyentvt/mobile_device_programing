package com.tvt.foodiepal.utilities

import android.text.InputFilter
import android.text.Spanned

class MinMaxFilter(): InputFilter {

    private var intMin: Double = 0.0
    private var intMax: Double = 0.0

    // Initialized
    constructor(minValue: Double, maxValue: Double) : this() {
        this.intMin = minValue
        this.intMax = maxValue
    }

    override fun filter(source: CharSequence, start: Int, end: Int, dest: Spanned, dStart: Int, dEnd: Int): CharSequence? {
        try {
            val input: Double = (dest.toString() + source.toString()).toDouble()
            if (isInRange(intMin, intMax, input)) {
                return null
            }
        } catch (e: NumberFormatException) {
            e.printStackTrace()
        }
        return ""
    }

    // Check if input c is in between min a and max b and
    // returns corresponding boolean
    private fun isInRange(a: Double, b: Double, c: Double): Boolean {
        return if (b > a) c in a..b else c in b..a
    }
}