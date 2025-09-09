package io.github.monolithic.invoicer.foundation.designSystem.ink.tokens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import invoicer.foundation.design_system.generated.resources.Res
import invoicer.foundation.design_system.generated.resources.Urbanist_BlackItalic
import invoicer.foundation.design_system.generated.resources.Urbanist_Bold
import invoicer.foundation.design_system.generated.resources.Urbanist_BoldItalic
import invoicer.foundation.design_system.generated.resources.Urbanist_ExtraBold
import invoicer.foundation.design_system.generated.resources.Urbanist_ExtraBoldItalic
import invoicer.foundation.design_system.generated.resources.Urbanist_ExtraLight
import invoicer.foundation.design_system.generated.resources.Urbanist_ExtraLightItalic
import invoicer.foundation.design_system.generated.resources.Urbanist_Italic
import invoicer.foundation.design_system.generated.resources.Urbanist_Light
import invoicer.foundation.design_system.generated.resources.Urbanist_LightItalic
import invoicer.foundation.design_system.generated.resources.Urbanist_Medium
import invoicer.foundation.design_system.generated.resources.Urbanist_MediumItalic
import invoicer.foundation.design_system.generated.resources.Urbanist_Regular
import invoicer.foundation.design_system.generated.resources.Urbanist_SemiBold
import invoicer.foundation.design_system.generated.resources.Urbanist_SemiBoldItalic
import invoicer.foundation.design_system.generated.resources.Urbanist_Thin
import invoicer.foundation.design_system.generated.resources.Urbanist_ThinItalic
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import org.jetbrains.compose.resources.Font

@Composable
internal fun rememberUrbanistFontFamily(): FontFamily {
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
        Font(Res.font.Urbanist_Regular, FontWeight.Normal, style = FontStyle.Normal)
    val urbanistThin =
        Font(Res.font.Urbanist_Thin, FontWeight.Thin, style = FontStyle.Normal)
    val urbanistLight =
        Font(Res.font.Urbanist_Light, FontWeight.Light, style = FontStyle.Normal)
    val urbanistBold =
        Font(Res.font.Urbanist_Bold, FontWeight.Bold, style = FontStyle.Normal)
    val urbanistExtraBold =
        Font(Res.font.Urbanist_ExtraBold, FontWeight.ExtraBold, style = FontStyle.Normal)
    val urbanistSemiBold =
        Font(Res.font.Urbanist_SemiBold, FontWeight.SemiBold, style = FontStyle.Normal)
    val urbanistMedium =
        Font(Res.font.Urbanist_Medium, FontWeight.Medium, style = FontStyle.Normal)
    val urbanistExtraLight =
        Font(Res.font.Urbanist_ExtraLight, FontWeight.ExtraLight, style = FontStyle.Normal)
    val urbanistBlack =
        Font(Res.font.Urbanist_BlackItalic, FontWeight.Black, style = FontStyle.Italic)

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
        Font(Res.font.Urbanist_Italic, FontWeight.Normal, style = FontStyle.Italic)
    val urbanistThin =
        Font(Res.font.Urbanist_ThinItalic, FontWeight.Thin, style = FontStyle.Italic)
    val urbanistLight =
        Font(Res.font.Urbanist_LightItalic, FontWeight.Light, style = FontStyle.Italic)
    val urbanistBold =
        Font(Res.font.Urbanist_BoldItalic, FontWeight.Bold, style = FontStyle.Italic)
    val urbanistExtraBold =
        Font(Res.font.Urbanist_ExtraBoldItalic, FontWeight.ExtraBold, style = FontStyle.Italic)
    val urbanistSemiBold =
        Font(Res.font.Urbanist_SemiBoldItalic, FontWeight.SemiBold, style = FontStyle.Italic)
    val urbanistMedium =
        Font(Res.font.Urbanist_MediumItalic, FontWeight.Medium, style = FontStyle.Italic)
    val urbanistExtraLight =
        Font(Res.font.Urbanist_ExtraLightItalic, FontWeight.ExtraLight, style = FontStyle.Italic)
    val urbanistBlack =
        Font(Res.font.Urbanist_BlackItalic, FontWeight.Black, style = FontStyle.Italic)

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