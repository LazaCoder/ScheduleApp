package com.example.scheduleapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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

@Composable
fun AttendancePage() {
    Surface(modifier = Modifier.fillMaxSize(), color = Color.Transparent) { // Background color
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(0.dp))

            // Greeting Section
            Text(
                text = "Hello Sven.",
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Black,
                    fontFamily = Poppins
                ),

            )

            Spacer(modifier = Modifier.height(42.dp))

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
            val classCards = listOf(
                Triple("Today", "Ugradbeni računalni sustavi", "(250)"),
                Triple("Tomorrow", "Arhitektura digitalnih računala", "(550)"),
                Triple("January 18th", "Operacijski sustavi", "(120)"),
                Triple("January 18th", "Operacijski sustavi", "(120)")
            )

            Box(modifier = Modifier.weight(1f),
                contentAlignment = Alignment.Center,



            ) { // Ensure LazyColumn fits within the available space
                LazyColumn(
                    modifier = Modifier.fillMaxSize().align(Alignment.Center)


                    ,
                    verticalArrangement = Arrangement.spacedBy(32.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    items(classCards) { card ->
                        ClassCard(date = card.first, subject = card.second, count = card.third)
                    }
                }
            }

            // "Having issues?" Section

            Text(
                text = "Having issues?",
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Black,

                ),
                textDecoration = TextDecoration.Underline
            )

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Composable
fun ClassCard(date: String, subject: String, count: String) {
    Box(
        modifier = Modifier
            .width(304.dp)
            .height(107.dp)
            .background(color = Color(0xFF274C77), shape = RectangleShape)
            .padding(16.dp),

    ) {
        Column {
            Text(
                text = date,
                style = TextStyle(
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    fontFamily = Poppins
                ),

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
