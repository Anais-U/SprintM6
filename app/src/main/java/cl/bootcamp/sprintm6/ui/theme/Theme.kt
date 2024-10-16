package cl.bootcamp.sprintm6.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val DarkPrimaryColor = Color(0xFF252243) // #FBC02D
val LightPrimaryColor = Color(0xFFFFFFFF) // #FFF9C4
val PrimaryColor = Color(0xFF63E9F8) // #9E9E9E
val AccentColor = Color(0xFF5161F1) // #FFEB3B



private val LightColors = lightColorScheme(
    primaryContainer = PrimaryColor,
    onPrimary = DarkPrimaryColor,
    secondary = AccentColor,
    background = LightPrimaryColor,
    onBackground = DarkPrimaryColor

)

private val DarkColors = darkColorScheme(
    primary = DarkPrimaryColor,
    primaryContainer = LightPrimaryColor,
    secondary = AccentColor
)

@Composable
fun SprintM6Theme(
    useDarkTheme: Boolean = false,
    content: @Composable () -> Unit
) {
    val colors = if (useDarkTheme) {
        DarkColors
    } else {
        LightColors
    }

    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        content = content
    )
}