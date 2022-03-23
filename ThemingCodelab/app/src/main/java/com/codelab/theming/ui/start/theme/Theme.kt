package com.codelab.theming.ui.start.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color.Companion.White


class Theme {
    /*
    * lightColors(
    primary: Color = Color(0xFF6200EE),
    primaryVariant: Color = Color(0xFF3700B3),
    secondary: Color = Color(0xFF03DAC6),
    secondaryVariant: Color = Color(0xFF018786),
    background: Color = Color.White,
    surface: Color = Color.White,
    error: Color = Color(0xFFB00020),
    onPrimary: Color = Color.White,
    onSecondary: Color = Color.Black,
    onBackground: Color = Color.Black,
    onSurface: Color = Color.Black,
    onError: Color = Color.White
)
* */

}

private val LightColors = lightColors(
    primary = Red700,
    primaryVariant = Red900,
    onPrimary = White,
    secondary = Red700,
    secondaryVariant = Red900,
    onSecondary = White,
    error = Red800
)

@Composable
fun JetnewsTheme(content: @Composable () -> Unit) {
    MaterialTheme(colors = LightColors,
        typography = JetnewsTypography,
        content = content)
}