package io.github.monolithic.invoicer.foundation.designSystem.ink.values

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import io.github.monolithic.invoicer.foundation.designSystem.ink.tokens.rememberUrbanistFontFamily

internal object InkTextStyle {

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