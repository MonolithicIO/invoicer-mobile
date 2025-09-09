package io.github.monolithic.invoicer.foundation.designSystem.components

import io.github.monolithic.invoicer.foundation.designSystem.configs.InvoicerPaparazziConfig
import io.github.monolithic.invoicer.foundation.designSystem.configs.invoicerSnapshot
import io.github.monolithic.invoicer.foundation.designSystem.legacy.theme.InvoicerTheme
import io.github.monolithic.invoicer.foundation.testUtil.MultiplatformSnapshot
import org.junit.Rule
import kotlin.test.Test

class DialogScreenshotTests {

    @get:Rule
    val paparazzi = InvoicerPaparazziConfig

    @Test
    fun dialogVariantError() {
        paparazzi.invoicerSnapshot {
            MultiplatformSnapshot {
                InvoicerTheme {
                    InvoicerDialog(
                        onDismiss = {},
                        variant = DialogVariant.Error,
                        title = "Error",
                        description = "This is an error message"
                    )
                }
            }
        }
    }
}
