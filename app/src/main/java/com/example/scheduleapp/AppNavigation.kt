package com.example.scheduleapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.NavHostController

@Composable
fun ScheduleApp(navController: NavHostController) {
    // Set "home" as the start destination for login.
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomePage(navController = navController)
        }
        composable(
            route = "subjectListPage/{username}",
            arguments = listOf(
                navArgument("username") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val username = backStackEntry.arguments?.getString("username") ?: ""
            SubjectListPage(username = username, navController = navController)
        }
        // New route for the Random QR Code Screen (for students)
        composable("randomQRCodeScreen") {
            RandomQRCodeScreen()
        }
        composable(
            route = "classDetail/{subjectName}/{subjectCode}/{startDate}",
            arguments = listOf(
                navArgument("subjectName") { type = NavType.StringType },
                navArgument("subjectCode") { type = NavType.StringType },
                navArgument("startDate") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val subjectName = backStackEntry.arguments?.getString("subjectName") ?: ""
            val subjectCode = backStackEntry.arguments?.getString("subjectCode") ?: ""
            val startDate = backStackEntry.arguments?.getString("startDate") ?: ""
            ClassPage(subjectName = subjectName, subjectCode = subjectCode, startDate = startDate)
        }
    }
}
