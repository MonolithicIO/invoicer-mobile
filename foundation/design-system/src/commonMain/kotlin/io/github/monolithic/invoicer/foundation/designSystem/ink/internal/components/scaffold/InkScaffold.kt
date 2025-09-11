package io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.scaffold

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.scaffold.props.InkFabPosition
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.specs.InkColor
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.theme.InkTheme

@Composable
fun InkScaffold(
    modifier: Modifier = Modifier,
    topBar: @Composable () -> Unit = {},
    bottomBar: @Composable () -> Unit = {},
    snackBarHost: @Composable () -> Unit = {},
    floatingActionButton: @Composable () -> Unit = {},
    floatingActionButtonPosition: InkFabPosition = InkFabPosition.End,
    containerColor: InkColor = InkTheme.colorScheme.background,
    contentColor: InkColor = InkTheme.colorScheme.onBackground,
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        modifier = modifier,
        topBar = topBar,
        bottomBar = bottomBar,
        snackbarHost = { snackBarHost() },
        floatingActionButton = floatingActionButton,
        floatingActionButtonPosition = floatingActionButtonPosition.materialValue,
        containerColor = containerColor.value,
        contentColor = contentColor.value,
        content = content
    )
}