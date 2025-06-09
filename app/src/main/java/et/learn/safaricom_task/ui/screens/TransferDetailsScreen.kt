package com.mobilemoney.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mobilemoney.model.TransferStatus
import com.mobilemoney.viewmodel.RecentTransfersViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransferDetailsScreen(
    transferId: String,
    viewModel: RecentTransfersViewModel,
    onBackClick: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    val transfer = uiState.transfers.find { it.id == transferId }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        TopAppBar(

            modifier = Modifier.height(80.dp),
            expandedHeight = 10.dp,
            title = {
                Text(
                    text = "Transfer Details",
                    fontWeight = FontWeight.Bold
                )
            },
            navigationIcon = {
                IconButton(onClick = onBackClick) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                }
            }
        )

        transfer?.let { t ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        DetailRow("Recipient", t.recipientName)
                        DetailRow("Amount", "${t.amount} Birr")
                        DetailRow("Date & Time", t.dateTime)
                        DetailRow("Transaction ID", t.id)

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "Status",
                                fontSize = 14.sp,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                            Surface(
                                shape = RoundedCornerShape(16.dp),
                                color = when (t.status) {
                                    TransferStatus.SUCCESS -> Color(0xFF4CAF50).copy(alpha = 0.1f)
                                    TransferStatus.PENDING -> Color(0xFFFF9800).copy(alpha = 0.1f)
                                    TransferStatus.FAILED -> Color(0xFFF44336).copy(alpha = 0.1f)
                                }
                            ) {
                                Text(
                                    text = t.status.name,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Medium,
                                    color = when (t.status) {
                                        TransferStatus.SUCCESS -> Color(0xFF4CAF50)
                                        TransferStatus.PENDING -> Color(0xFFFF9800)
                                        TransferStatus.FAILED -> Color(0xFFF44336)
                                    },
                                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun DetailRow(label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Text(
            text = value,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium
        )
    }
}
