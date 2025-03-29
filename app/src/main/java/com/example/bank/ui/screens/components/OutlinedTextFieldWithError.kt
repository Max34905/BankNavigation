package com.example.bank.ui.screens.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bank.ui.theme.BankTheme

@Composable
fun OutlinedTextFieldWithError(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    placeholderText: String,
    errorMessage: String
) {
    OutlinedTextField(
        modifier = Modifier
            .padding(top = 40.dp)
            .width(300.dp),
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = label) },
        placeholder = {
            Text(
                text = placeholderText,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f)
            )
        },
        isError = errorMessage.isNotEmpty(),
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
        maxLines = 1
    )
    Box(
        modifier = Modifier
            .width(300.dp)
            .height(30.dp)
    ) {
        if (errorMessage.isNotEmpty()) {
            Text(
                text = errorMessage,
                fontSize = 10.sp,
                color = MaterialTheme.colorScheme.error,
                maxLines = 2
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OutlinedTextFieldWithErrorPreview() {
    BankTheme {
        OutlinedTextFieldWithError(
            value = "",
            onValueChange = { },
            label = "Label",
            placeholderText = "Placeholder",
            errorMessage = ""
        )
    }
}
