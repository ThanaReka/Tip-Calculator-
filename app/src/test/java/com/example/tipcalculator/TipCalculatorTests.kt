package com.example.tipcalculator

import junit.framework.TestCase.assertEquals
import org.junit.Test
import java.text.NumberFormat

class TipCalculatorTests {
    /*
     * Annotate the method with the @Test annotation to let the compiler know that the method
     * is a test method and runs the method accordingly.
     */
    @Test
    fun calculateTip_20PercentNoRoundup() {
        val amount = 10.00
        val tipPercent = 20.00
        val expectedTip = NumberFormat.getCurrencyInstance().format(2)
        val actualTip = calculateTip(amount = amount, tipPercent = tipPercent, false)
        assertEquals(expectedTip, actualTip)
        /*
         * Making an assertion is typically the end goal of an automated test
         * and it's not something commonly used in app code.
         * The assertEquals() method takes two parameters—an expected value and an actual value.
         * If those values are equal, the assertion and the test pass. If they're not equal,
         * the assertion and the test fail.
         */

    }
}