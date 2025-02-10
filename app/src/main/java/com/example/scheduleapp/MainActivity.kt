package com.example.scheduleapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.scheduleapp.ui.theme.ScheduleAppTheme

val Poppins = FontFamily(
    Font(R.font.poppins_regular), // Regular
    Font(R.font.poppins_bold, weight = FontWeight.Bold) // Bold
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ScheduleAppTheme {
                // Declare a single shared NavHostController.
                val navController = rememberNavController()

                // Observe the current back stack entry to determine if the user is logged in.
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route
                val isLoggedIn = currentRoute != "home"

                Column(modifier = Modifier.background(color = Color(0xFFEFF3F8))) {
                    // Pass the navController and isLoggedIn to Header.
                    Header(navController = navController, isLoggedIn = isLoggedIn)
                    Spacer(modifier = Modifier.height(50.dp))
                    // Pass the same navController to the navigation host.
                  ScheduleApp(navController = navController)

                  //  StudentAttendancePage()
                }
            }
        }
    }
}



