package com.example.kotlin_pr_9_12.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal, // Толщина шрифта
        fontSize = 16.sp,
        lineHeight = 10.sp, // Высота строки
        letterSpacing = 0.5.sp // Интервал между символами
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)
val CustomTypography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Cursive,
        fontSize = 24.sp,
        lineHeight = 10.sp,
        letterSpacing = 0.2.sp
    )
)