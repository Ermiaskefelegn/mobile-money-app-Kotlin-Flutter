package et.learn.safaricom_task

import et.learn.safaricom_task.ui.theme.MobileMoneyTheme
import et.learn.safaricom_task.ui.theme.ThemeManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.mobilemoney.navigation.MobileMoneyNavigation
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var themeManager: ThemeManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MobileMoneyTheme(themeManager = themeManager) {
                MobileMoneyApp(themeManager = themeManager)
            }
        }
    }
}

@Composable
fun MobileMoneyApp(themeManager: ThemeManager) {
    val navController = rememberNavController()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Scaffold { paddingValues ->
            MobileMoneyNavigation(
                navController = navController,
                themeManager = themeManager,
                modifier = Modifier.padding(paddingValues)
            )
        }
    }
}
