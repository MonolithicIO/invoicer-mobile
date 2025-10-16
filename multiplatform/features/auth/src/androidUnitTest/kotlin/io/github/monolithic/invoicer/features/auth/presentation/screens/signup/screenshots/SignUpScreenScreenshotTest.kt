package io.github.monolithic.invoicer.features.auth.presentation.screens.signup.screenshots

import androidx.compose.runtime.Composable
import app.cash.paparazzi.Paparazzi
import io.github.monolithic.invoicer.features.auth.presentation.screens.signup.SignUpCallbacks
import io.github.monolithic.invoicer.features.auth.presentation.screens.signup.SignUpScreen
import io.github.monolithic.invoicer.features.auth.presentation.screens.signup.SignUpScreenState
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.snackbar.props.InkSnackBarHostState
import io.github.monolithic.invoicer.foundation.designSystem.ink.public.theme.InvoicerInkTheme
import io.github.monolithic.invoicer.foundation.utils.snapshot.MultiplatformSnapshot
import org.junit.Rule
import org.junit.Test

class SignUpScreenScreenshotTest {

    @get:Rule
    val paparazzi = Paparazzi(
        maxPercentDifference = 0.01,
    )

    @Test
    fun signUpScreen_default() {
        paparazzi.snapshot {
            TestContent(
                state = SignUpScreenState()
            )
        }
    }

    @Test
    fun signUpScreen_fieldsFilled() {
        paparazzi.snapshot {
            TestContent(
                state = SignUpScreenState(
                    email = "johndona@gmail.com",
                    password = "123123123"
                )
            )
        }
    }

    @Test
    fun signUpScreen_passwordVisible() {
        paparazzi.snapshot {
            TestContent(
                state = SignUpScreenState(
                    email = "johndona@gmail.com",
                    password = "123123123",
                    censored = false
                )
            )
        }
    }

    @Test
    fun signUpScreen_passwordWeak() {
        paparazzi.snapshot {
            TestContent(
                state = SignUpScreenState(
                    email = "johndona@gmail.com",
                    password = "123123123",
                )
            )
        }
    }

    @Test
    fun signUpScreen_passwordStrong() {
        paparazzi.snapshot {
            TestContent(
                state = SignUpScreenState(
                    email = "johndona@gmail.com",
                    password = "123123123",
                )
            )
        }
    }

    @Test
    fun signUpScreen_requestLoading() {
        paparazzi.snapshot {
            TestContent(
                state = SignUpScreenState(
                    email = "johndona@gmail.com",
                    password = "123123123",
                    requestLoading = true
                )
            )
        }
    }

    @Test
    fun signUpScreen_dialogUp() {
        paparazzi.snapshot {
            TestContent(
                state = SignUpScreenState(),
                showDuplicateAccountDialog = true
            )
        }
    }

    @Composable
    private fun TestContent(
        state: SignUpScreenState,
        showDuplicateAccountDialog: Boolean = false
    ) {
        MultiplatformSnapshot {


            InvoicerInkTheme {
                SignUpScreen().StateContent(
                    state = state,
                    snackBarState = InkSnackBarHostState(),
                    callbacks = SignUpCallbacks(
                        onEmailChange = { },
                        onPasswordChange = { },
                        toggleCensorship = { },
                        onBackClick = { },
                        onSubmitClick = { },
                        onSignInClick = { },
                        onDismissDialog = { },
                    ),
                    showDuplicateAccountDialog = showDuplicateAccountDialog
                )
            }
        }
    }
}
