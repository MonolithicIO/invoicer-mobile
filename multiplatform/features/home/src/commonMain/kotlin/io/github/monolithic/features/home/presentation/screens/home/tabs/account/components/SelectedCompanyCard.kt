package io.github.monolithic.features.home.presentation.screens.home.tabs.account.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import invoicer.multiplatform.features.home.generated.resources.Res
import invoicer.multiplatform.features.home.generated.resources.account_selected_company_title
import invoicer.multiplatform.foundation.design_system.generated.resources.DsResources
import invoicer.multiplatform.foundation.design_system.generated.resources.ic_chevron_right
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.InkCard
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.InkText
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.InkTextStyle
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.icon.InkIcon
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.theme.InkTheme
import io.github.monolithic.invoicer.foundation.designSystem.ink.public.components.CompanyNameIcon
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun SelectedCompanyCard(
    companyName: String,
    onClick: () -> Unit,
    contentPadding: PaddingValues,
    containerColor: Color,
) {
    InkCard(
        contentPadding = contentPadding,
        containerColor = containerColor,
        onClick = onClick
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(InkTheme.spacing.medium)
        ) {
            CompanyNameIcon(
                name = companyName
            )
            Column(modifier = Modifier.weight(1f)) {
                InkText(
                    text = stringResource(Res.string.account_selected_company_title),
                    weight = FontWeight.Bold,
                    style = InkTextStyle.Heading6
                )
                InkText(
                    text = companyName,
                    weight = FontWeight.SemiBold,
                    style = InkTextStyle.BodyLarge,
                    color = InkTheme.colorScheme.onBackgroundVariant
                )
            }
            InkIcon(
                painter = painterResource(DsResources.drawable.ic_chevron_right),
                contentDescription = null,
                tint = InkTheme.colorScheme.onBackground
            )
        }
    }
}