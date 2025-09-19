package io.github.monolithic.invoicer.features.auth.presentation.screens.signup.components

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import invoicer.features.auth.presentation.generated.resources.Res
import invoicer.features.auth.presentation.generated.resources.auth_sign_up_description
import invoicer.features.auth.presentation.generated.resources.auth_sign_up_title
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.InkText
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.InkTextStyle
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.SpacerSize
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.VerticalSpacer
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.theme.InkTheme
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun SignUpHeader(
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        InkText(
            text = stringResource(Res.string.auth_sign_up_title),
            style = InkTextStyle.Heading3,
            weight = FontWeight.Bold
        )
        VerticalSpacer(SpacerSize.XSmall)
        InkText(
            text = stringResource(Res.string.auth_sign_up_description),
            style = InkTextStyle.BodyXlarge,
            color = InkTheme.colorScheme.onBackgroundVariant
        )
    }
}
