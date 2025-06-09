package com.mobilemoney.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReceiveFromAbroadScreen(
    onBackClick: () -> Unit
) {
    val transferServices = listOf(
        "Boss Revolution",
        "ClickSend",
        "GME",
        "G-Money Trans",
        "BNB transfer",
        "Paysend",
        "Dahabshiil",
        "BFC Bahrain",
        "NEC Remit",
        "Bin Yaala Exchange",
        "AL Mulla Exchange",
        "Lulu Exchange Company WLL",
        "Unimoni",
        "NAFEX",
        "NNEC Remit",
        "Mama Money",
        "Capital Services"
    )

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        // Top Bar
        TopAppBar(
            modifier = Modifier.height(80.dp),
            expandedHeight = 10.dp,
            title = { },
            navigationIcon = {
                IconButton(onClick = onBackClick) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                }
            }
        )

        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "RECEIVE FROM ABROAD",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Text(
                text = "Receive money from your loved ones across the world instantly via M-PESA",
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(transferServices) { service ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                    ) {
                        Text(
                            text = "• $service",
                            fontSize = 14.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
        }
    }
}
