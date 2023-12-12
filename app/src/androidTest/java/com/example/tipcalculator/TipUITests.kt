package com.example.tipcalculator

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import com.example.tipcalculator.ui.theme.TipTimeTheme
import org.junit.Rule
import org.junit.Test
import java.text.NumberFormat

//  test that the app displays the correct tip value based on the bill amount and tip percentage inputs
class TipUITests {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun calculate_20_percent_tip() {
        composeTestRule.setContent {
            TipTimeTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    TipTimeLayout()
                }
                // UI components can be accessed as nodes through the composeTestRule.

                // pass in the text that you want entered to fill the TextField composable
                // by calling the performTextInput() method
                /*
                 * make sure that the Text composable displays the correct tip with an assertion
                 * by using the assertExists() method, which directly calls assertions on UI components
                */
//                val expectedTip = NumberFormat.getCurrencyInstance().format(2)
//                composeTestRule.onNodeWithText("Tip Amount: $expectedTip").assertExists(
//                    "No node with this text was found."
//                )
            }
        }
        composeTestRule.onNodeWithText("Bill Amount")
            .performTextInput("10")
        composeTestRule.onNodeWithText("Tip Percentage").performTextInput("20")

        val expectedTip = NumberFormat.getCurrencyInstance().format(2)
        composeTestRule.onNodeWithText("Tip Amount: $expectedTip").assertExists(
            "No node with this text was found."
        )
    }
}