package com.example.scheduleapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun StudentAttendancePage() {
    Surface(modifier = Modifier.fillMaxSize(), color = Color.Transparent) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            // Title Section
            Text(
                text = "Ugradbeni računalni sustavi (250)",
                style = TextStyle(
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                ),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Subtitle Section
            Text(
                text = "January 14th",
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Black
                )
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Student Cards Section
            val students = listOf(
                Pair("Domagoj Tomić", 0),
                Pair("Domagoj Tomić", 1),
                Pair("Domagoj Tomić", 2),
                Pair("Domagoj Tomić", 3),
                Pair("Domagoj Tomić", 4),
                Pair("Domagoj Tomić", 5),
                Pair("Domagoj Tomić", 6),
            )

            Box(modifier = Modifier.weight(1f)) {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(students, key = { it.second }) { student ->
                        StudentCard(name = student.first)
                    }
                }
            }

            Spacer(modifier = Modifier.height(48.dp))

            // QR Code Section
            FloatingActionButton(
                onClick = { /* Handle scan */ },
                modifier = Modifier.size(72.dp),
                shape = CircleShape,
            ) {
                Image(
                    modifier = Modifier.fillMaxSize(0.65f),
                    painter = painterResource(id = R.drawable.qr_code),
                    contentDescription = "",
                )
            }

            // "Having issues?" Section
            Spacer(modifier = Modifier.height(48.dp))
            Text(
                text = "Having issues?",
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    fontFamily = Poppins,
                    color = Color.Black,
                    textDecoration = TextDecoration.Underline
                ),
            )

            Spacer(modifier = Modifier.height(45.dp))
        }
    }
}

@Composable
fun StudentCard(name: String) {
    val backgroundColor = Color(0xFF274C77)
    val textColor = Color.White
    var isPresent by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(65.dp)
            .background(color = backgroundColor, shape = RectangleShape)
            .padding(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = name,
                style = TextStyle(
                    fontSize = 16.sp,
                    color = textColor,
                    fontFamily = Poppins
                )
            )
            Checkbox(
                checked = isPresent,
                onCheckedChange = { isPresent = it },
                colors = CheckboxDefaults.colors(
                    checkedColor = Color.White,
                    uncheckedColor = Color.White,
                    checkmarkColor = Color.Black
                )
            )
        }
    }
}