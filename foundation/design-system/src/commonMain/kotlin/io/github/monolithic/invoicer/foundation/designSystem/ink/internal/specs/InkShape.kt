package io.github.monolithic.invoicer.foundation.designSystem.ink.internal.specs

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

data object InkShape {
    val regular: Shape = RoundedCornerShape(14.dp)
    val small: Shape = RoundedCornerShape(8.dp)
    val large: Shape = RoundedCornerShape(20.dp)
    val pill: Shape = RoundedCornerShape(100)
}
