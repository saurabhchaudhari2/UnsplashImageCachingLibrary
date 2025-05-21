package com.saurabh.imagecachinglibrary.presentation.theme

// import android.app.Activity // Android specific
// import android.os.Build // Android specific
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
// import androidx.compose.material3.dynamicDarkColorScheme // Android specific
// import androidx.compose.material3.dynamicLightColorScheme // Android specific
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
// import androidx.compose.runtime.SideEffect // Used for Android specific status bar
// import androidx.compose.ui.graphics.toArgb // Android specific
// import androidx.compose.ui.platform.LocalContext // Android specific context for dynamic colors
// import androidx.compose.ui.platform.LocalView // Android specific view for status bar
// import androidx.core.view.WindowCompat // Android specific

private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
fun UnsplashImageCachingLibraryTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is commented out as it's Android specific.
    // KMP might require expect/actual for platform-specific theming capabilities.
    // dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        // dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> { // Android specific
        //     val context = LocalContext.current // Android specific
        //     if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context) // Android specific
        // }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    // val view = LocalView.current // Android specific
    // if (!view.isInEditMode) { // Android specific
    //     SideEffect { // Android specific
    //         val window = (view.context as Activity).window // Android specific
    //         window.statusBarColor = colorScheme.primary.toArgb() // Android specific
    //         WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme // Android specific
    //     }
    // }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
