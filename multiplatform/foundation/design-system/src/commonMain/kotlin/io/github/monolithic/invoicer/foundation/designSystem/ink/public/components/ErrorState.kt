package io.github.monolithic.invoicer.foundation.designSystem.ink.public.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import invoicer.multiplatform.foundation.design_system.generated.resources.DsResources
import invoicer.multiplatform.foundation.design_system.generated.resources.error_feedback_default_description
import invoicer.multiplatform.foundation.design_system.generated.resources.error_feedback_default_title
import invoicer.multiplatform.foundation.design_system.generated.resources.img_error_default
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.InkText
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.InkTextStyle
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.button.InkPrimaryButton
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.button.InkSecondaryButton
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.button.props.InkButtonSize
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.theme.InkTheme
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun ErrorState(
    modifier: Modifier = Modifier,
    title: String = stringResource(
        DsResources.string.error_feedback_default_title
    ),
    description: String = stringResource(
        DsResources.string.error_feedback_default_description
    ),
    primaryAction: ErrorStateAction? = null,
    secondaryAction: ErrorStateAction? = null
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(
            space = InkTheme.spacing.medium,
            alignment = Alignment.CenterVertically
        )
    ) {
        Image(
            painter = painterResource(DsResources.drawable.img_error_default),
            contentDescription = null
        )
        InkText(
            text = title,
            style = InkTextStyle.Heading4,
            weight = FontWeight.Bold
        )

        InkText(
            text = description,
            style = InkTextStyle.BodyMedium,
        )

        primaryAction?.let {
            InkPrimaryButton(
                modifier = Modifier.fillMaxWidth(),
                text = it.label,
                onClick = it.action,
                size = InkButtonSize.Small
            )
        }

        secondaryAction?.let {
            InkSecondaryButton(
                modifier = Modifier.fillMaxWidth(),
                text = it.label,
                onClick = it.action,
                size = InkButtonSize.Small
            )
        }
    }
}

data class ErrorStateAction(
    val label: String,
    val action: () -> Unit
)

