package io.github.monolithic.invoicer.foundation.designSystem.ink.tokens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.text.font.Font
import invoicer.foundation.design_system.generated.resources.Res
import invoicer.foundation.design_system.generated.resources.Urbanist_Bold
import invoicer.foundation.design_system.generated.resources.Urbanist_BoldItalic
import invoicer.foundation.design_system.generated.resources.Urbanist_Light
import invoicer.foundation.design_system.generated.resources.Urbanist_LightItalic
import invoicer.foundation.design_system.generated.resources.Urbanist_Medium
import invoicer.foundation.design_system.generated.resources.Urbanist_MediumItalic
import invoicer.foundation.design_system.generated.resources.Urbanist_Regular
import invoicer.foundation.design_system.generated.resources.Urbanist_SemiBold
import invoicer.foundation.design_system.generated.resources.Urbanist_SemiBoldItalic
import invoicer.foundation.design_system.generated.resources.Urbanist_Thin
import invoicer.foundation.design_system.generated.resources.Urbanist_ThinItalic

internal interface InkFontFamily {
    val regular: Font
    val thin: Font
    val light: Font
    val bold: Font
    val semiBold: Font
    val medium: Font
    val italicBold: Font
    val italicSemiBold: Font
    val italicMedium: Font
    val thinItalic: Font
    val lightItalic: Font
}

@Composable
internal fun rememberUrbanistFontFamily(): InkFontFamily {
    val urbanistRegular = org.jetbrains.compose.resources.Font(Res.font.Urbanist_Regular)
    val urbanistThin = org.jetbrains.compose.resources.Font(Res.font.Urbanist_Thin)
    val urbanistLight = org.jetbrains.compose.resources.Font(Res.font.Urbanist_Light)
    val urbanistBold = org.jetbrains.compose.resources.Font(Res.font.Urbanist_Bold)
    val urbanistSemiBold = org.jetbrains.compose.resources.Font(Res.font.Urbanist_SemiBold)
    val urbanistMedium = org.jetbrains.compose.resources.Font(Res.font.Urbanist_Medium)
    val urbanistItalicBold = org.jetbrains.compose.resources.Font(Res.font.Urbanist_BoldItalic)
    val urbanistItalicSemiBold =
        org.jetbrains.compose.resources.Font(Res.font.Urbanist_SemiBoldItalic)
    val urbanistItalicMedium = org.jetbrains.compose.resources.Font(Res.font.Urbanist_MediumItalic)
    val urbanistThinItalic = org.jetbrains.compose.resources.Font(Res.font.Urbanist_ThinItalic)
    val urbanistLightItalic = org.jetbrains.compose.resources.Font(Res.font.Urbanist_LightItalic)

    return remember {
        object : InkFontFamily {
            override val regular: Font = urbanistRegular
            override val thin: Font = urbanistThin
            override val light: Font = urbanistLight
            override val bold: Font = urbanistBold
            override val semiBold: Font = urbanistSemiBold
            override val medium: Font = urbanistMedium
            override val italicBold: Font = urbanistItalicBold
            override val italicSemiBold: Font = urbanistItalicSemiBold
            override val italicMedium: Font = urbanistItalicMedium
            override val thinItalic: Font = urbanistThinItalic
            override val lightItalic: Font = urbanistLightItalic
        }
    }
}