package io.github.monolithic.invoicer.features.invoice.presentation.screens.create.steps.activities.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.FocusRequester.Companion.FocusRequesterFactory.component1
import androidx.compose.ui.focus.FocusRequester.Companion.FocusRequesterFactory.component2
import androidx.compose.ui.focus.FocusRequester.Companion.FocusRequesterFactory.component3
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import invoicer.multiplatform.features.invoice.presentation.generated.resources.Res
import invoicer.multiplatform.features.invoice.presentation.generated.resources.invoice_add_activity_cta
import invoicer.multiplatform.features.invoice.presentation.generated.resources.invoice_create_activity_form_description_label
import invoicer.multiplatform.features.invoice.presentation.generated.resources.invoice_create_activity_form_description_placeholder
import invoicer.multiplatform.features.invoice.presentation.generated.resources.invoice_create_activity_form_price_label
import invoicer.multiplatform.features.invoice.presentation.generated.resources.invoice_create_activity_form_price_placeholder
import invoicer.multiplatform.features.invoice.presentation.generated.resources.invoice_create_activity_form_quantity_label
import invoicer.multiplatform.features.invoice.presentation.generated.resources.invoice_create_activity_form_quantity_placeholder
import invoicer.multiplatform.features.invoice.presentation.generated.resources.invoice_create_activity_form_quantity_support
import invoicer.multiplatform.foundation.design_system.generated.resources.DsResources
import invoicer.multiplatform.foundation.design_system.generated.resources.ic_dollar
import invoicer.multiplatform.foundation.design_system.generated.resources.ic_edit
import invoicer.multiplatform.foundation.design_system.generated.resources.ic_horizontal_tuning
import io.github.monolithic.invoicer.features.invoice.presentation.screens.create.steps.activities.AddActivityFormState
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.button.InkPrimaryButton
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.icon.InkIcon
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.input.InkOutlinedInput
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.sheets.modal.InkModalBottomSheet
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.sheets.modal.props.InkSheetState
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.theme.InkTheme
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun AddActivityBottomSheet(
    modifier: Modifier = Modifier,
    sheetState: InkSheetState,
    isVisible: Boolean,
    formState: AddActivityFormState,
    onDismiss: () -> Unit,
    onChangeDescription: (String) -> Unit,
    onChangeUnitPrice: (String) -> Unit,
    onChangeQuantity: (String) -> Unit,
    onAddActivity: () -> Unit
) {
    InkModalBottomSheet(
        onDismiss = onDismiss,
        sheetState = sheetState,
        modifier = modifier.testTag(AddActivityBottomSheetTestTag.CONTENT),
        isVisible = isVisible
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(InkTheme.spacing.medium),
            verticalArrangement = Arrangement.spacedBy(InkTheme.spacing.medium)
        ) {
            val (descriptionFocus, unitPriceFocus, quantityFocus) = FocusRequester.createRefs()
            val keyboard = LocalSoftwareKeyboardController.current

            InkOutlinedInput(
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(descriptionFocus)
                    .testTag(AddActivityBottomSheetTestTag.FIELD_DESCRIPTION),
                maxLines = 1,
                value = formState.description,
                onValueChange = onChangeDescription,
                keyboardActions = KeyboardActions(
                    onNext = { unitPriceFocus.requestFocus() }
                ),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                    capitalization = KeyboardCapitalization.Sentences
                ),
                placeholder = stringResource(Res.string.invoice_create_activity_form_description_placeholder),
                label = stringResource(Res.string.invoice_create_activity_form_description_label),
                leadingContent = {
                    InkIcon(
                        painter = painterResource(DsResources.drawable.ic_edit),
                        contentDescription = null,
                        tint = InkTheme.colorScheme.onBackground
                    )
                }
            )

            InkOutlinedInput(
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(unitPriceFocus)
                    .testTag(AddActivityBottomSheetTestTag.FIELD_UNIT_PRICE),
                maxLines = 1,
                value = formState.unitPrice,
                onValueChange = { fieldValue ->
                    onChangeUnitPrice(fieldValue.filter { it.isDigit() })
                },
                keyboardActions = KeyboardActions(
                    onNext = { quantityFocus.requestFocus() }
                ),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.NumberPassword
                ),
                placeholder = stringResource(Res.string.invoice_create_activity_form_price_placeholder),
                label = stringResource(Res.string.invoice_create_activity_form_price_label),
                leadingContent = {
                    InkIcon(
                        painter = painterResource(DsResources.drawable.ic_dollar),
                        contentDescription = null,
                        tint = InkTheme.colorScheme.onBackground
                    )
                }
            )

            InkOutlinedInput(
                maxLines = 1,
                value = formState.quantity,
                onValueChange = { fieldValue ->
                    onChangeQuantity(fieldValue.filter { it.isDigit() })
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(quantityFocus)
                    .testTag(AddActivityBottomSheetTestTag.FIELD_QUANTITY),
                keyboardActions = KeyboardActions(
                    onDone = { keyboard?.hide() }
                ),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.NumberPassword
                ),
                label = stringResource(Res.string.invoice_create_activity_form_quantity_label),
                placeholder = stringResource(Res.string.invoice_create_activity_form_quantity_placeholder),
                supportText = stringResource(Res.string.invoice_create_activity_form_quantity_support),
                leadingContent = {
                    InkIcon(
                        painter = painterResource(DsResources.drawable.ic_horizontal_tuning),
                        contentDescription = null,
                        tint = InkTheme.colorScheme.onBackground
                    )
                }
            )

            InkPrimaryButton(
                onClick = onAddActivity,
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag(AddActivityBottomSheetTestTag.SUBMIT_BUTTON),
                enabled = formState.formButtonEnabled,
                text = stringResource(Res.string.invoice_add_activity_cta)
            )
        }
    }
}

internal object AddActivityBottomSheetTestTag {
    const val FIELD_DESCRIPTION = "add_activity_description"
    const val FIELD_UNIT_PRICE = "add_activity_unit_price"
    const val FIELD_QUANTITY = "add_activity_quantity"
    const val CONTENT = "add_activity_content"
    const val SUBMIT_BUTTON = "add_activity_submit_buttom"
}
