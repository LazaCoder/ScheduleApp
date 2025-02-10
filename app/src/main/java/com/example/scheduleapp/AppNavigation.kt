package com.example.scheduleapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.NavHostController

@Composable
fun ScheduleApp(navController: NavHostController) {
    // Set "home" as the start destination.
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
        // Route for the Random QR Code Screen (for students)
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
            // Pass navController so ClassPage can navigate
            ClassPage(
                subjectName = subjectName,
                subjectCode = subjectCode,
                startDate = startDate,
                navController = navController
            )
        }
        // New route for the Student Attendance Page
        composable("studentAttendancePage") {
            StudentAttendancePage()
        }
    }
}
