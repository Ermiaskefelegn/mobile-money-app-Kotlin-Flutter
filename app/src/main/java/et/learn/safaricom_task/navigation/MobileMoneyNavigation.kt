package com.mobilemoney.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mobilemoney.ui.screens.AddMoneyScreen
import com.mobilemoney.ui.screens.BankFormScreen
import com.mobilemoney.ui.screens.ConfirmationScreen
import com.mobilemoney.ui.screens.HomeScreen
import com.mobilemoney.ui.screens.ReceiveFromAbroadScreen
import com.mobilemoney.ui.screens.RecentTransfersScreen
import com.mobilemoney.ui.screens.TransferDetailsScreen
import com.mobilemoney.viewmodel.BankTransferViewModel
import com.mobilemoney.viewmodel.RecentTransfersViewModel
import et.learn.safaricom_task.ui.theme.ThemeManager

@Composable
fun MobileMoneyNavigation(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    themeManager: ThemeManager,
) {
    NavHost(
        navController = navController,
        startDestination = "home",
        modifier = modifier
    ) {
        composable("home") {
            HomeScreen(
                onAddMoneyClick = { navController.navigate("add_money") },
                onSendToBankClick = { navController.navigate("bank_form") },
                onRecentTransfersClick = { navController.navigate("recent_transfers") },themeManager = themeManager
            )
        }

        composable("add_money") {
            AddMoneyScreen(
                onBackClick = { navController.popBackStack() },
                onReceiveFromAbroadClick = { navController.navigate("receive_from_abroad") }
            )
        }

        composable("receive_from_abroad") {
            ReceiveFromAbroadScreen(
                onBackClick = { navController.popBackStack() }
            )
        }

        composable("bank_form") {
            val viewModel: BankTransferViewModel = hiltViewModel()
            BankFormScreen(
                viewModel = viewModel,
                onBackClick = { navController.popBackStack() },
                onContinueClick = { transferData ->
                    navController.navigate("confirmation")
                }
            )
        }

        composable("confirmation") {
            val viewModel: BankTransferViewModel = hiltViewModel()
            ConfirmationScreen(
                viewModel = viewModel,
                onBackClick = { navController.popBackStack("home", false) },
                onDoneClick = { navController.popBackStack("home", false) }
            )
        }

        composable("recent_transfers") {
            val viewModel: RecentTransfersViewModel = hiltViewModel()
            RecentTransfersScreen(
                viewModel = viewModel,
                onBackClick = { navController.popBackStack() },
                onTransferClick = { transferId ->
                    navController.navigate("transfer_details/$transferId")
                }
            )
        }

        composable("transfer_details/{transferId}") { backStackEntry ->
            val transferId = backStackEntry.arguments?.getString("transferId") ?: ""
            val viewModel: RecentTransfersViewModel = hiltViewModel()
            TransferDetailsScreen(
                transferId = transferId,
                viewModel = viewModel,
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}
