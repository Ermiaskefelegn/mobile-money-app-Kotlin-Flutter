package et.learn.safaricom_task

import com.mobilemoney.ui.screens.HomeScreen
import et.learn.safaricom_task.ui.theme.MobileMoneyTheme
import et.learn.safaricom_task.ui.theme.ThemeManager


import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

@RunWith(AndroidJUnit4::class)
class HomeScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun homeScreen_displaysAllMainElements() {
        var addMoneyClicked = false
        var sendToBankClicked = false
        var recentTransfersClicked = false

        composeTestRule.setContent {
            MobileMoneyTheme {
                HomeScreen(
                    onAddMoneyClick = { addMoneyClicked = true },
                    onSendToBankClick = { sendToBankClicked = true },
                    onRecentTransfersClick = { recentTransfersClicked = true },
                    themeManager = mock(ThemeManager::class.java)
                )
            }
        }

        // Verify main elements are displayed
        composeTestRule.onNodeWithText("Mobile Money").assertIsDisplayed()
        composeTestRule.onNodeWithText("Current Balance").assertIsDisplayed()
        composeTestRule.onNodeWithText("3,235.18 BIRR").assertIsDisplayed()
        composeTestRule.onNodeWithText("Add Money").assertIsDisplayed()
        composeTestRule.onNodeWithText("Send to Bank").assertIsDisplayed()
        composeTestRule.onNodeWithText("Recent Transfers").assertIsDisplayed()
    }

    @Test
    fun homeScreen_addMoneyCard_clickable() {
        var addMoneyClicked = false

        composeTestRule.setContent {
            MobileMoneyTheme {
                HomeScreen(
                    onAddMoneyClick = { addMoneyClicked = true },
                    onSendToBankClick = { },
                    onRecentTransfersClick = { },
                    themeManager = mock(ThemeManager::class.java)
                )
            }
        }

        composeTestRule.onNodeWithText("Add Money").performClick()
        assert(addMoneyClicked)
    }

    @Test
    fun homeScreen_sendToBankCard_clickable() {
        var sendToBankClicked = false

        composeTestRule.setContent {
            MobileMoneyTheme {
                HomeScreen(
                    onAddMoneyClick = { },
                    onSendToBankClick = { sendToBankClicked = true },
                    onRecentTransfersClick = { },
                    themeManager = mock(ThemeManager::class.java)
                )
            }
        }

        composeTestRule.onNodeWithText("Send to Bank").performClick()
        assert(sendToBankClicked)
    }

    @Test
    fun homeScreen_recentTransfersCard_clickable() {
        var recentTransfersClicked = false

        composeTestRule.setContent {
            MobileMoneyTheme {
                HomeScreen(
                    onAddMoneyClick = { },
                    onSendToBankClick = { },
                    onRecentTransfersClick = { recentTransfersClicked = true },
                    themeManager = mock(ThemeManager::class.java)
                )
            }
        }

        composeTestRule.onNodeWithText("Recent Transfers").performClick()
        assert(recentTransfersClicked)
    }
}
