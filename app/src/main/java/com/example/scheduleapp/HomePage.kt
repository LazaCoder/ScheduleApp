package com.example.scheduleapp

import android.graphics.drawable.shapes.Shape
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HomePage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,

    ) {
        // Welcome Text
        Text(
            text = "Log in for better\nexperience...",
            style = TextStyle(
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = Poppins,
                shadow = Shadow(
                    color = Color.Black.copy(alpha = 0.12f),
                    blurRadius = 12f
                ),
                textAlign = TextAlign.Center
            ),
            modifier = Modifier.padding(bottom = 16.dp).align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Button
        Button(
            onClick = { /*TODO: Add action*/ },
            shape = RectangleShape,
            modifier = Modifier
                .size(width = 170.dp, height = 65.dp)
                .shadow(
                    elevation = 12.dp,
                ),
            colors = ButtonDefaults.buttonColors(contentColor = Color(0xFF00008B))
        ) {
            Text(
                text = "Log in",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        // QR Code Image
        Image(
            painter = painterResource(id = R.drawable.qr_code),
            contentDescription = "QR Code",
            modifier = Modifier
                .size(245.dp)
                .offset(y = 80.dp)

        )
    }
}
