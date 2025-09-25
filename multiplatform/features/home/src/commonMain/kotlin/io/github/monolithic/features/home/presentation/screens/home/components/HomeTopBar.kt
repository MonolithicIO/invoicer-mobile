package io.github.monolithic.features.home.presentation.screens.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import invoicer.multiplatform.features.home.generated.resources.Res
import invoicer.multiplatform.features.home.generated.resources.welcome_topbar_subtitle
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.InkSurface
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.InkText
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.InkTextStyle
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.theme.InkTheme
import io.github.monolithic.invoicer.foundation.designSystem.ink.public.components.CompanyNameIcon
import org.jetbrains.compose.resources.stringResource

private val BarHeight = 72.dp

@Composable
internal fun HomeTopBar(
    backgroundColor: Color,
    companyName: String,
    modifier: Modifier = Modifier,
) {
    InkSurface(
        color = backgroundColor,
        modifier = modifier
            .defaultMinSize(minHeight = BarHeight)
            .padding(horizontal = InkTheme.spacing.medium)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(InkTheme.spacing.small)
        ) {
            CompanyNameIcon(
                name = companyName
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(InkTheme.spacing.xSmall2)
            ) {
                InkText(
                    text = companyName,
                    style = InkTextStyle.Heading5,
                    weight = FontWeight.Bold
                )
                InkText(
                    text = stringResource(Res.string.welcome_topbar_subtitle),
                    style = InkTextStyle.BodyMedium,
                    color = InkTheme.colorScheme.onBackgroundVariant
                )
            }
        }
    }
}
