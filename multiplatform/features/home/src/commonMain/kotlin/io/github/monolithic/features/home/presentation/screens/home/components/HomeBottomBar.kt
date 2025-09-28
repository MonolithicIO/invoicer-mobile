package io.github.monolithic.features.home.presentation.screens.home.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import cafe.adriel.voyager.navigator.tab.Tab
import invoicer.multiplatform.foundation.design_system.generated.resources.DsResources
import invoicer.multiplatform.foundation.design_system.generated.resources.ic_home
import invoicer.multiplatform.foundation.design_system.generated.resources.ic_user
import invoicer.multiplatform.foundation.design_system.generated.resources.ic_user_group
import io.github.monolithic.features.home.presentation.screens.home.tabs.account.AccountTab
import io.github.monolithic.features.home.presentation.screens.home.tabs.welcome.WelcomeTab
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.InkSurface
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.InkText
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.InkTextStyle
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.icon.InkIconButton
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.icon.basic.InkIconButtonDefaults
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.theme.InkTheme
import org.jetbrains.compose.resources.painterResource

@Composable
internal fun HomeBottomBar(
    modifier: Modifier = Modifier,
    onCustomersClick: () -> Unit,
    selectedTab: Tab,
    onSelectTab: (Tab) -> Unit
) {
    InkSurface(
        modifier = modifier,
        color = InkTheme.colorScheme.background
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(vertical = InkTheme.spacing.xSmall3),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            NavigationItem(
                icon = painterResource(DsResources.drawable.ic_home),
                label = "Home",
                selected = selectedTab is WelcomeTab,
                onClick = { onSelectTab(WelcomeTab) }
            )
            NavigationItem(
                icon = painterResource(DsResources.drawable.ic_user_group),
                label = "Customers",
                selected = false,
                onClick = onCustomersClick
            )
            NavigationItem(
                icon = painterResource(DsResources.drawable.ic_user),
                label = "Account",
                selected = selectedTab is AccountTab,
                onClick = { onSelectTab(AccountTab) }
            )
        }
    }
}

@Composable
private fun NavigationItem(
    icon: Painter,
    label: String,
    selected: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    val colors = InkTheme.colorScheme

    val currentContentColor = remember(selected) {
        if (selected) colors.primary
        else colors.onBackgroundVariant
    }

    val animatedColor by animateColorAsState(targetValue = currentContentColor)

    val finalModifier = remember(selected) {
        if (selected) Modifier else Modifier.clickable(
            onClick = onClick,
            interactionSource = MutableInteractionSource(),
            indication = null
        )
    }

    Column(
        modifier = modifier
            .then(finalModifier),
        verticalArrangement = Arrangement.spacedBy(InkTheme.spacing.xSmall3),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        InkIconButton(
            icon = icon,
            onClick = onClick,
            colors = InkIconButtonDefaults.colors.copy(
                iconColor = animatedColor
            )
        )
        InkText(
            text = label,
            style = InkTextStyle.BodyMedium,
            weight = if (selected) FontWeight.Bold else FontWeight.Normal,
            color = animatedColor
        )
    }
}
