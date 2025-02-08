package com.example.scheduleapp

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

@Composable
fun SubjectListPage(viewModel: CourseViewModel = viewModel(), navController: NavController) {
    // Collect the courses list from the ViewModel.
    val courses by viewModel.courses.collectAsState()

    Surface(modifier = Modifier.fillMaxSize(), color = Color.Transparent) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Greeting Section
            Text(
                text = "Hello Sven.",
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Black,
                    fontFamily = Poppins
                )
            )

            Spacer(modifier = Modifier.height(30.dp))

            // Subtitle Section
            Text(
                text = "Keep track of attendance in your classes easily.",
                style = TextStyle(
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = Poppins,
                    color = Color.Black
                ),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Class Cards Section
            Box(
                modifier = Modifier.weight(1f),
                contentAlignment = Alignment.Center,
            ) {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(32.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    items(courses) { course ->
                        // Format the date (for example, take the first 10 characters)
                        val dateText = course.startDate.take(10)
                        // When a card is clicked, navigate to the detail screen passing the data.
                        ClassCard(
                            date = dateText,
                            subject = course.name,
                            count = "(${course.code})",
                            onClick = {
                                // Construct a route with arguments. Be sure to URL-encode if needed.
                                navController.navigate("classDetail/${course.name}/${course.code}/$dateText")
                            }
                        )
                    }
                }
            }

            // "Having issues?" Section
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "Having issues?",
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Black,
                    fontFamily = Poppins
                ),
                textDecoration = TextDecoration.Underline,
            )
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

@Composable
fun ClassCard(
    date: String,
    subject: String,
    count: String,
    onClick: () -> Unit  // New parameter for click handling
) {
    Box(
        modifier = Modifier
            .width(304.dp)
            .height(107.dp)
            .background(color = Color(0xFF274C77), shape = RectangleShape)
            .clickable { onClick() }  // Wrap the card with clickable
            .padding(16.dp)
    ) {
        Column {
            Text(
                text = date,
                style = TextStyle(
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    fontFamily = Poppins
                )
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "$subject $count",
                style = TextStyle(
                    fontSize = 16.sp,
                    color = Color.White,
                    fontFamily = Poppins
                ),
                textAlign = TextAlign.Center
            )
        }
    }
}