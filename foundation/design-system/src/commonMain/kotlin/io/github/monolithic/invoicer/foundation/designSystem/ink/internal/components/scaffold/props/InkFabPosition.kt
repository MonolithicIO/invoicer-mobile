package io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.scaffold.props

import androidx.compose.material3.FabPosition

enum class InkFabPosition(internal val materialValue: FabPosition) {
    Start(FabPosition.Start),
    Center(FabPosition.Center),
    End(FabPosition.End),
    EndOverlay(FabPosition.EndOverlay)
}
