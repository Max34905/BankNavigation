package com.example.bank.ui.screens.profile

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Upload
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.bank.R
import com.example.bank.ui.screens.components.OutlinedTextFieldWithError
import com.example.bank.ui.screens.components.ScreenHeader
import com.example.bank.ui.theme.BankTheme
import com.example.bank.utils.InputValidator

@Composable
fun UpdateProfileScreenWithViewModel(
    navigateBack: () -> Unit,
    navigateToAddPhoneNumberScreen: () -> Unit,
    viewModel: UpdateProfileScreenViewModel = hiltViewModel()
) {
    UpdateProfileScreen(
        navigateBack = navigateBack,
        navigateToAddPhoneNumberScreen = navigateToAddPhoneNumberScreen,
        updateProfileInfo = { firstName, lastName, uri ->
            viewModel.saveProfileInfo(firstName, lastName, uri)
        }
    )
}

@Composable
private fun UpdateProfileScreen(
    navigateBack: () -> Unit,
    navigateToAddPhoneNumberScreen: () -> Unit,
    updateProfileInfo: (String, String, Uri?) -> Unit
) {
    var firstName by remember { mutableStateOf("") }
    var lastName by remember {mutableStateOf("")}
    var errorMessage by remember {mutableStateOf("")}
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    val filePicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        imageUri = uri
    }
    val context = LocalContext.current

    Column (
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ScreenHeader(
            title = stringResource(R.string.profile_banner),
            navigateBack = navigateBack,
        )
        Text(
            modifier = Modifier.padding(top = 40.dp),
            text = stringResource(R.string.set_up_profile),
            color = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f)
        )
        Box (
            modifier = Modifier
                .padding(top = 20.dp)
                .size(200.dp)
                .background(MaterialTheme.colorScheme.primary, shape = CircleShape),
            contentAlignment = Alignment.Center
        ) {
            IconButton(
                modifier = Modifier.size(200.dp),
                onClick = {
                    filePicker.launch("image/*")
                }
            ) {
                Icon(
                    modifier = Modifier.size(150.dp),
                    imageVector = Icons.Filled.Upload,
                    contentDescription = stringResource(R.string.upload_button_description)
                )
            }
        }
        OutlinedTextFieldWithError(
            value = firstName,
            onValueChange = { newValue ->
                firstName = newValue
            },
            label = stringResource(R.string.first_name),
            placeholderText = stringResource(R.string.first_name),
            errorMessage = errorMessage
        )
        OutlinedTextFieldWithError(
            value = lastName,
            onValueChange = { newValue ->
                lastName = newValue
            },
            label = stringResource(R.string.last_name),
            placeholderText = stringResource(R.string.last_name),
            errorMessage = errorMessage
        )
        Button (
            modifier = Modifier
                .width(200.dp)
                .padding(top = 20.dp),
            onClick = {
                if (firstName.isNotEmpty() && lastName.isNotEmpty()) {
                    updateProfileInfo(firstName, lastName, imageUri)
                    navigateToAddPhoneNumberScreen()
                } else if (firstName.isEmpty() || lastName.isEmpty()) {
                    errorMessage = context.getString(R.string.first_or_last_name_empty)
                } else if (InputValidator.areNamesValid(firstName, lastName)) {
                    errorMessage = context.getString(R.string.first_or_last_name_invalid)
                }
            }
        ) {
            Text(text = stringResource(R.string.set))
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun UpdateProfileScreenPreview() {
    BankTheme {
        UpdateProfileScreen({}, {}) {str1, str2, uri ->}
    }
}