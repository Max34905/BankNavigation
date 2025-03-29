package com.example.bank.ui.screens.main

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import com.example.bank.R
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import com.example.bank.ui.theme.BankTheme
import androidx.core.net.toUri
import com.example.bank.model.BankCard

@Composable
fun MainScreenWithViewModel(
    navigateToUpdateProfileScreen: () -> Unit,
    navigateToLogInScreen: () -> Unit,
    viewModel: MainScreenViewModel = hiltViewModel()
) {
    val filePath = viewModel.filePath.collectAsState()
    val fullName = viewModel.fullName.collectAsState()
    val email = viewModel.email.collectAsState()
    val phoneNumber = viewModel.phoneNumber.collectAsState()

    MainScreen(
        filePath = filePath.value,
        fullName = fullName.value,
        email = email.value,
        phoneNumber = phoneNumber.value,
        navigateToLogInScreen = navigateToLogInScreen,
        navigateToUpdateProfileScreen = navigateToUpdateProfileScreen
    )
}

@Composable
private fun MainScreen(
    filePath: String,
    fullName: String,
    email: String,
    phoneNumber: String,
    navigateToLogInScreen: () -> Unit,
    navigateToUpdateProfileScreen: () -> Unit
) {
    val context = LocalContext.current
    val personalInfo = remember(fullName, email, phoneNumber) {
        listOf(
            (context.getString(R.string.account_number) to context.getString(R.string.account_number_value)),
            (context.getString(R.string.username) to fullName),
            (context.getString(R.string.email) to email),
            (context.getString(R.string.phone_number) to phoneNumber),
            (context.getString(R.string.address) to context.getString(R.string.address_value))
        )
    }
    val bankCards = remember {
        listOf(
            BankCard(
                image = R.drawable.mastercard_logo,
                name = "Visa",
                number = "4174",
                paymentSystem = "Master Card",
                balance = "$45788.55"
            ),
            BankCard(
                image = R.drawable.paypal_logo,
                name = "PayPal",
                number = "5768",
                paymentSystem = "Visa",
                balance = "$876.92"
            )
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .windowInsetsPadding(WindowInsets.safeDrawing),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row (
            modifier = Modifier.padding(top = 10.dp, start = 16.dp, end = 16.dp),
        ) {
            IconButton(
                onClick = { navigateToLogInScreen() }
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.Logout,
                    contentDescription = stringResource(R.string.log_out_button_description)
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            IconButton(
                onClick = { navigateToUpdateProfileScreen() }
            ) {
                Icon(
                    imageVector = Icons.Outlined.Edit,
                    contentDescription = stringResource(R.string.edit_button_description)
                )
            }
        }
        Text (
            modifier = Modifier.padding(top = 16.dp),
            text = stringResource(R.string.your_profile_information),
            color = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f),
            fontSize = 18.sp,
        )
        AsyncImage(
            modifier = Modifier
                .size(200.dp)
                .padding(20.dp)
                .clip(CircleShape),
            model = ImageRequest.Builder(LocalContext.current)
                .data(if(filePath != "null") filePath.toUri() else R.drawable.user)
                .build(),
            contentDescription = stringResource(R.string.profile_picture),
            contentScale = ContentScale.Crop
        )
        Row {
            Text(
                modifier = Modifier.padding(start = 16.dp, top = 20.dp),
                text = stringResource(R.string.personal_info),
                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f),
                fontSize = 25.sp
            )
            Spacer(modifier = Modifier.weight(1f))
        }
        LazyColumn (
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp,start = 16.dp, end = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(personalInfo) { pair ->
                PersonalInfoCard(
                    title = pair.first,
                    info = pair.second
                )
            }
        }
        Row {
            Text(
                modifier = Modifier.padding(start = 16.dp, top = 30.dp),
                text = stringResource(R.string.my_cards),
                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f),
                fontSize = 25.sp
            )
            Spacer(modifier = Modifier.weight(1f))
        }
        LazyRow (
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(bankCards) { card ->
                CreditCard(
                    cardImage = card.image,
                    cardName = card.name,
                    paymentSystem = card.paymentSystem,
                    cardNumber = card.number,
                    balance = card.balance
                )
            }
        }
    }
}

@Composable
private fun PersonalInfoCard(
    title: String,
    info: String
) {
    Card {
        Row {
            Text(
                modifier = Modifier.padding(start = 16.dp),
                text = title,
                fontSize = 15.sp
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                modifier = Modifier
                    .padding(end = 16.dp)
                    .width(120.dp),
                text = info,
                fontSize = 15.sp,
                maxLines = 1
            )
        }
    }
}

@Composable
private fun CreditCard(
    @DrawableRes cardImage: Int,
    cardName: String,
    paymentSystem: String,
    cardNumber: String,
    balance: String
) {
    Card (
        modifier = Modifier
            .width(320.dp)
            .height(70.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
        border = BorderStroke(2.dp, MaterialTheme.colorScheme.outline)
    ) {
        Row (
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Image(
                modifier = Modifier
                    .width(50.dp)
                    .height(50.dp)
                    .padding(start = 8.dp),
                painter = painterResource(id = cardImage),
                contentDescription = stringResource(R.string.bank_card_image_description)
            )
            Column {
                Text(
                    text = cardName
                )
                Row {
                    Text(text = paymentSystem)
                    Text(text = " • $cardNumber")
                }
            }
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = balance,
                modifier = Modifier.padding(end = 8.dp),
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun MainScreenPreview() {
    BankTheme {
        MainScreen(filePath = "null", fullName = "Maksym Chaban", email = "maksym.chaban@example.com", phoneNumber = "+57346856345654", {}, {})
    }
}