package io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.topbar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.InkText
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.InkTextStyle
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.Spacer
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.icon.InkIconButton
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.icon.basic.InkIconButtonDefaults
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.topbar.props.InkTopbarColors
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.theme.InkTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InkTopBar(
    navigationIcon: Painter,
    onNavigationClick: () -> Unit,
    modifier: Modifier = Modifier,
    title: String? = null,
    colors: InkTopbarColors = InkTopBarDefaults.colors,
    actions: @Composable RowScope.() -> Unit = {},
) {

    Surface(
        modifier = modifier.height(InkTopBarDefaults.Height),
        color = colors.containerColor
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(InkTheme.spacing.small)
        ) {
            InkIconButton(
                onClick = onNavigationClick,
                icon = navigationIcon,
                colors = InkIconButtonDefaults.colors.copy(
                    iconColor = colors.navigationIconColor,
                    containerColor = colors.containerColor
                )
            )
            title?.let {
                InkText(
                    text = it,
                    style = InkTextStyle.Heading4,
                    weight = FontWeight.SemiBold
                )
            }
            Spacer(1f)
            actions()
        }
    }
}

object InkTopBarDefaults {
    internal val Height = 64.dp

    val colors: InkTopbarColors
        @Composable
        get() = InkTopbarColors(
            containerColor = InkTheme.colorScheme.background,
            navigationIconColor = InkTheme.colorScheme.onBackground
        )
}

