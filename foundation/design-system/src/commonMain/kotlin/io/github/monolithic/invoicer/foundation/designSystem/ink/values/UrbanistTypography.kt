package io.github.monolithic.invoicer.foundation.designSystem.ink.values

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
import io.github.monolithic.invoicer.foundation.designSystem.ink.tokens.InkFontFamily
import org.jetbrains.compose.resources.Font

@Composable
internal fun rememberUrbanistFontFamily(): InkFontFamily {
    val urbanistRegular = Font(Res.font.Urbanist_Regular)
    val urbanistThin = Font(Res.font.Urbanist_Thin)
    val urbanistLight = Font(Res.font.Urbanist_Light)
    val urbanistBold = Font(Res.font.Urbanist_Bold)
    val urbanistSemiBold = Font(Res.font.Urbanist_SemiBold)
    val urbanistMedium = Font(Res.font.Urbanist_Medium)
    val urbanistItalicBold = Font(Res.font.Urbanist_BoldItalic)
    val urbanistItalicSemiBold = Font(Res.font.Urbanist_SemiBoldItalic)
    val urbanistItalicMedium = Font(Res.font.Urbanist_MediumItalic)
    val urbanistThinItalic = Font(Res.font.Urbanist_ThinItalic)
    val urbanistLightItalic = Font(Res.font.Urbanist_LightItalic)

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