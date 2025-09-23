package io.github.monolithic.invoicer.foundation.designSystem.components

import io.github.monolithic.invoicer.foundation.designSystem.configs.InvoicerPaparazziConfig
import io.github.monolithic.invoicer.foundation.designSystem.configs.invoicerSnapshot
import io.github.monolithic.invoicer.foundation.designSystem.legacy.components.ScreenTitle
import org.junit.Rule
import org.junit.Test

class ScreenTitleScreenshotTest {
    @get:Rule
    val paparazzi = InvoicerPaparazziConfig

    @Test
    fun titleAndSubtitle() {
        paparazzi.invoicerSnapshot {
            ScreenTitle(
                title = "Title",
                subTitle = "Subtitle"
            )
        }
    }

    @Test
    fun titleOnly() {
        paparazzi.invoicerSnapshot {
            ScreenTitle(
                title = "Title",
                subTitle = null
            )
        }
    }
}
