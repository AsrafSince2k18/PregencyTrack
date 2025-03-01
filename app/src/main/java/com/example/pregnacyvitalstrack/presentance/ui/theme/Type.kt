package com.example.pregnacyvitalstrack.presentance.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.pregnacyvitalstrack.R

val poppinsBold = FontFamily(Font(R.font.poppinsbold))
val poppinsLight = FontFamily(Font(R.font.poppinslight))
val poppinsSemiBold = FontFamily(Font(R.font.poppinssemibold))
val roboto = FontFamily(Font(R.font.robotovariablefont))



// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )


)