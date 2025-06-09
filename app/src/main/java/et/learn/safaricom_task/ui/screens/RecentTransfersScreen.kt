package com.mobilemoney.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mobilemoney.model.Transfer
import com.mobilemoney.model.TransferStatus
import com.mobilemoney.viewmodel.RecentTransfersViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecentTransfersScreen(
    viewModel: RecentTransfersViewModel,
    onBackClick: () -> Unit,
    onTransferClick: (String) -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        TopAppBar(
            modifier = Modifier.height(80.dp),
            expandedHeight = 10.dp,
            title = {
                Text(
                    text = "Recent Transfers",
                    fontWeight = FontWeight.Bold
                )
            },
            navigationIcon = {
                IconButton(onClick = onBackClick) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                }
            }
        )


        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(uiState.transfers) { transfer ->
                TransferItem(
                    transfer = transfer,
                    onClick = { onTransferClick(transfer.id) }
                )
            }

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransferItem(
    transfer: Transfer,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = transfer.recipientName,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    text = "${transfer.amount} Birr",
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(top = 2.dp)
                )
                Text(
                    text = transfer.dateTime,
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(top = 2.dp)
                )
            }

            Surface(
                shape = RoundedCornerShape(16.dp),
                color = when (transfer.status) {
                    TransferStatus.SUCCESS -> Color(0xFF4CAF50).copy(alpha = 0.1f)
                    TransferStatus.PENDING -> Color(0xFFFF9800).copy(alpha = 0.1f)
                    TransferStatus.FAILED -> Color(0xFFF44336).copy(alpha = 0.1f)
                }
            ) {
                Text(
                    text = transfer.status.name,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium,
                    color = when (transfer.status) {
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
