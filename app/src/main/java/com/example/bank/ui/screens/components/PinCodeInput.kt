package com.example.bank.ui.screens.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Backspace
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bank.R
import com.example.bank.ui.theme.BankTheme

@Composable
fun PinCodeInput(
    pinCode: String,
    buttonsList: List<String>,
    onPinCodeInput: (String) -> Unit,
    onIconButtonClick: () -> Unit,
    onSetButtonClick: () -> Unit
) {
    Row (
        modifier = Modifier
            .padding(top = 20.dp)
            .width(150.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        PinCircle(isFilled = pinCode.length > 0)
        PinCircle(isFilled = pinCode.length > 1)
        PinCircle(isFilled = pinCode.length > 2)
        PinCircle(isFilled = pinCode.length > 3)
    }
    Spacer(modifier = Modifier.height(20.dp))
    PinCodeButtonsRow(
        buttonsList = buttonsList.subList(0, 3),
        onPinCodeChange = onPinCodeInput
    )
    PinCodeButtonsRow(
        buttonsList = buttonsList.subList(3, 6),
        onPinCodeChange = onPinCodeInput
    )
    PinCodeButtonsRow(
        buttonsList = buttonsList.subList(6, 9),
        onPinCodeChange = onPinCodeInput
    )
    Row (
        modifier = Modifier
            .padding(top = 20.dp)
            .width(300.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.weight(1f))
        Box(
            modifier = Modifier.weight(1f),
            contentAlignment = Alignment.Center
        ) {
            PinCodeButton(
                number = "0",
                onClick = { onPinCodeInput("0") }
            )
        }

        Box(
            modifier = Modifier.weight(1f),
            contentAlignment = Alignment.Center
        ) {
            IconButton(
                onClick = onIconButtonClick
            ) {
                Icon(
                    modifier = Modifier.size(70.dp),
                    imageVector = Icons.AutoMirrored.Filled.Backspace,
                    contentDescription = "Delete"
                )
            }
        }
    }
    Button(
        modifier = Modifier
            .padding(top = 40.dp)
            .width(200.dp),
        onClick = onSetButtonClick
    ) {
        Text(
            text = stringResource(id = R.string.set_pin_code)
        )
    }
}

@Composable
private fun PinCircle(
    isFilled: Boolean = false
) {
    Box(
        modifier = Modifier
            .size(20.dp)
            .background(
                color = if (isFilled) MaterialTheme.colorScheme.primary else Color.Transparent,
                shape = CircleShape
            )
            .border(
                width = 2.dp,
                color = MaterialTheme.colorScheme.primary,
                shape = CircleShape
            )
    )
}

@Composable
private fun PinCodeButtonsRow(
    buttonsList: List<String>,
    onPinCodeChange: (String) -> Unit,
) {
    Row (
        modifier = Modifier
            .width(300.dp)
            .padding(top = 20.dp, start = 20.dp, end = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        PinCodeButton(
            number = buttonsList.get(0),
            onClick = { onPinCodeChange(buttonsList.get(0)) }
        )
        PinCodeButton(
            number = buttonsList.get(1),
            onClick = { onPinCodeChange(buttonsList.get(1)) }
        )
        PinCodeButton(
            number = buttonsList.get(2),
            onClick = { onPinCodeChange(buttonsList.get(2)) }
        )
    }
}

@Composable
private fun PinCodeButton(
    number: String,
    onClick: () -> Unit
) {
    Button(
        modifier = Modifier.size(70.dp),
        shape = CircleShape,
        onClick = onClick
    ) {
        Text(
            text = number,
            fontSize = 30.sp,
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun PinCodeInputPreview() {
    BankTheme {
        Column (
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            PinCodeInput(
                pinCode = "",
                buttonsList = listOf("1", "2", "3", "4", "5", "6", "7", "8", "9", "0"),
                onPinCodeInput = {},
                onIconButtonClick = {},
                onSetButtonClick = {}
            )
        }
    }
}