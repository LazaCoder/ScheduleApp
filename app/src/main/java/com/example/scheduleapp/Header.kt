package com.example.scheduleapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController  // Updated type import

@Composable
fun Header(navController: NavHostController, isLoggedIn: Boolean) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFEFF3F8)) // Light background color
            .padding(start = 30.dp, end = 30.dp, top = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {


        if(isLoggedIn) {
            // Left Image (Profile)
            Image(
                painter = painterResource(id = R.drawable.user_icon), // Replace with your image resource
                contentDescription = "Profile Image",
                modifier = Modifier.size(32.dp)
            )
        }

        // Right Image (Settings) with logout functionality.
        // Only display if the user is logged in.
        if (isLoggedIn) {
            Image(
                painter = painterResource(id = R.drawable.logout), // Replace with your image resource
                contentDescription = "Logout Image",
                modifier = Modifier
                    .size(32.dp)
                    .clickable {
                        // When clicked, navigate to "home" (the login page) and clear the back stack.
                        navController.navigate("home") {
                            popUpTo(navController.graph.startDestinationId) { inclusive = true }
                        }
                    }
            )
        }
    }
}
