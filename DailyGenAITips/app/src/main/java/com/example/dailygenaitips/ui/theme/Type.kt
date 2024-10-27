package com.example.dailygenaitips.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.text.style.LineBreak
import com.example.dailygenaitips.R

val customLineBreak = LineBreak(
    strategy = LineBreak.Strategy.HighQuality,
    strictness = LineBreak.Strictness.Strict,
    wordBreak = LineBreak.WordBreak.Phrase
)

val provider = GoogleFont.Provider(
    providerAuthority = "com.google.android.gms.fonts",
    providerPackage = "com.google.android.gms",
    certificates = R.array.com_google_android_gms_fonts_certs
)

val bodyFontFamily = FontFamily(
    Font(
        googleFont = GoogleFont("Zen Kaku Gothic New"),
        fontProvider = provider,
    )
)

val displayFontFamily = FontFamily(
    Font(
        googleFont = GoogleFont("Zen Kaku Gothic New"),
        weight = androidx.compose.ui.text.font.FontWeight.W900,
        fontProvider = provider,
    )
)

// Default Material 3 typography values
val baseline = Typography()

val AppTypography = Typography(
    displayLarge = baseline.displayLarge.copy(
        fontFamily = displayFontFamily,
        lineBreak = customLineBreak
    ),
    displayMedium = baseline.displayMedium.copy(
        fontFamily = displayFontFamily,
        lineBreak = customLineBreak
    ),
    displaySmall = baseline.displaySmall.copy(
        fontFamily = displayFontFamily,
        lineBreak = customLineBreak
    ),
    headlineLarge = baseline.headlineLarge.copy(
        fontFamily = displayFontFamily,
        lineBreak = customLineBreak
    ),
    headlineMedium = baseline.headlineMedium.copy(
        fontFamily = displayFontFamily,
        lineBreak = customLineBreak
    ),
    headlineSmall = baseline.headlineSmall.copy(
        fontFamily = displayFontFamily,
        lineBreak = customLineBreak
    ),
    titleLarge = baseline.titleLarge.copy(
        fontFamily = displayFontFamily,
        lineBreak = customLineBreak
    ),
    titleMedium = baseline.titleMedium.copy(
        fontFamily = displayFontFamily,
        lineBreak = customLineBreak
    ),
    titleSmall = baseline.titleSmall.copy(
        fontFamily = displayFontFamily,
        lineBreak = customLineBreak
    ),
    bodyLarge = baseline.bodyLarge.copy(fontFamily = bodyFontFamily, lineBreak = customLineBreak),
    bodyMedium = baseline.bodyMedium.copy(fontFamily = bodyFontFamily, lineBreak = customLineBreak),
    bodySmall = baseline.bodySmall.copy(fontFamily = bodyFontFamily, lineBreak = customLineBreak),
    labelLarge = baseline.labelLarge.copy(fontFamily = bodyFontFamily, lineBreak = customLineBreak),
    labelMedium = baseline.labelMedium.copy(
        fontFamily = bodyFontFamily,
        lineBreak = customLineBreak
    ),
    labelSmall = baseline.labelSmall.copy(fontFamily = bodyFontFamily, lineBreak = customLineBreak),
)

