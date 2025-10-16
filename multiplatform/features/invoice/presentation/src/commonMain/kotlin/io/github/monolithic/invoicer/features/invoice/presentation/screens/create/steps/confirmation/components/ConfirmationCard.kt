package io.github.monolithic.invoicer.features.invoice.presentation.screens.create.steps.confirmation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import invoicer.multiplatform.features.invoice.presentation.generated.resources.Res
import invoicer.multiplatform.features.invoice.presentation.generated.resources.confirmation_amount_label
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.InkText
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.InkTextStyle
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.Spacer
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.theme.InkTheme
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun ConfirmationCard(
    label: String,
    content: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(InkTheme.spacing.medium)
    ) {
        InkText(
            text = label,
            style = InkTextStyle.BodyLarge
        )
        Spacer(1f)
        InkText(
            text = content,
            style = InkTextStyle.BodyXlarge,
            weight = FontWeight.SemiBold,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
internal fun AmountConfirmationCard(
    amount: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(InkTheme.spacing.medium)
    ) {
        InkText(
            text = stringResource(Res.string.confirmation_amount_label),
            style = InkTextStyle.BodyLarge,
            weight = FontWeight.Black
        )
        Spacer(1f)
        InkText(
            text = amount,
            style = InkTextStyle.BodyXlarge,
            weight = FontWeight.Black,
            color = InkTheme.colorScheme.primaryVariant
        )
    }
}
