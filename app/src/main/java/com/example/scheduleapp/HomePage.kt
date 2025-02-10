package com.example.scheduleapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// ... (imports and other code remain unchanged)

@Composable
fun HomePage(navController: NavHostController) {
    // UI state variables
    var showLoginDialog by remember { mutableStateOf(false) }
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()

    // Retrofit setup (remember to update the base URL)
    val retrofit = remember {
        Retrofit.Builder()
            .baseUrl("https://ams-sz8c.onrender.com/") // Replace with your actual API base URL
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val authApi = remember { retrofit.create(AuthApi::class.java) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
    ) {
        // Welcome Text
        Text(
            text = "Log in for better\nexperience...",
            style = TextStyle(
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = Poppins, // Ensure your font is defined/imported
                shadow = Shadow(
                    color = Color.Black.copy(alpha = 0.12f),
                    blurRadius = 12f
                ),
                textAlign = TextAlign.Center
            ),
            modifier = Modifier
                .padding(bottom = 16.dp)
                .align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Log in Button that opens the login form popup
        Button(
            onClick = { showLoginDialog = true },
            shape = MaterialTheme.shapes.medium,
            modifier = Modifier
                .size(width = 170.dp, height = 65.dp)
                .shadow(elevation = 12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00008B))
        ) {
            Text(
                text = "Log in",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        // QR Code Image
        Image(
            painter = painterResource(id = R.drawable.qr_code),
            contentDescription = "QR Code",
            modifier = Modifier
                .size(245.dp)
                .offset(y = 80.dp)
        )

        // Login dialog
        if (showLoginDialog) {
            AlertDialog(
                onDismissRequest = { showLoginDialog = false },
                title = { Text(text = "Login") },
                text = {
                    Column {
                        OutlinedTextField(
                            value = username,
                            onValueChange = { username = it },
                            label = { Text("Username") },
                            modifier = Modifier.fillMaxWidth(),
                            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        OutlinedTextField(
                            value = password,
                            onValueChange = { password = it },
                            label = { Text("Password") },
                            modifier = Modifier.fillMaxWidth(),
                            visualTransformation = PasswordVisualTransformation(),
                            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done)
                        )
                        if (errorMessage.isNotEmpty()) {
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(text = errorMessage, color = Color.Red)
                        }
                    }
                },
                confirmButton = {
                    Button(
                        onClick = {
                            // Launch a coroutine to call the login API
                            coroutineScope.launch {
                                try {
                                    // Call the API
                                    val loginResponse = authApi.login(LoginRequest(username, password))
                                    // Clear error, dismiss the dialog, and navigate based on role
                                    errorMessage = ""
                                    showLoginDialog = false

                                    when (loginResponse.role) {
                                        2 -> { // Professor
                                            navController.navigate("subjectListPage/$username")
                                        }
                                        1 -> { // Student
                                            navController.navigate("randomQRCodeScreen")
                                        }
                                        else -> {
                                            errorMessage = "Unknown user role"
                                        }
                                    }
                                } catch (e: Exception) {
                                    errorMessage = "Login failed: ${e.message}"
                                }
                            }
                        }
                    ) {
                        Text("Login")
                    }
                },
                dismissButton = {
                    Button(onClick = { showLoginDialog = false }) {
                        Text("Cancel")
                    }
                }
            )
        }
    }
}
