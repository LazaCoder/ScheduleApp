
package com.example.scheduleapp
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp


@Composable
fun Header() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFEFF3F8)) // Light background color
            .padding(start = 30.dp, end = 30.dp, top = 20.dp),         // .padding(horizontal = 30.dp, vertical = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Left Image
        Image(
            painter = painterResource(id = R.drawable.user_icon), // Replace with your image resource
            contentDescription = "Profile Image",
            modifier = Modifier.size(32.dp)
        )

        // Right Image
        Image(
            painter = painterResource(id = R.drawable.settings_icon), // Replace with your image resource
            contentDescription = "Settings Image",
            modifier = Modifier.size(32.dp)
        )
    }
}
