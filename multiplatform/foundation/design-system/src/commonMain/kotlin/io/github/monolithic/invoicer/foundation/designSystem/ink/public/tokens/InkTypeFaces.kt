package io.github.monolithic.invoicer.foundation.designSystem.ink.public.tokens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import invoicer.multiplatform.foundation.design_system.generated.resources.DsResources
import invoicer.multiplatform.foundation.design_system.generated.resources.Urbanist_Black
import invoicer.multiplatform.foundation.design_system.generated.resources.Urbanist_BlackItalic
import invoicer.multiplatform.foundation.design_system.generated.resources.Urbanist_Bold
import invoicer.multiplatform.foundation.design_system.generated.resources.Urbanist_BoldItalic
import invoicer.multiplatform.foundation.design_system.generated.resources.Urbanist_ExtraBold
import invoicer.multiplatform.foundation.design_system.generated.resources.Urbanist_ExtraBoldItalic
import invoicer.multiplatform.foundation.design_system.generated.resources.Urbanist_ExtraLight
import invoicer.multiplatform.foundation.design_system.generated.resources.Urbanist_ExtraLightItalic
import invoicer.multiplatform.foundation.design_system.generated.resources.Urbanist_Italic
import invoicer.multiplatform.foundation.design_system.generated.resources.Urbanist_Light
import invoicer.multiplatform.foundation.design_system.generated.resources.Urbanist_LightItalic
import invoicer.multiplatform.foundation.design_system.generated.resources.Urbanist_Medium
import invoicer.multiplatform.foundation.design_system.generated.resources.Urbanist_MediumItalic
import invoicer.multiplatform.foundation.design_system.generated.resources.Urbanist_Regular
import invoicer.multiplatform.foundation.design_system.generated.resources.Urbanist_SemiBold
import invoicer.multiplatform.foundation.design_system.generated.resources.Urbanist_SemiBoldItalic
import invoicer.multiplatform.foundation.design_system.generated.resources.Urbanist_Thin
import invoicer.multiplatform.foundation.design_system.generated.resources.Urbanist_ThinItalic
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import org.jetbrains.compose.resources.Font

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
                fontSize = 20.sp,
                fontFamily = rememberUrbanistFontFamily(),
            )

        val large
            @Composable
            get() = TextStyle(
                fontSize = 18.sp,
                fontFamily = rememberUrbanistFontFamily(),
            )

        val medium
            @Composable
            get() = TextStyle(
                fontSize = 16.sp,
                fontFamily = rememberUrbanistFontFamily(),
            )

        val small
            @Composable
            get() = TextStyle(
                fontSize = 14.sp,
                fontFamily = rememberUrbanistFontFamily(),
            )

        val xSmall
            @Composable
            get() = TextStyle(
                fontSize = 12.sp,
                fontFamily = rememberUrbanistFontFamily(),
            )
    }
}

@Composable
private fun rememberUrbanistFontFamily(): FontFamily {
    val normal = urbanistNormal()
    val italic = urbanistItalic()

    return remember {
        FontFamily(
            normal + italic
        )
    }
}

@Composable
private fun urbanistNormal(): ImmutableList<Font> {
    val urbanistRegular =
        Font(DsResources.font.Urbanist_Regular, FontWeight.Normal, style = FontStyle.Normal)
    val urbanistThin =
        Font(DsResources.font.Urbanist_Thin, FontWeight.Thin, style = FontStyle.Normal)
    val urbanistLight =
        Font(DsResources.font.Urbanist_Light, FontWeight.Light, style = FontStyle.Normal)
    val urbanistBold =
        Font(DsResources.font.Urbanist_Bold, FontWeight.Bold, style = FontStyle.Normal)
    val urbanistExtraBold =
        Font(DsResources.font.Urbanist_ExtraBold, FontWeight.ExtraBold, style = FontStyle.Normal)
    val urbanistSemiBold =
        Font(DsResources.font.Urbanist_SemiBold, FontWeight.SemiBold, style = FontStyle.Normal)
    val urbanistMedium =
        Font(DsResources.font.Urbanist_Medium, FontWeight.Medium, style = FontStyle.Normal)
    val urbanistExtraLight =
        Font(DsResources.font.Urbanist_ExtraLight, FontWeight.ExtraLight, style = FontStyle.Normal)
    val urbanistBlack =
        Font(DsResources.font.Urbanist_Black, FontWeight.Black, style = FontStyle.Normal)

    return remember {
        persistentListOf(
            urbanistRegular,
            urbanistThin,
            urbanistLight,
            urbanistBold,
            urbanistExtraBold,
            urbanistSemiBold,
            urbanistMedium,
            urbanistExtraLight,
            urbanistBlack
        )
    }
}

@Composable
private fun urbanistItalic(): ImmutableList<Font> {
    val urbanistItalic =
        Font(DsResources.font.Urbanist_Italic, FontWeight.Normal, style = FontStyle.Italic)
    val urbanistThin =
        Font(DsResources.font.Urbanist_ThinItalic, FontWeight.Thin, style = FontStyle.Italic)
    val urbanistLight =
        Font(DsResources.font.Urbanist_LightItalic, FontWeight.Light, style = FontStyle.Italic)
    val urbanistBold =
        Font(DsResources.font.Urbanist_BoldItalic, FontWeight.Bold, style = FontStyle.Italic)
    val urbanistExtraBold =
        Font(
            DsResources.font.Urbanist_ExtraBoldItalic,
            FontWeight.ExtraBold,
            style = FontStyle.Italic
        )
    val urbanistSemiBold =
        Font(
            DsResources.font.Urbanist_SemiBoldItalic,
            FontWeight.SemiBold,
            style = FontStyle.Italic
        )
    val urbanistMedium =
        Font(DsResources.font.Urbanist_MediumItalic, FontWeight.Medium, style = FontStyle.Italic)
    val urbanistExtraLight =
        Font(
            DsResources.font.Urbanist_ExtraLightItalic,
            FontWeight.ExtraLight,
            style = FontStyle.Italic
        )
    val urbanistBlack =
        Font(DsResources.font.Urbanist_BlackItalic, FontWeight.Black, style = FontStyle.Italic)

    return remember {
        persistentListOf(
            urbanistItalic,
            urbanistThin,
            urbanistLight,
            urbanistBold,
            urbanistExtraBold,
            urbanistSemiBold,
            urbanistMedium,
            urbanistExtraLight,
            urbanistBlack
        )
    }
}
