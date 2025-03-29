package com.example.bank.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.bank.ui.screens.phone.AddPhoneNumberScreenWithViewModel
import com.example.bank.ui.screens.question.SecurityQuestionScreenWithViewModel
import com.example.bank.ui.screens.card.ChooseCardColorScreenWithViewModel
import com.example.bank.ui.screens.login.LogInScreenWithViewModel
import com.example.bank.ui.screens.pincode.ConfirmPinCodeScreenWithViewModel
import com.example.bank.ui.screens.main.MainScreenWithViewModel
import com.example.bank.ui.screens.pincode.SetPinCodeScreenWithViewModel
import com.example.bank.ui.screens.signup.SignUpScreenWithViewModel
import com.example.bank.ui.screens.StartScreen
import com.example.bank.ui.screens.profile.UpdateProfileScreenWithViewModel
import kotlinx.serialization.Serializable

@Serializable
object StartScreen
@Serializable
object SignUp
@Serializable
object LogIn
@Serializable
object UpdateProfile
@Serializable
object AddPhoneNumber
@Serializable
object SecurityQuestion
@Serializable
object SetPinCode
@Serializable
object ConfirmPinCode
@Serializable
object CardColor
@Serializable
object MainScreen

@Composable
fun AppNavigation(
    navController: NavHostController,
    viewModel: AppNavigationViewModel = hiltViewModel()
) {
    val navigationState = viewModel.navigationState.collectAsState().value

    when (navigationState) {
        is NavigationState.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
        is NavigationState.Ready -> {
            NavHost(navController = navController, startDestination = navigationState.startDestination) {
                composable<StartScreen> { StartScreen {navController.navigate(SignUp)} }
                composable<SignUp> {
                    SignUpScreenWithViewModel(
                        navigateToLogInScreen = { navController.navigate(LogIn) {
                            popUpTo(0) { inclusive = true }
                        } },
                        navigateToUpdateProfileScreen = { navController.navigate(UpdateProfile) }
                    )
                }
                composable<LogIn> {
                    LogInScreenWithViewModel(
                        navigateToUpdateProfileScreen = { navController.navigate(UpdateProfile) },
                        navigateToSignUpScreen = { navController.navigate(SignUp) {
                            popUpTo(0) { inclusive = true }
                        } },
                        navigateToPhoneNumberScreen = { navController.navigate(AddPhoneNumber) },
                        navigateToPinCodeScreen = { navController.navigate(SetPinCode) },
                        navigateToSecurityQuestionScreen = { navController.navigate(SecurityQuestion) },
                        navigateToCardColorScreen = { navController.navigate(CardColor) },
                        navigateToMainScreen = { navController.navigate(MainScreen) {
                            popUpTo(0) { inclusive = true }
                        } }
                    )
                }
                composable<UpdateProfile> {
                    UpdateProfileScreenWithViewModel(
                        navigateBack = { navController.navigateUp() },
                        navigateToAddPhoneNumberScreen = { navController.navigate(AddPhoneNumber) }
                    )
                }
                composable<AddPhoneNumber> {
                    AddPhoneNumberScreenWithViewModel(
                        navigateBackToUpdateProfileScreen = { navController.navigateUp() },
                        navigateToSecurityQuestionScreen = { navController.navigate(SecurityQuestion) }
                    )
                }
                composable<SecurityQuestion> {
                    SecurityQuestionScreenWithViewModel(
                        navigateBackToPhoneNumberScreen = { navController.navigateUp() },
                        navigateToSetPinCodeScreen = { navController.navigate(SetPinCode) }
                    )
                }
                composable<SetPinCode> {
                    SetPinCodeScreenWithViewModel(
                        navigateBackToSecurityQuestionScreen = { navController.navigateUp() },
                        navigateToLogInViaPinCodeScreen = { navController.navigate(ConfirmPinCode) }
                    )
                }
                composable<ConfirmPinCode> {
                    ConfirmPinCodeScreenWithViewModel (
                        navigateBackToSetPinCodeScreen = { navController.navigateUp() },
                        navigateToChooseCardColorScreen = { navController.navigate(CardColor) }
                    )
                }
                composable<CardColor> {
                    ChooseCardColorScreenWithViewModel(
                        navigateBackToConfirmPinCodeScreen = { navController.navigateUp() },
                        navigateToMainScreen = { navController.navigate(MainScreen) {
                            popUpTo(0) { inclusive = true }
                        } }
                    )
                }
                composable<MainScreen> {
                    MainScreenWithViewModel(
                        navigateToLogInScreen = { navController.navigate(LogIn) {
                            popUpTo(0) { inclusive = true }
                        } },
                        navigateToUpdateProfileScreen = { navController.navigate(UpdateProfile) {
                            popUpTo(0) { inclusive = true }
                        } }
                    )
                }
            }
        }
    }
}