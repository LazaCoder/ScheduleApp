package com.example.scheduleapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
// import androidx.compose.ui.text.font.FontFamily // Uncomment if you have Poppins defined
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.geometry.Offset

@Composable
fun StudentAttendancePage() {
    Surface(modifier = Modifier.fillMaxSize(), color = Color(0xFFEFF3F8)) {
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

            // List of students with a unique ID (using an Int as id)
            val students = listOf(
                Pair("Domagoj Tomić", 0),
                Pair("Domagoj Tomić", 1),
                Pair("Domagoj Tomić", 2),
                Pair("Domagoj Tomić", 3),
                Pair("Domagoj Tomić", 4),
                Pair("Domagoj Tomić", 5),
                Pair("Domagoj Tomić", 6)
            )

            // Centralized state: Map of student id to their attendance status
            val attendanceState = remember {
                mutableStateMapOf<Int, Boolean>().apply {
                    students.forEach { student ->
                        putIfAbsent(student.second, false)
                    }
                }
            }

            Box(modifier = Modifier.weight(1f)) {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(students, key = { it.second }) { student ->
                        StudentCard(
                            name = student.first,
                            isPresent = attendanceState[student.second] ?: false,
                            onCheckedChange = { newValue ->
                                attendanceState[student.second] = newValue
                            }
                        )
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
                    contentDescription = "QR Code",
                )
            }

            // "Having issues?" Section
            Spacer(modifier = Modifier.height(48.dp))
            Text(
                text = "Having issues?",
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    // Uncomment if you have defined Poppins
                    // fontFamily = Poppins,
                    color = Color.Black,
                    textDecoration = TextDecoration.Underline
                ),
            )

            Spacer(modifier = Modifier.height(45.dp))
        }
    }
}

@Composable
fun StudentCard(
    name: String,
    isPresent: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    // Change background and text color based on checked state
    val backgroundColor = if (isPresent) Color(0xFF274C77) else Color(0xFFE7ECEF)
    val textColor = if (isPresent) Color.White else Color.Black

    // The entire card is clickable to toggle the checkbox state.
    Box(
        modifier = Modifier
            .shadow(elevation = 3.dp)
            .fillMaxWidth()
            .height(65.dp)
            .clickable { onCheckedChange(!isPresent) }  // Toggle state on card tap
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

                    // , fontFamily = Poppins  // Uncomment if defined
                )
            )
            Checkbox(
                checked = isPresent,
                onCheckedChange = onCheckedChange,
                colors = CheckboxDefaults.colors(
                    checkedColor = Color.White,
                    uncheckedColor = Color.White,
                    checkmarkColor = Color.Black
                )
            )
        }
    }
}
