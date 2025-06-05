package io.github.alaksion.invoicer.foundation.designSystem.components.card

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import io.github.alaksion.invoicer.foundation.designSystem.tokens.Spacing

@Composable
fun ListCard(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
    leadingContent: (@Composable () -> Unit)? = null,
    trailingContent: (@Composable () -> Unit)? = null
) {
    Card(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Spacing.small),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(Spacing.medium)
        ) {
            leadingContent?.let { it() }
            Column(modifier = Modifier.weight(1f)) {
                content()
            }
            trailingContent?.let { it() }
        }
    }
}
