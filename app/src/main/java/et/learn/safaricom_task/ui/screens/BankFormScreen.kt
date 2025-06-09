package com.mobilemoney.ui.screens

import BankTransferData
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mobilemoney.viewmodel.BankTransferViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BankFormScreen(
    viewModel: BankTransferViewModel,
    onBackClick: () -> Unit,
    onContinueClick: (BankTransferData) -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    val banks = listOf(
        "Select bank",
        "Commercial Bank of Ethiopia",
        "Dashen Bank",
        "Bank of Abyssinia",
        "Awash Bank",
        "United Bank",
        "Nib International Bank"
    )

    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        // Top Bar
        TopAppBar(
            modifier = Modifier.height(80.dp),
            expandedHeight = 10.dp,
            title = {
                Text(
                    text = "SEND TO BANK",
                    fontWeight = FontWeight.Medium
                )
            },
            navigationIcon = {
                IconButton(onClick = onBackClick) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                }
            }
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Bank Selection
            Text(
                text = "SELECT BANK",
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontWeight = FontWeight.Medium
            )

            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded }
            ) {
                OutlinedTextField(
                    value = uiState.selectedBank,
                    onValueChange = { },
                    readOnly = true,
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .menuAnchor(),
                    colors = OutlinedTextFieldDefaults.colors()
                )

                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    banks.forEach { bank ->
                        DropdownMenuItem(
                            text = { Text(bank) },
                            onClick = {
                                if (bank != "Select bank") {
                                    viewModel.updateSelectedBank(bank)
                                }
                                expanded = false
                            }
                        )
                    }
                }
            }

            // Account Number
            Text(
                text = "BANK ACCOUNT NUMBER",
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontWeight = FontWeight.Medium
            )

            OutlinedTextField(
                value = uiState.accountNumber,
                onValueChange = viewModel::updateAccountNumber,
                placeholder = { Text("Enter Bank Account Number") },
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth(),
                isError = uiState.accountNumberError != null
            )

            uiState.accountNumberError?.let { error ->
                Text(
                    text = error,
                    color = MaterialTheme.colorScheme.error,
                    fontSize = 12.sp
                )
            }

            // Amount
            Text(
                text = "AMOUNT",
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontWeight = FontWeight.Medium
            )

            OutlinedTextField(
                value = uiState.amount,
                onValueChange = viewModel::updateAmount,
                placeholder = { Text("Birr 0") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth(),
                isError = uiState.amountError != null
            )

            uiState.amountError?.let { error ->
                Text(
                    text = error,
                    color = MaterialTheme.colorScheme.error,
                    fontSize = 12.sp
                )
            }

            Text(
                text = "BALANCE: 3,235.18 BIRR",
                fontSize = 12.sp,
                color = Color(0xFF4CAF50),
                fontWeight = FontWeight.Medium
            )

            // Description
            Text(
                text = "DESCRIPTION (OPTIONAL)",
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontWeight = FontWeight.Medium
            )

            OutlinedTextField(
                value = uiState.description,
                onValueChange = viewModel::updateDescription,
                placeholder = { Text("Enter description") },
                modifier = Modifier.fillMaxWidth(),
                maxLines = 3
            )

            Text(
                text = "${uiState.description.length}/100",
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.weight(1f))

            // Continue Button
            Button(
                onClick = {
                    if (viewModel.validateForm()) {
                        onContinueClick(
                            BankTransferData(
                                bankName = uiState.selectedBank,
                                accountNumber = uiState.accountNumber,
                                amount = uiState.amount.toDoubleOrNull() ?: 0.0,
                                description = uiState.description
                            )
                        )
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF4CAF50)
                ),
                enabled = uiState.isFormValid
            ) {
                Text(
                    text = "CONTINUE",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("→", fontSize = 16.sp)
            }
        }
    }
}
