package io.github.alaksion.invoicer.features.company.presentation.selectcompany

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Business
import androidx.compose.material.icons.outlined.ChevronRight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import invoicer.features.company.generated.resources.Res
import invoicer.features.company.generated.resources.company_selection_new_company
import invoicer.features.company.generated.resources.company_selection_title
import io.github.alaksion.invoicer.foundation.designSystem.components.LoadingState
import io.github.alaksion.invoicer.foundation.designSystem.components.ScreenTitle
import io.github.alaksion.invoicer.foundation.designSystem.components.buttons.CloseButton
import io.github.alaksion.invoicer.foundation.designSystem.components.buttons.PrimaryButton
import io.github.alaksion.invoicer.foundation.designSystem.components.card.ListCard
import io.github.alaksion.invoicer.foundation.designSystem.components.spacer.SpacerSize
import io.github.alaksion.invoicer.foundation.designSystem.components.spacer.VerticalSpacer
import io.github.alaksion.invoicer.foundation.designSystem.tokens.Spacing
import org.jetbrains.compose.resources.stringResource

internal class SelectCompanyScreen : Screen {

    @Composable
    override fun Content() {
        val screenModel = koinScreenModel<SelectCompanyScreenModel>()
        val state = screenModel.state.collectAsState()

        LaunchedEffect(screenModel) {
            screenModel.events.collect {
                when (it) {
                    SelectCompanyEvent.ContinueToHome -> Unit
                }
            }
        }

        StateContent(
            state = state.value,
            onSelectCompanyClick = {},
            onCreateCompanyClick = {}
        )
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun StateContent(
        state: SelectCompanyState,
        onCreateCompanyClick: () -> Unit,
        onSelectCompanyClick: (companyId: String) -> Unit,
    ) {
        Scaffold(
            modifier = Modifier.imePadding(),
            topBar = {
                TopAppBar(
                    title = {},
                    navigationIcon = {
                        CloseButton(onBackClick = {})
                    }
                )
            },
        ) { scaffoldPadding ->
            Column(
                modifier = Modifier
                    .padding(scaffoldPadding)
                    .padding(Spacing.medium)
            ) {
                ScreenTitle(
                    title = stringResource(Res.string.company_selection_title),
                    subTitle = null
                )
                VerticalSpacer(height = SpacerSize.XLarge)

                when (state.mode) {
                    SelectCompanyMode.Loading -> LoadingState(
                        modifier = Modifier.fillMaxSize()
                    )

                    SelectCompanyMode.List -> {
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f)
                        ) {
                            items(
                                items = state.companies,
                                key = { it.id }
                            ) {
                                ListCard(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable { onSelectCompanyClick(it.id) },
                                    leadingContent = {
                                        Icon(
                                            imageVector = Icons.Outlined.Business,
                                            contentDescription = null
                                        )
                                    },
                                    trailingContent = {
                                        Icon(
                                            imageVector = Icons.Outlined.ChevronRight,
                                            contentDescription = null
                                        )
                                    },
                                    content = {
                                        Column {
                                            Text(
                                                text = it.name,
                                                style = MaterialTheme.typography.bodyLarge,
                                                fontWeight = FontWeight.SemiBold
                                            )
                                            Text(
                                                text = it.document,
                                            )
                                        }
                                    }
                                )
                            }
                        }
                        VerticalSpacer(height = SpacerSize.Medium)
                        PrimaryButton(
                            label = stringResource(Res.string.company_selection_new_company),
                            onClick = onCreateCompanyClick
                        )
                    }

                    SelectCompanyMode.Error -> {
                        // Show error message
                    }

                    SelectCompanyMode.CreateCompany -> {
                        // Show create company form
                    }
                }
            }
        }
    }
}