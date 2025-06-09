package com.mobilemoney.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class AddMoneyOption(
    val title: String,
    val description: String,
    val icon: ImageVector,
    val buttonText: String,
    val iconColor: Color,
    val onClick: () -> Unit = {}
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddMoneyScreen(
    onBackClick: () -> Unit,
    onReceiveFromAbroadClick: () -> Unit
) {
    val addMoneyOptions = listOf(
        AddMoneyOption(
            title = "Transfer from bank",
            description = "Move funds from your bank to M-PESA instantly.",
            icon = Icons.Default.Send,
            buttonText = "LEARN HOW",
            iconColor = Color(0xFF4CAF50)
        ),
        AddMoneyOption(
            title = "Deposit at Agent",
            description = "Deposit cash at any M-PESA Agent near you.",
            icon = Icons.Default.AddCircle,
            buttonText = "FIND AN AGENT",
            iconColor = Color(0xFF4CAF50)
        ),
        AddMoneyOption(
            title = "Ask a friend",
            description = "Request money from a friend with just a tap.",
            icon = Icons.Default.Person,
            buttonText = "REQUEST MONEY",
            iconColor = Color(0xFF4CAF50)
        ),
        AddMoneyOption(
            title = "Receive from abroad",
            description = "Receive international money transfers easily.",
            icon = Icons.Default.Add,
            buttonText = "LEARN HOW",
            iconColor = Color(0xFF4CAF50),
            onClick = onReceiveFromAbroadClick
        )
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
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Icon and Title
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Surface(
                    modifier = Modifier.size(64.dp),
                    shape = CircleShape,
                    color = MaterialTheme.colorScheme.primary
                ) {
                    Icon(
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = null,
                        modifier = Modifier.padding(16.dp),
                        tint = Color.White
                    )
                }
            }

            Text(
                text = "ADD MONEY",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 16.dp)
            )

            Text(
                text = "How you can fund your M-PESA account:",
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            // Options List
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(addMoneyOptions) { option ->
                    AddMoneyOptionCard(option = option)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddMoneyOptionCard(option: AddMoneyOption) {
    Card(
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
            // Icon
            Surface(
                modifier = Modifier.size(40.dp),
                shape = CircleShape,
                color = option.iconColor.copy(alpha = 0.1f)
            ) {
                Icon(
                    imageVector = option.icon,
                    contentDescription = null,
                    modifier = Modifier.padding(8.dp),
                    tint = option.iconColor
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            // Content
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = option.title,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    text = option.description,
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }

            // Button
            Button(
                onClick = option.onClick,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF4CAF50)
                ),
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier.height(32.dp)
            ) {
                Text(
                    text = option.buttonText,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}
@Composable
@Preview(showBackground = true, name = "Add Money Screen Preview")
fun DebugAddMoneyScreenPreview() {
    MaterialTheme {
        AddMoneyScreen(
            onBackClick = { /* Debug back click */ },
            onReceiveFromAbroadClick = { /* Debug receive abroad click */ }
        )
    }
}