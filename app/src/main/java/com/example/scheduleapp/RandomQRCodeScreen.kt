package com.example.scheduleapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.util.UUID
import android.graphics.Bitmap
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.sp
import com.google.zxing.BarcodeFormat
import com.google.zxing.EncodeHintType
import com.google.zxing.MultiFormatWriter
import com.google.zxing.common.BitMatrix
import java.util.EnumMap

@Composable
fun RandomQRCodeScreen() {
    var randomString by remember { mutableStateOf(UUID.randomUUID().toString()) }

    // Re-generate the QR code bitmap whenever randomString changes
    val qrBitmap = remember(randomString) {
        generateQRCode(randomString)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Your unique QR code is below.",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = Poppins,
            textAlign = TextAlign.Center,
        )
        Spacer(modifier = Modifier.height(16.dp))

        Image(
            bitmap = qrBitmap.asImageBitmap(),
            contentDescription = "Random QR Code"
        )
        Spacer(modifier = Modifier.height(32.dp))

        Text(text = "Did something go wrong?",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = Poppins,



            )

        Spacer(modifier = Modifier.height(48.dp))

        Button(onClick = {


            // Generate a new random UUID string
            randomString = UUID.randomUUID().toString()
        },
            modifier = Modifier.size(width = 170.dp , height = 65.dp ).shadow(
                elevation = 12.dp,
            ),
            shape = RectangleShape
            ) {
            Text(text = "Generate Again",
                fontWeight = FontWeight.Bold,
                fontFamily = Poppins,



                )
        }
        Spacer(modifier = Modifier.height(130.dp))

        Text(text = "Having issues?",
            fontFamily = Poppins,
            textDecoration = TextDecoration.Underline,

            )
    }
}


fun generateQRCode(content: String, size: Int = 512): Bitmap {
    val hints = EnumMap<EncodeHintType, Any>(EncodeHintType::class.java)
    hints[EncodeHintType.CHARACTER_SET] = "UTF-8"
    // Optional: reduce default white margin
    hints[EncodeHintType.MARGIN] = 1

    val bitMatrix: BitMatrix = MultiFormatWriter().encode(
        content,
        BarcodeFormat.QR_CODE,
        size,
        size,
        hints
    )

    val pixels = IntArray(size * size)
    for (y in 0 until size) {
        for (x in 0 until size) {
            pixels[y * size + x] = if (bitMatrix[x, y]) {
                android.graphics.Color.BLACK
            } else {
                android.graphics.Color.WHITE
            }
        }
    }

    val bitmap = Bitmap.createBitmap(size, size, Bitmap.Config.RGB_565)
    bitmap.setPixels(pixels, 0, size, 0, 0, size, size)
    return bitmap
}