package com.example.scheduleapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.scheduleapp.ui.theme.ScheduleAppTheme
import com.google.zxing.qrcode.encoder.QRCode


val Poppins = FontFamily(
    Font(R.font.poppins_regular), // Regular
    Font(R.font.poppins_bold, weight = FontWeight.Bold) // Bold
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ScheduleAppTheme {

                Column(
                 modifier = Modifier.background(color = Color(0xFFEFF3F8))


                ) {
                    Header()
                    Spacer(modifier = Modifier.height(50.dp))

                    val navController = rememberNavController()
                    ScheduleApp()


                }

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