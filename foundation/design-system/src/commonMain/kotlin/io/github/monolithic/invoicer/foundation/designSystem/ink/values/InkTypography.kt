package io.github.monolithic.invoicer.foundation.designSystem.ink.values

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import io.github.monolithic.invoicer.foundation.designSystem.ink.tokens.rememberUrbanistFontFamily

internal object InkTypeFaces {

    object Headline {
        val h1
            @Composable
            get() = TextStyle(
                fontSize = 48.sp,
                fontFamily = rememberUrbanistFontFamily(),
            )

        val h2
            @Composable
            get() = TextStyle(
                fontSize = 40.sp,
                fontFamily = rememberUrbanistFontFamily(),
            )

        val h3
            @Composable
            get() = TextStyle(
                fontSize = 32.sp,
                fontFamily = rememberUrbanistFontFamily(),
            )

        val h4
            @Composable
            get() = TextStyle(
                fontSize = 24.sp,
                fontFamily = rememberUrbanistFontFamily(),
            )

        val h5
            @Composable
            get() = TextStyle(
                fontSize = 20.sp,
                fontFamily = rememberUrbanistFontFamily(),
            )

        val h6
            @Composable
            get() = TextStyle(
                fontSize = 18.sp,
                fontFamily = rememberUrbanistFontFamily(),
            )
    }

    object Body {
        val xLarge
            @Composable
            get() = TextStyle(
                fontSize = 18.sp,
                fontFamily = rememberUrbanistFontFamily(),
            )

        val large
            @Composable
            get() = TextStyle(
                fontSize = 16.sp,
                fontFamily = rememberUrbanistFontFamily(),
            )

        val medium
            @Composable
            get() = TextStyle(
                fontSize = 14.sp,
                fontFamily = rememberUrbanistFontFamily(),
            )

        val small
            @Composable
            get() = TextStyle(
                fontSize = 12.sp,
                fontFamily = rememberUrbanistFontFamily(),
            )

        val xSmall
            @Composable
            get() = TextStyle(
                fontSize = 10.sp,
                fontFamily = rememberUrbanistFontFamily(),
            )
    }
}

data class InkTypography(
    val h1: TextStyle,
    val h2: TextStyle,
    val h3: TextStyle,
    val h4: TextStyle,
    val h5: TextStyle,
    val h6: TextStyle,
    val bodyXLarge: TextStyle,
    val bodyLarge: TextStyle,
    val bodyMedium: TextStyle,
    val bodySmall: TextStyle,
    val bodyXSmall: TextStyle,
)

@Composable
internal fun inkTypography() = InkTypography(
    h1 = InkTypeFaces.Headline.h1,
    h2 = InkTypeFaces.Headline.h2,
    h3 = InkTypeFaces.Headline.h3,
    h4 = InkTypeFaces.Headline.h4,
    h5 = InkTypeFaces.Headline.h5,
    h6 = InkTypeFaces.Headline.h6,
    bodyXLarge = InkTypeFaces.Body.xLarge,
    bodyLarge = InkTypeFaces.Body.large,
    bodyMedium = InkTypeFaces.Body.medium,
    bodySmall = InkTypeFaces.Body.small,
    bodyXSmall = InkTypeFaces.Body.xSmall,
)