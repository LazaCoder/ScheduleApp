package com.example.scheduleapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun ScheduleApp() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "subjectList") {
        composable("subjectList") {
            // Pass the navController to the list so it can trigger navigation.
            SubjectListPage(navController = navController)
        }
        composable(
            route = "classDetail/{subjectName}/{subjectCode}/{startDate}",
            arguments = listOf(
                navArgument("subjectName") { type = NavType.StringType },
                navArgument("subjectCode") { type = NavType.StringType },
                navArgument("startDate") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            // Retrieve the arguments
            val subjectName = backStackEntry.arguments?.getString("subjectName") ?: ""
            val subjectCode = backStackEntry.arguments?.getString("subjectCode") ?: ""
            val startDate = backStackEntry.arguments?.getString("startDate") ?: ""
            // Pass the subject data to your detailed ClassPage
            ClassPage(subjectName = subjectName, subjectCode = subjectCode, startDate = startDate)
        }
    }
}
