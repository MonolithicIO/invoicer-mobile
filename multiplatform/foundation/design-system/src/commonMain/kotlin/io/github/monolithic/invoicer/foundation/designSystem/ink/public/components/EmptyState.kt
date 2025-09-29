package io.github.monolithic.invoicer.foundation.designSystem.ink.public.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import invoicer.multiplatform.foundation.design_system.generated.resources.DsResources
import invoicer.multiplatform.foundation.design_system.generated.resources.empty_feedback_default_title
import invoicer.multiplatform.foundation.design_system.generated.resources.img_not_found_dark
import invoicer.multiplatform.foundation.design_system.generated.resources.img_not_found_light
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.InkText
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.InkTextStyle
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.theme.InkTheme
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun EmptyState(
    modifier: Modifier = Modifier,
    title: String = stringResource(
        DsResources.string.empty_feedback_default_title
    ),
    description: String,
    image: Painter =
        if (InkTheme.isDark) painterResource(DsResources.drawable.img_not_found_dark)
        else painterResource(DsResources.drawable.img_not_found_light)
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(InkTheme.spacing.medium)
    ) {
        Image(
            painter = image,
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
    }
}
