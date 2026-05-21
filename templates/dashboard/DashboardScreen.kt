package com.appfactory.dashboard.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

data class DashboardCard(
    val title: String,
    val value: String,
    val subtitle: String,
    val color: androidx.compose.ui.graphics.Color
)

@Composable
fun DashboardScreen(
    userName: String = "User",
    onSettingsClick: () -> Unit = {},
    onProfileClick: () -> Unit = {}
) {
    val cards = listOf(
        DashboardCard(
            title = "Total Revenue",
            value = "$12,450",
            subtitle = "This month",
            color = MaterialTheme.colorScheme.primary
        ),
        DashboardCard(
            title = "Active Users",
            value = "2,341",
            subtitle = "Online now",
            color = MaterialTheme.colorScheme.secondary
        ),
        DashboardCard(
            title = "Conversion Rate",
            value = "4.2%",
            subtitle = "Up 0.5% from last month",
            color = MaterialTheme.colorScheme.tertiary
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        // Header
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.Start,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    "Welcome back, $userName!",
                    style = MaterialTheme.typography.headlineMedium
                )
                Text(
                    "Here's what's happening today",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        // Stats Cards
        Column(
            modifier = Modifier.padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            cards.forEach { card ->
                StatCard(card)
            }
        }

        // Recent Activity Section
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp)
        ) {
            Text(
                "Recent Activity",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            repeat(3) { index ->
                ActivityItem(
                    title = "Activity ${index + 1}",
                    description = "Something interesting happened",
                    time = "2 hours ago"
                )
            }
        }

        // Bottom Navigation Spacer
        Spacer(modifier = Modifier.height(80.dp))
    }
}

@Composable
fun StatCard(card: DashboardCard) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                card.title,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                card.value,
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(vertical = 8.dp)
            )
            Text(
                card.subtitle,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
fun ActivityItem(
    title: String,
    description: String,
    time: String
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                title,
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                time,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
        Text(
            description,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(top = 4.dp)
        )
        Divider(modifier = Modifier.padding(top = 12.dp))
    }
}
