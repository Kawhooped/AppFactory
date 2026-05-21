package com.appfactory.settings.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SettingsScreen(
    onBackClick: () -> Unit = {}
) {
    var notificationsEnabled by remember { mutableStateOf(true) }
    var darkModeEnabled by remember { mutableStateOf(false) }
    var autoSyncEnabled by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        // Header
        TopAppBar(
            title = { Text("Settings") },
            navigationIcon = {
                IconButton(onClick = onBackClick) {
                    Icon(
                        Icons.Default.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            }
        )

        // Account Settings Section
        SettingsSection(title = "Account") {
            SettingsItem(
                title = "Email",
                subtitle = "user@example.com",
                onClick = { }
            )
            Divider()
            SettingsItem(
                title = "Password",
                subtitle = "Change your password",
                onClick = { }
            )
            Divider()
            SettingsItem(
                title = "Profile",
                subtitle = "Edit your profile information",
                onClick = { }
            )
        }

        // Preferences Section
        SettingsSection(title = "Preferences") {
            SettingsToggle(
                title = "Push Notifications",
                subtitle = "Get notifications about important updates",
                checked = notificationsEnabled,
                onCheckedChange = { notificationsEnabled = it }
            )
            Divider()
            SettingsToggle(
                title = "Dark Mode",
                subtitle = "Use dark theme",
                checked = darkModeEnabled,
                onCheckedChange = { darkModeEnabled = it }
            )
            Divider()
            SettingsToggle(
                title = "Auto Sync",
                subtitle = "Automatically sync data",
                checked = autoSyncEnabled,
                onCheckedChange = { autoSyncEnabled = it }
            )
        }

        // App Info Section
        SettingsSection(title = "About") {
            SettingsItem(
                title = "App Version",
                subtitle = "1.0.0",
                onClick = { }
            )
            Divider()
            SettingsItem(
                title = "Privacy Policy",
                subtitle = "View our privacy policy",
                onClick = { }
            )
            Divider()
            SettingsItem(
                title = "Terms of Service",
                subtitle = "View our terms",
                onClick = { }
            )
        }

        // Logout Button
        Button(
            onClick = { },
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp)
                .height(48.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.error
            )
        ) {
            Text("Logout")
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
fun SettingsSection(
    title: String,
    content: @Composable ColumnScope.() -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp)
    ) {
        Text(
            title,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            content = content
        )
    }
}

@Composable
fun SettingsItem(
    title: String,
    subtitle: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                title,
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                subtitle,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
fun SettingsToggle(
    title: String,
    subtitle: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                title,
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                subtitle,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange
        )
    }
}
