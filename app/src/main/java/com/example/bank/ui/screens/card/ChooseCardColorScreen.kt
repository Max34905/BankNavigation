package com.example.bank.ui.screens.card

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.bank.R
import com.example.bank.ui.screens.components.ScreenHeader
import com.example.bank.ui.theme.BankTheme

@Composable
fun ChooseCardColorScreenWithViewModel(
    navigateBackToConfirmPinCodeScreen: () -> Unit,
    navigateToMainScreen: () -> Unit,
    viewModel: ChooseCardColorScreenViewModel = hiltViewModel()
) {
    val name by viewModel.name.collectAsState()
    viewModel.getUserFullName()

    ChooseCardColorScreen(
        name = name,
        navigateBackToConfirmPinCodeScreen = navigateBackToConfirmPinCodeScreen,
        navigateToMainScreen = navigateToMainScreen,
        saveColor = { color ->
            viewModel.saveCardColor(color)
        }
    )
}

@Composable
private fun ChooseCardColorScreen(
    name: String,
    navigateBackToConfirmPinCodeScreen: () -> Unit,
    navigateToMainScreen: () -> Unit,
    saveColor: (Color) -> Unit,
) {
    val orangeColor = colorResource(id = R.color.orange)
    var cardColor by remember { mutableStateOf(orangeColor) }
    val colorOptions = remember {
        listOf(
            Color(0xFF831446), Color(0xFF8051e1), Color(0xFF50f2c0), Color(0xFF545da2),
            Color(0xFFf78ab7), Color(0xFF1dd4fd), Color(0xFFdd724e), Color(0xFF59f263),
            Color(0xFF22d28b), Color(0xFFb00962), Color(0xFFa438fd), Color(0xFF0dc0ad),
            Color(0xFFda7fcc), Color(0xFF6a6662), Color(0xFFcf7f17), Color(0xFF0739df)
        )
    }

    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .windowInsetsPadding(WindowInsets.safeDrawing),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ScreenHeader(
            title = stringResource(R.string.choose_card_color),
            navigateBack = navigateBackToConfirmPinCodeScreen
        )
        Text(
            modifier = Modifier.padding(start = 30.dp, end = 30.dp),
            text = stringResource(R.string.choose_card_color_hint),
            color = MaterialTheme.colorScheme.primary.copy(alpha = 0.7f),
            fontSize = 16.sp,
            maxLines = 3
        )
        Card (
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 16.dp)
                .width(400.dp)
                .height(200.dp),
            colors = CardDefaults.cardColors(
                containerColor = cardColor,
                contentColor = Color.White,
                disabledContainerColor = cardColor,
                disabledContentColor = Color.White
            ),
        ) {
            Column (
                modifier = Modifier.padding(20.dp)
            ) {
                Text(
                    text = name,
                    fontSize = 16.sp
                )
                Text(
                    text = stringResource(R.string.card_number),
                    fontSize = 20.sp
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = stringResource(R.string.card_description),
                    fontSize = 16.sp
                )
                Row {
                    Text(
                        text = stringResource(R.string.expiration_date),
                        fontSize = 20.sp,
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        text = stringResource(R.string.payment_system)
                    )
                }
            }
        }
        LazyVerticalGrid(
            modifier = Modifier.padding(horizontal = 16.dp),
            columns = GridCells.Fixed(4),
            contentPadding = PaddingValues(16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            items(colorOptions) { option ->
                ColorOption(
                    color = option,
                    isSelected = option == cardColor,
                    onClick = { cardColor = option }
                )
            }
        }
        Button(
            modifier = Modifier
                .padding(top = 30.dp)
                .width(200.dp),
            onClick = {
                saveColor(cardColor)
                navigateToMainScreen()
            }
        ) {
            Text(
                text = stringResource(R.string.confirm_color)
            )
        }
    }
}

@Composable
private fun ColorOption(
    color: Color,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Box(
        contentAlignment = Alignment.Center
    ) {
        Surface (
            modifier = Modifier
                .size(40.dp)
                .clickable(onClick = onClick),
            shape = CircleShape,
            color = color,
            border = if (isSelected) BorderStroke(2.dp, MaterialTheme.colorScheme.primary) else null,
        ) {
            if (isSelected) {
                Icon(
                    modifier = Modifier
                        .padding(8.dp),
                    imageVector = Icons.Filled.Check,
                    contentDescription = null
                )
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun ChooseCardColorScreenPreview() {
    BankTheme {
        ChooseCardColorScreen(navigateBackToConfirmPinCodeScreen = {},name = "John Doe", navigateToMainScreen = {}) {}
    }
}