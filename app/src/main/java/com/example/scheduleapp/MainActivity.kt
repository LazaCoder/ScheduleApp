package com.example.scheduleapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
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
                HomePage()
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun HomePagePreview() {
    ScheduleAppTheme {
        HomePage()
    }
}