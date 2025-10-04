package io.github.monolithic.invoicer.foundation.designSystem.ink.public.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import invoicer.multiplatform.foundation.design_system.generated.resources.DsResources
import invoicer.multiplatform.foundation.design_system.generated.resources.img_success_default
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.InkText
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.InkTextStyle
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.SpacerSize
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.VerticalSpacer
import org.jetbrains.compose.resources.painterResource

@Composable
fun SuccessState(
    title: String,
    description: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center

    ) {
        Image(
            painter = painterResource(DsResources.drawable.img_success_default),
            contentDescription = null
        )
        VerticalSpacer(height = SpacerSize.XLarge3)
        InkText(
            text = title,
            style = InkTextStyle.Heading3,
            weight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        VerticalSpacer(height = SpacerSize.Small)
        InkText(
            text = description,
            style = InkTextStyle.BodyXlarge,
            textAlign = TextAlign.Center
        )
    }
}