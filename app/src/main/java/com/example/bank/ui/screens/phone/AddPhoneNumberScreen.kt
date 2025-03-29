package com.example.bank.ui.screens.phone

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.example.bank.ui.screens.components.OutlinedTextFieldWithError
import com.example.bank.ui.screens.components.ScreenHeader
import com.example.bank.ui.theme.BankTheme
import com.example.bank.utils.InputValidator

@Composable
fun AddPhoneNumberScreenWithViewModel(
    navigateBackToUpdateProfileScreen: () -> Unit,
    navigateToSecurityQuestionScreen: () -> Unit,
    viewModel: AddPhoneNumberScreenViewModel = hiltViewModel()
) {
    AddPhoneNumberScreen(
        navigateBackToUpdateProfileScreen = navigateBackToUpdateProfileScreen,
        navigateToSecurityQuestionScreen = navigateToSecurityQuestionScreen,
        savePhoneNumber = { phoneNumber ->
            viewModel.savePhoneNumber(phoneNumber)
        }
    )
}

@Composable
private fun AddPhoneNumberScreen(
    navigateBackToUpdateProfileScreen: () -> Unit,
    navigateToSecurityQuestionScreen: () -> Unit,
    savePhoneNumber: (String) -> Unit
) {
    var phoneNumber by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }
    val context = LocalContext.current

    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .windowInsetsPadding(WindowInsets.safeDrawing),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ScreenHeader(
            title = stringResource(R.string.phone_number),
            navigateBack = navigateBackToUpdateProfileScreen,
        )
        Text(
            modifier = Modifier
                .padding(top = 40.dp),
            text = stringResource(R.string.add_phone_number_text),
            color = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f),
            textAlign = TextAlign.Center,
            fontSize = 20.sp,
            maxLines = 2
        )
        OutlinedTextFieldWithError(
            value = phoneNumber,
            onValueChange = { newValue ->
                if (newValue.length <= 16) {
                    phoneNumber = newValue
                }
            },
            label = stringResource(R.string.phone_number),
            placeholderText = stringResource(R.string.phone_number_placeholder),
            errorMessage = errorMessage
        )
        Button (
            modifier = Modifier
                .width(200.dp)
                .padding(top = 20.dp),
            onClick = {
                if (InputValidator.isPhoneNumberValid(phoneNumber)) {
                    savePhoneNumber(phoneNumber)
                    navigateToSecurityQuestionScreen()
                } else {
                    errorMessage = context.getString(R.string.phone_number_error)
                }
            }
        ) {
            Text(text = stringResource(R.string.confirm_phone_number_button))
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun AddPhoneNumberScreenPreview() {
    BankTheme {
        AddPhoneNumberScreen({}, {}) {str ->}
    }
}