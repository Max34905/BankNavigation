package com.example.bank.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bank.R
import com.example.bank.ui.theme.BankTheme

@Composable
fun StartScreen(
    navigateToSignUpScreen: () -> Unit
) {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .windowInsetsPadding(WindowInsets.safeDrawing),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
    ) {
        Text(
            modifier = Modifier.padding(top = 16.dp),
            text = stringResource(R.string.bank_name_banner),
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            color = MaterialTheme.colorScheme.secondary
        )
        Text(
            modifier = Modifier.padding(top = 250.dp),
            text = stringResource(R.string.welcome_text),
            fontSize = 40.sp,
            color = MaterialTheme.colorScheme.secondary
        )
        Text(
            text = stringResource(R.string.bank_name_banner),
            fontSize = 40.sp,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = stringResource(R.string.small_text),
            color = MaterialTheme.colorScheme.secondary
        )
        Button(
            modifier = Modifier
                .padding(top = 100.dp)
                .width(200.dp),
            onClick = navigateToSignUpScreen
        ) {
            Text(
                text = stringResource(R.string.get_started),
                fontSize = 20.sp
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun StartScreenPreview() {
    BankTheme {
        StartScreen(
            navigateToSignUpScreen = {}
        )
    }
}