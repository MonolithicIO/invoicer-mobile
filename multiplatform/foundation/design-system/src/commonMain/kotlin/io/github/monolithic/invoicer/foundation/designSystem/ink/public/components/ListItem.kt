package io.github.monolithic.invoicer.foundation.designSystem.ink.public.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import invoicer.multiplatform.foundation.design_system.generated.resources.DsResources
import invoicer.multiplatform.foundation.design_system.generated.resources.ic_chevron_right
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.InkSurface
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.InkText
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.InkTextStyle
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.icon.InkIcon
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.toggle.InkToggle
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.theme.InkTheme
import org.jetbrains.compose.resources.painterResource

private val IconSize = 24.dp

@Composable
fun ListItem(
    icon: Painter,
    text: String,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(vertical = InkTheme.spacing.medium),
    containerColor: Color = InkTheme.colorScheme.background,
    contentColor: Color = InkTheme.colorScheme.onBackground,
    onClick: (() -> Unit)? = null,
    showNavIcon: Boolean = onClick != null
) {
    InkSurface(
        color = containerColor,
        onClick = onClick ?: {},
        enabled = onClick != null
    ) {
        Row(
            modifier = modifier
                .padding(contentPadding),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(InkTheme.spacing.medium)
        ) {
            InkIcon(
                painter = icon,
                tint = contentColor,
                contentDescription = null,
                modifier = Modifier.size(IconSize)
            )
            InkText(
                modifier = Modifier.weight(1f),
                overflow = TextOverflow.Ellipsis,
                text = text,
                color = contentColor,
                weight = FontWeight.Bold,
                style = InkTextStyle.Heading6
            )
            if (onClick != null && showNavIcon) {
                InkIcon(
                    painter = painterResource(DsResources.drawable.ic_chevron_right),
                    contentDescription = null,
                    tint = contentColor
                )
            }
        }
    }
}

@Composable
fun LabeledListItem(
    label: String,
    value: String,
    valueColor: Color = InkTheme.colorScheme.onBackground,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(InkTheme.spacing.small)
    ) {
        InkText(
            text = label,
            style = InkTextStyle.BodyLarge,
            modifier = Modifier.weight(1f)
        )
        InkText(
            text = value,
            style = InkTextStyle.BodyXlarge,
            modifier = Modifier.weight(1f),
            weight = FontWeight.SemiBold,
            color = valueColor,
            textAlign = TextAlign.End
        )
    }
}

@Composable
fun ToggleListItem(
    label: String,
    labelWeight: FontWeight = FontWeight.Normal,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(InkTheme.spacing.small)
    ) {
        InkText(
            text = label,
            style = InkTextStyle.BodyLarge,
            modifier = Modifier.weight(1f),
            weight = labelWeight
        )
        InkToggle(
            checked = checked,
            onClick = onCheckedChange
        )
    }
}
