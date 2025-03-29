package com.example.bank.ui.screens.signup

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
import com.example.bank.utils.InputValidator

@Composable
fun SignUpScreenWithViewModel(
    viewModel: SignUpScreenViewModel = hiltViewModel(),
    navigateToLogInScreen: () -> Unit,
    navigateToUpdateProfileScreen: () -> Unit
) {
    val isEmailInUse by viewModel.emailInUseState.collectAsState(initial = false)

    SignUpScreen(
        navigateToLogInScreen = navigateToLogInScreen,
        navigateToUpdateProfileScreen = navigateToUpdateProfileScreen,
        registerUser = { email, password ->
            viewModel.registerUser(email, password)
        },
        checkEmail = { email ->
                viewModel.checkIfEmailExists(email)
        },
        isEmailInUse = isEmailInUse == true
    )
}

@Composable
private fun SignUpScreen(
    navigateToLogInScreen: () -> Unit,
    navigateToUpdateProfileScreen: () -> Unit,
    registerUser: (String, String) -> Unit,
    checkEmail: (String) -> Unit,
    isEmailInUse: Boolean
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var showPassword by remember {mutableStateOf(false)}
    var showPasswordConfirmation by remember {mutableStateOf(false)}
    var emailError by remember {mutableStateOf("")}
    var passwordError by remember {mutableStateOf("")}
    val context = LocalContext.current

    Column (
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .windowInsetsPadding(WindowInsets.safeDrawing)
    ) {
        Text(
            modifier = Modifier
                .padding(start = 20.dp, top = 16.dp),
            text = stringResource(R.string.sign_up_banner),
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
                    emailError = if (InputValidator.isEmailValid(email)) "" else context.getString(R.string.email_error)
                },
                label = stringResource(R.string.email),
                placeholderText = stringResource(R.string.email),
                errorMessage = emailError,
            )
            OutlinedPasswordTextFieldWithError(
                password = password,
                onPasswordChange = { newValue ->
                    password = newValue
                    passwordError = if (InputValidator.isPasswordValid(password)) "" else context.getString(R.string.invalid_password)
                },
                label = stringResource(R.string.password),
                placeholderText = stringResource(R.string.password),
                showPassword = showPassword,
                onShowPasswordChange = { showPassword = !showPassword },
                errorMessage = passwordError,
                context = context
            )
            OutlinedPasswordTextFieldWithError(
                password = confirmPassword,
                onPasswordChange = { newValue ->
                    confirmPassword = newValue
                },
                label = stringResource(R.string.confirm_password),
                placeholderText = stringResource(R.string.confirm_password),
                showPassword = showPasswordConfirmation,
                onShowPasswordChange = { showPasswordConfirmation = !showPasswordConfirmation },
                errorMessage = passwordError,
                context = context
            )
            Spacer(modifier = Modifier.weight(1f))
            Button(
                modifier = Modifier
                    .width(200.dp),
                onClick = {
                    checkEmail(email)
                    if (
                        InputValidator.isEmailValid(email)
                        && InputValidator.isPasswordValid(password)
                        && password.equals(confirmPassword)
                        && !isEmailInUse
                    ) {
                        navigateToUpdateProfileScreen()
                        registerUser(email, password)
                    } else if(email.isEmpty()) {
                        emailError = context.getString(R.string.empty_email)
                    } else if (!InputValidator.isEmailValid(email)) {
                        emailError = context.getString(R.string.email_error)
                    } else if(password.isEmpty()) {
                        passwordError = context.getString(R.string.empty_password)
                    } else if (!InputValidator.isPasswordValid(password)) {
                        passwordError = context.getString(R.string.invalid_password)
                    } else if (!password.equals(confirmPassword)) {
                        passwordError = context.getString(R.string.password_mismatch)
                    } else if (isEmailInUse) {
                        emailError = context.getString(R.string.email_exists)
                    }
                }
            ) {
                Text(text = stringResource(R.string.sign_up_banner))
            }
            Button(
                modifier = Modifier
                    .padding(vertical = 16.dp)
                    .width(200.dp),
                onClick = { navigateToLogInScreen() }
            ) {
                Text(text = stringResource(R.string.log_in_banner))
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun SignUpScreenPreview() {
    BankTheme {
        SignUpScreen(
            registerUser = {email, password ->

            },
            navigateToLogInScreen = {},
            navigateToUpdateProfileScreen = {},
            isEmailInUse = false,
            checkEmail = { email: String ->

            }
        )
    }
}