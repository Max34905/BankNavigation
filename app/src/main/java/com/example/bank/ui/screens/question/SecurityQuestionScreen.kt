package com.example.bank.ui.screens.question

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.bank.R
import com.example.bank.ui.screens.components.ScreenHeader
import com.example.bank.ui.theme.BankTheme

@Composable
fun SecurityQuestionScreenWithViewModel(
    navigateBackToPhoneNumberScreen: () -> Unit,
    navigateToSetPinCodeScreen: () -> Unit,
    viewModel: SecurityScreenViewModel = hiltViewModel()
) {
    SecurityQuestionScreen(
        navigateBack = navigateBackToPhoneNumberScreen,
        navigateForward = navigateToSetPinCodeScreen,
        saveSecurityAnswer = { securityQuestion ->
            viewModel.saveSecurityQuestion(securityQuestion)
        }
    )
}

@Composable
private fun SecurityQuestionScreen(
    navigateBack: () -> Unit,
    navigateForward: () -> Unit,
    saveSecurityAnswer: (String) -> Unit
) {
    var securityAnswer by remember { mutableStateOf("") }
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
            title = stringResource(R.string.security_question_banner),
            navigateBack = navigateBack
        )
        Text(
            modifier = Modifier
                .padding(top = 40.dp, start = 10.dp, end = 10.dp),
            text = stringResource(R.string.security_question),
            fontSize = 50.sp,
            color = MaterialTheme.colorScheme.primary,
            lineHeight = 50.sp,
            maxLines = 3
        )
        Text(
            modifier = Modifier.padding(top = 10.dp, start = 10.dp, end = 10.dp),
            text = stringResource(R.string.security_answer_hint),
            color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.5f),
            fontSize = 15.sp
        )
        TextField(
            modifier = Modifier
                .padding(top = 40.dp, start = 10.dp, end = 10.dp)
                .width(400.dp),
            value = securityAnswer,
            onValueChange = { securityAnswer = it },
            label = { Text(text = stringResource(R.string.answer)) },
            placeholder = {
                Text(
                    text = stringResource(R.string.security_answer_placeholder),
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f)
                )
            },
            isError = errorMessage.isNotEmpty(),
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
            maxLines = 3,
            minLines = 3
        )
        Box(
            modifier = Modifier
                .padding(start = 10.dp, end = 10.dp)
                .width(400.dp)
                .height(30.dp)
        ) {
            if (errorMessage.isNotEmpty()) {
                Text(
                    text = errorMessage,
                    fontSize = 10.sp,
                    color = MaterialTheme.colorScheme.error
                )
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        Button(
            modifier = Modifier
                .width(200.dp)
                .padding(bottom = 50.dp),
            onClick = {
                if (securityAnswer.isNotEmpty()) {
                    saveSecurityAnswer(securityAnswer)
                    navigateForward()
                } else {
                    errorMessage = context.getString(R.string.security_answer_error)
                }
            }
        ) {
            Text(text = stringResource(R.string.save))
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun AddSecurityQuestionScreenPreview() {
    BankTheme {
        SecurityQuestionScreen({}, {}) {}
    }
}