package io.github.monolithic.features.home.presentation.screens.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import invoicer.multiplatform.features.home.generated.resources.Res
import invoicer.multiplatform.features.home.generated.resources.account_topbar_title
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.InkSurface
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.InkText
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.InkTextStyle
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.theme.InkTheme
import org.jetbrains.compose.resources.stringResource

private val BarHeight = 72.dp

@Composable
internal fun AccountTopBar(
    modifier: Modifier = Modifier
) {
    InkSurface(
        modifier = modifier,
        color = InkTheme.colorScheme.background
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .defaultMinSize(minHeight = BarHeight),
            horizontalArrangement = Arrangement.spacedBy(InkTheme.spacing.small),
            verticalAlignment = Alignment.CenterVertically
        ) {
            InkText(
                modifier = Modifier.weight(1f),
                text = stringResource(Res.string.account_topbar_title),
                style = InkTextStyle.Heading4,
                weight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }
    }
}
