package com.appfactory.auth.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit,
    onSignUpClick: () -> Unit
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Logo/Title
        Text(
            "Welcome Back",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        // Email Field
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            singleLine = true,
            enabled = !isLoading
        )

        // Password Field
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp),
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            enabled = !isLoading
        )

        // Error Message
        if (errorMessage.isNotEmpty()) {
            Text(
                errorMessage,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }

        // Login Button
        Button(
            onClick = {
                isLoading = true
                // API call would go here
                // For now, just simulate success
                if (email.isNotEmpty() && password.isNotEmpty()) {
                    onLoginSuccess()
                } else {
                    errorMessage = "Please fill in all fields"
                    isLoading = false
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            enabled = !isLoading
        ) {
            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(24.dp),
                    color = MaterialTheme.colorScheme.onPrimary,
                    strokeWidth = 2.dp
                )
            } else {
                Text("Login")
            }
        }

        // Sign Up Link
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text("Don't have an account? ")
            TextButton(onClick = onSignUpClick) {
                Text("Sign Up")
            }
        }
    }
}

@Composable
fun SignUpScreen(
    onSignUpSuccess: () -> Unit,
    onBackClick: () -> Unit
) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "Create Account",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Full Name") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            visualTransformation = PasswordVisualTransformation()
        )

        OutlinedTextField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            label = { Text("Confirm Password") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp),
            visualTransformation = PasswordVisualTransformation()
        )

        if (errorMessage.isNotEmpty()) {
            Text(
                errorMessage,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }

        Button(
            onClick = {
                if (password != confirmPassword) {
                    errorMessage = "Passwords don't match"
                } else if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
                    errorMessage = "Please fill in all fields"
                } else {
                    isLoading = true
                    onSignUpSuccess()
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            enabled = !isLoading
        ) {
            Text("Create Account")
        }

        TextButton(
            onClick = onBackClick,
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("Back to Login")
        }
    }
}
