package com.example.tipcalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tipcalculator.ui.theme.TipTimeTheme
import com.example.tiptime.R
import java.text.NumberFormat

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            TipTimeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    TipTimeLayout()
                }
            }
        }
    }
}

@Composable
fun TipTimeLayout() {
/*
 * use state hoisting to hoist/extracting, the state from a composable to make it stateless composable
 * (i.e. a composable that doesn't store its own state. It displays whatever state it's given
 * as input arguments.
 */

    var amountInput by remember { mutableStateOf("") }

    val amount = amountInput.toDoubleOrNull()?:0.0
    val tip = calculateTip(amount)

/*  This makes EditNumberField stateless by having hoisted the UI state to its ancestor, TipTimeLayout().
 *  The TipTimeLayout() is the state(amountInput) owner now.
 */

    Column(
        modifier = Modifier
            .statusBarsPadding()
            .padding(horizontal = 40.dp)
            .safeDrawingPadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(R.string.calculate_tip),
            modifier = Modifier
                .padding(bottom = 16.dp, top = 40.dp)
                .align(alignment = Alignment.Start)
        )
        EditNumberField(
            value = amountInput,
            onValueChange = { amountInput = it },
            modifier = Modifier
            .padding(bottom = 32.dp)
            .fillMaxWidth())
        Text(
//            text = stringResource(R.string.tip_amount, "$0.00"),
            text = stringResource(R.string.tip_amount, tip),
            style = MaterialTheme.typography.displaySmall
        )
        Spacer(modifier = Modifier.height(150.dp))
    }
}


@Composable
fun EditNumberField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier){



    // This is the state of the app for the bill amount (i.e. any value that can change over time)
    // val amountInput = "0"
    /*
     * need to change the above code so that the state of the app can be updated when the user
     * updates the bill amount
     */


//    var amountInput by remember { mutableStateOf("") }
//    // convert the amountInput value, which contains the amount entered by the use from String to Double
//    // and add an ?: Elvis operator that returns a 0.0 value when amountInput is null:
//    val amount = amountInput.toDoubleOrNull()?:0.0
//
//    val tip = calculateTip(amount)

    // Compose keeps track of each composable that reads state value
    // properties and triggers a recomposition when its value changes.

    TextField(
      //value = amountInput.value,
//        value = amountInput,
        // the it variable contains the new value in the lambda expression
     // onValueChange = {amountInput.value = it},
//        onValueChange = {amountInput = it},
        value = value,
        onValueChange = onValueChange,
//        label = {Text(stringResource(R.string.bill_amount))},
        label = {Text(stringResource(R.string.bill_amount))},
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        modifier = modifier
        )
}


/**
 * Calculates the tip based on the user input and format the tip amount
 * according to the local currency.
 * Example would be "$10.00".
 */
private fun calculateTip(amount: Double, tipPercent: Double = 15.0): String {
    val tip = tipPercent / 100 * amount
    return NumberFormat.getCurrencyInstance().format(tip)
}

@Preview(showBackground = true)
@Composable
fun TipTimeLayoutPreview() {
    TipTimeTheme {
        TipTimeLayout()
    }
}

