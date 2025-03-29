package com.example.bank.ui.screens.pincode

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.bank.R
import com.example.bank.ui.screens.components.PinCodeInput
import com.example.bank.ui.screens.components.ScreenHeader
import com.example.bank.ui.theme.BankTheme

@Composable
fun ConfirmPinCodeScreenWithViewModel(
    navigateBackToSetPinCodeScreen: () -> Unit,
    navigateToChooseCardColorScreen: () -> Unit,
    viewModel: ConfirmPinCodeScreenViewModel = hiltViewModel()
) {
    val isPinCodeRight by viewModel.isPinCodeRight.collectAsState()

    ConfirmPinCodeScreen(
        navigateBackToSetPinCodeScreen = navigateBackToSetPinCodeScreen,
        navigateToChooseCardColorScreen = navigateToChooseCardColorScreen,
        checkPinCode = { pinCode ->
            viewModel.checkPinCode(pinCode)
        },
        savePinCode = { pinCode ->
            viewModel.savePinCode(pinCode)
        },
        isPinCodeRight = isPinCodeRight
    )
}

@Composable
private fun ConfirmPinCodeScreen(
    navigateBackToSetPinCodeScreen: () -> Unit,
    navigateToChooseCardColorScreen: () -> Unit,
    checkPinCode: (String) -> Unit,
    savePinCode: (String) -> Unit,
    isPinCodeRight: Boolean
) {
    var pinCode by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }
    val context = LocalContext.current
    val buttonsList = listOf("1", "2", "3", "4", "5", "6", "7", "8", "9")

    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .windowInsetsPadding(WindowInsets.safeDrawing),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ScreenHeader(
            title = stringResource(R.string.confirm_pin_code),
            navigateBack = navigateBackToSetPinCodeScreen
        )
        Box(
            modifier = Modifier
                .height(50.dp)
                .width(200.dp),
            contentAlignment = Alignment.Center,
        ) {
            if (errorMessage.isEmpty()) {
                Text(
                    modifier = Modifier
                        .width(200.dp),
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    text = stringResource(R.string.confirm_pin_code_text),
                    color = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f),
                    maxLines = 2
                )
            } else {
                Text (
                    modifier = Modifier
                        .width(100.dp),
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    text = errorMessage,
                    color = MaterialTheme.colorScheme.error,
                    maxLines = 2
                )
            }
        }
        Text(
            modifier = Modifier.padding(top = 20.dp),
            text = stringResource(R.string.confirm_pin_code_hint),
            color = MaterialTheme.colorScheme.primary.copy(alpha = 0.7f)
        )
        PinCodeInput(
            pinCode = pinCode,
            buttonsList = buttonsList,
            onPinCodeInput = { number ->
                if (pinCode.length < 4) {
                    pinCode = StringBuilder(pinCode).append(number).toString()
                }
                if (pinCode.length == 4) {
                    checkPinCode(pinCode)
                }
            },
            onIconButtonClick = { pinCode = pinCode.dropLast(1) },
            onSetButtonClick = {
                if (isPinCodeRight) {
                    savePinCode(pinCode)
                    navigateToChooseCardColorScreen()
                } else {
                    errorMessage = context.getString(R.string.pin_code_wrong)
                }
            }
        )
    }
}

@Preview(showSystemUi = true)
@Composable
private fun ConfirmPinCodeScreenPreview() {
    BankTheme {
        ConfirmPinCodeScreen({}, {}, {str -> }, {str ->}, false)
    }
}