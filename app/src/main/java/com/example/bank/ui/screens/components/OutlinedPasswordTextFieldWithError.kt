package com.example.bank.ui.screens.components

import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bank.R
import com.example.bank.ui.theme.BankTheme

@Composable
fun OutlinedPasswordTextFieldWithError(
    password: String,
    onPasswordChange: (String) -> Unit,
    showPassword: Boolean,
    onShowPasswordChange: () -> Unit,
    label: String,
    placeholderText: String,
    context: Context,
    errorMessage: String
) {
    OutlinedTextField(
        modifier = Modifier
            .padding(top = 20.dp)
            .width(300.dp),
        value = password,
        onValueChange = onPasswordChange,
        label = { Text(text = label) },
        placeholder = {
            Text(
                text = placeholderText,
                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f)
            )
        },
        visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            IconButton(
                onClick = onShowPasswordChange
            ) {
                Icon(imageVector = if(showPassword) {
                    Icons.Filled.Visibility
                } else {
                    Icons.Filled.VisibilityOff
                },
                    contentDescription = context.getString(R.string.toggle_visibility_description)
                )
            }
        },
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
        isError = errorMessage.isNotEmpty()
    )
    Box(
        modifier = Modifier
            .height(30.dp)
            .width(300.dp)
    ) {
        if (errorMessage.isNotEmpty()) {
            Text(
                text = errorMessage,
                fontSize = 10.sp,
                color = MaterialTheme.colorScheme.error
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OutlinedPasswordTextFieldWithErrorPreview() {
    BankTheme {
        OutlinedPasswordTextFieldWithError(
            password = "",
            onPasswordChange = {},
            showPassword = false,
            onShowPasswordChange = {},
            label = "Password",
            placeholderText = "Enter password",
            context = LocalContext.current,
            errorMessage = ""
        )
    }
}