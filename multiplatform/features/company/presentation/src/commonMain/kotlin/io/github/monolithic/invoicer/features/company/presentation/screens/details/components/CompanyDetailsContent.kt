package io.github.monolithic.invoicer.features.company.presentation.screens.details.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import invoicer.multiplatform.features.company.presentation.generated.resources.Res
import invoicer.multiplatform.features.company.presentation.generated.resources.company_details_address
import invoicer.multiplatform.features.company.presentation.generated.resources.company_details_address_city
import invoicer.multiplatform.features.company.presentation.generated.resources.company_details_address_country_code
import invoicer.multiplatform.features.company.presentation.generated.resources.company_details_address_line1
import invoicer.multiplatform.features.company.presentation.generated.resources.company_details_address_line2
import invoicer.multiplatform.features.company.presentation.generated.resources.company_details_address_postal_code
import invoicer.multiplatform.features.company.presentation.generated.resources.company_details_address_state
import invoicer.multiplatform.features.company.presentation.generated.resources.company_details_intermediary_account
import invoicer.multiplatform.features.company.presentation.generated.resources.company_details_pay_account
import invoicer.multiplatform.features.company.presentation.generated.resources.company_details_pay_account_bank_address
import invoicer.multiplatform.features.company.presentation.generated.resources.company_details_pay_account_bank_name
import invoicer.multiplatform.features.company.presentation.generated.resources.company_details_pay_account_iban
import invoicer.multiplatform.features.company.presentation.generated.resources.company_details_pay_account_swift
import io.github.monolithic.invoicer.features.company.presentation.screens.details.CompanyDetailsState
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.InkText
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.InkTextStyle
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.theme.InkTheme
import io.github.monolithic.invoicer.foundation.designSystem.ink.public.components.LabeledListItem
import io.github.monolithic.invoicer.foundation.designSystem.ink.public.components.RoundTextAbbreviation
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun CompanyDetailsContent(
    state: CompanyDetailsState,
    onEditAddress: () -> Unit,
    onEditPrimaryAccount: () -> Unit,
    onEditIntermediaryAccount: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(InkTheme.spacing.medium)
    ) {
        RoundTextAbbreviation(
            text = state.name
        )
        InkText(
            text = state.name,
            style = InkTextStyle.Heading5,
            weight = FontWeight.Bold
        )
        AddressSection(
            addressLine1 = state.addressLine1,
            addressLine2 = state.addressLine2,
            state = state.state,
            city = state.city,
            zipCode = state.postalCode,
            country = state.countryCode,
            onEditClick = onEditAddress,
            modifier = Modifier.fillMaxWidth(),
        )
        PaymentSection(
            title = stringResource(Res.string.company_details_pay_account),
            swift = state.payAccount.swift,
            iban = state.payAccount.iban,
            bankAddress = state.payAccount.bankAddress,
            bankName = state.payAccount.bankName,
            onEditClick = onEditPrimaryAccount,
            modifier = Modifier.fillMaxWidth(),
        )

        state.intermediaryAccount?.let {
            PaymentSection(
                title = stringResource(Res.string.company_details_intermediary_account),
                swift = it.swift,
                iban = it.iban,
                bankAddress = it.bankAddress,
                bankName = it.bankName,
                onEditClick = onEditIntermediaryAccount,
                modifier = Modifier.fillMaxWidth(),
            )
        }
    }
}

@Composable
private fun AddressSection(
    addressLine1: String,
    addressLine2: String?,
    state: String,
    city: String,
    zipCode: String,
    country: String,
    onEditClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    CompanyDetailsSection(
        title = stringResource(Res.string.company_details_address),
        onEditClick = onEditClick,
        modifier = modifier,
    ) {
        LabeledListItem(
            label = stringResource(Res.string.company_details_address_line1),
            value = addressLine1
        )

        addressLine2?.let {
            LabeledListItem(
                label = stringResource(Res.string.company_details_address_line2),
                value = it
            )
        }
        LabeledListItem(
            label = stringResource(Res.string.company_details_address_state),
            value = state
        )
        LabeledListItem(
            label = stringResource(Res.string.company_details_address_city),
            value = city
        )
        LabeledListItem(
            label = stringResource(Res.string.company_details_address_postal_code),
            value = zipCode
        )
        LabeledListItem(
            label = stringResource(Res.string.company_details_address_country_code),
            value = country
        )
    }
}

@Composable
private fun PaymentSection(
    title: String,
    swift: String,
    iban: String,
    bankAddress: String,
    bankName: String,
    onEditClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    CompanyDetailsSection(
        title = title,
        onEditClick = onEditClick,
        modifier = modifier,
    ) {
        LabeledListItem(
            label = stringResource(Res.string.company_details_pay_account_swift),
            value = swift
        )
        LabeledListItem(
            label = stringResource(Res.string.company_details_pay_account_iban),
            value = iban
        )
        LabeledListItem(
            label = stringResource(Res.string.company_details_pay_account_bank_name),
            value = bankName
        )
        LabeledListItem(
            label = stringResource(Res.string.company_details_pay_account_bank_address),
            value = bankAddress
        )
    }
}
