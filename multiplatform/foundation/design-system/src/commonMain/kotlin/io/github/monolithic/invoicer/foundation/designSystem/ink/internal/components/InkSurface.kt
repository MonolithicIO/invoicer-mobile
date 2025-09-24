package io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.semantics.isContainer
import androidx.compose.ui.semantics.semantics
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.theme.InkTheme

@Composable
fun InkSurface(
    modifier: Modifier = Modifier,
    color: Color = InkTheme.colorScheme.background,
    shape: Shape = RectangleShape,
    content: @Composable () -> Unit
) {
    Box(
        modifier =
            modifier
                .background(
                    shape = shape,
                    color = color,
                )
                .semantics(mergeDescendants = false) {
                    @Suppress("DEPRECATION")
                    isContainer = true
                }
                .pointerInput(Unit) {},
        propagateMinConstraints = true
    ) {
        content()
    }
}