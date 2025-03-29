package com.example.bank.ui.screens.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Button
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.bank.R
import com.example.bank.ui.screens.components.OutlinedPasswordTextFieldWithError
import com.example.bank.ui.screens.components.OutlinedTextFieldWithError
import com.example.bank.ui.theme.BankTheme

@Composable
fun LogInScreenWithViewModel(
    viewModel: LogInScreenViewModel = hiltViewModel(),
    navigateToUpdateProfileScreen: () -> Unit,
    navigateToSignUpScreen: () -> Unit,
    navigateToPhoneNumberScreen: () -> Unit,
    navigateToPinCodeScreen: () -> Unit,
    navigateToSecurityQuestionScreen: () -> Unit,
    navigateToCardColorScreen: () -> Unit,
    navigateToMainScreen: () -> Unit
) {
    val isProfileInfoFilled by viewModel.isProfileInfoFilled.collectAsState()
    val isUserExists by viewModel.isUserExists.collectAsState()
    val isPhoneNumberAdded by viewModel.isPhoneNumberAdded.collectAsState()
    val isPinCodeAdded by viewModel.isPinCodeAdded.collectAsState()
    val isSecurityQuestionAnswerAdded by viewModel.isSecurityQuestionAnswerAdded.collectAsState()
    val isCardColorAdded by viewModel.isCardColorAdded.collectAsState()

    LogInScreen(
        checkEmailAndPassword = { email, password ->
            viewModel.checkEmailAndPassword(email, password)
        },
        navigateToUpdateProfileScreen = navigateToUpdateProfileScreen,
        navigateToSignUpScreen = navigateToSignUpScreen,
        navigateToPhoneNumberScreen = navigateToPhoneNumberScreen,
        navigateToMainScreen = navigateToMainScreen,
        isProfileInfoFilled = isProfileInfoFilled,
        isUserExists = isUserExists,
        isPhoneNumberAdded = isPhoneNumberAdded,
        isPinCodeAdded = isPinCodeAdded,
        isSecurityQuestionAnswerAdded = isSecurityQuestionAnswerAdded,
        isCardColorAdded = isCardColorAdded,
        navigateToPinCodeScreen = navigateToPinCodeScreen,
        navigateToSecurityQuestionScreen = navigateToSecurityQuestionScreen,
        navigateToCardColorScreen = navigateToCardColorScreen
    )
}

@Composable
private fun LogInScreen(
    checkEmailAndPassword: (String, String) -> Unit,
    navigateToUpdateProfileScreen: () -> Unit,
    navigateToSignUpScreen: () -> Unit,
    navigateToPhoneNumberScreen: () -> Unit,
    navigateToPinCodeScreen: () -> Unit,
    navigateToSecurityQuestionScreen: () -> Unit,
    navigateToCardColorScreen: () -> Unit,
    navigateToMainScreen: () -> Unit,
    isProfileInfoFilled: Boolean,
    isUserExists: Boolean,
    isPhoneNumberAdded: Boolean,
    isPinCodeAdded: Boolean,
    isSecurityQuestionAnswerAdded: Boolean,
    isCardColorAdded: Boolean
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var showPassword by remember {mutableStateOf(false)}
    var errorMessage by remember { mutableStateOf("") }
    val context = LocalContext.current

    Column (
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .windowInsetsPadding(WindowInsets.safeDrawing)
    ) {
        Text(
            modifier = Modifier
                .padding(start = 20.dp, top = 20.dp),
            text = stringResource(R.string.log_in_banner),
            fontSize = 50.sp
        )
        Column (
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextFieldWithError(
                value = email,
                onValueChange = { newValue ->
                    email = newValue
                    checkEmailAndPassword(email, password)
                },
                label = stringResource(R.string.email),
                placeholderText = stringResource(R.string.email),
                errorMessage = errorMessage
            )
            OutlinedPasswordTextFieldWithError(
                password = password,
                onPasswordChange = { newValue ->
                    password = newValue
                    checkEmailAndPassword(email, password)
                },
                showPassword = showPassword,
                onShowPasswordChange = { showPassword = !showPassword },
                errorMessage = errorMessage,
                label = stringResource(R.string.password),
                placeholderText = stringResource(R.string.password),
                context = context
            )
            Spacer(modifier = Modifier.weight(1f))
            Button(
                modifier = Modifier
                    .width(200.dp),
                onClick = {
                    if (isUserExists) {
                        if (
                            isProfileInfoFilled
                            && isPhoneNumberAdded
                            && isPinCodeAdded
                            && isSecurityQuestionAnswerAdded
                            && isCardColorAdded
                        ) {
                            navigateToMainScreen()
                        } else if (!isProfileInfoFilled) {
                            navigateToUpdateProfileScreen()
                        } else if (!isPhoneNumberAdded) {
                            navigateToPhoneNumberScreen()
                        } else if (!isSecurityQuestionAnswerAdded) {
                            navigateToSecurityQuestionScreen()
                        } else if (!isPinCodeAdded) {
                            navigateToPinCodeScreen()
                        } else {
                            navigateToCardColorScreen()
                        }
                    } else if (email.isEmpty() || password.isEmpty()) {
                        errorMessage = context.getString(R.string.email_or_password_empty)
                    } else {
                        errorMessage = context.getString(R.string.log_in_error)
                    }
                }
            ) {
                Text(text = stringResource(R.string.log_in_banner))
            }
            Button(
                modifier = Modifier
                    .padding(vertical = 16.dp)
                    .width(200.dp),
                onClick = {
                    navigateToSignUpScreen()
                }
            ) {
                Text(text = stringResource(R.string.sign_up_banner))
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun LoginScreenPreview() {
    BankTheme {
        LogInScreen(
            checkEmailAndPassword = { val1, val2 ->
                true
            },
            navigateToUpdateProfileScreen = {},
            navigateToSignUpScreen = {},
            navigateToMainScreen = {},
            navigateToSecurityQuestionScreen = {},
            navigateToPinCodeScreen = {},
            navigateToCardColorScreen = {},
            navigateToPhoneNumberScreen = {},
            isProfileInfoFilled = false,
            isUserExists = false,
            isPhoneNumberAdded = false,
            isPinCodeAdded = false,
            isSecurityQuestionAnswerAdded = false,
            isCardColorAdded = false
        )
    }
}