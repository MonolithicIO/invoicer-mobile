package io.github.monolithic.invoicer.features.qrcodeSession.presentation.screens.scan

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import invoicer.multiplatform.features.qrcode_session.generated.resources.Res
import invoicer.multiplatform.features.qrcode_session.generated.resources.qrcode_scan_in_progress
import invoicer.multiplatform.features.qrcode_session.generated.resources.qrcode_scan_title
import io.github.monolithic.invoicer.features.qrcodeSession.presentation.screens.confirmation.AuthorizationConfirmationScreen
import io.github.monolithic.invoicer.features.qrcodeSession.presentation.screens.scan.components.QrCodeCameraView
import io.github.monolithic.invoicer.foundation.designSystem.legacy.components.LoadingState
import io.github.monolithic.invoicer.foundation.designSystem.legacy.components.buttons.BackButton
import io.github.monolithic.invoicer.foundation.utils.compose.FlowCollectEffect
import io.github.monolithic.invoicer.foundation.utils.permissions.PermissionType
import io.github.monolithic.invoicer.foundation.utils.permissions.checkPermission
import io.github.monolithic.invoicer.foundation.utils.permissions.rememberPermissionRequester
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.stringResource

internal class AuthorizationScanScreen : Screen {

    @Composable
    override fun Content() {
        val screenModel = koinScreenModel<AuthorizationScanScreenModel>()
        val state by screenModel.state.collectAsState()
        val snackBarHost = remember { SnackbarHostState() }
        val scope = rememberCoroutineScope()
        val navigator = LocalNavigator.current

        val hasCameraPermission = checkPermission(PermissionType.CAMERA)
        val cameraPermissionState = remember { mutableStateOf(hasCameraPermission) }
        val permissionRequester = rememberPermissionRequester { result ->
            cameraPermissionState.value =
                result.isGranted && result.permissionType == PermissionType.CAMERA
        }

        StateContent(
            state = state,
            snackBarHostState = snackBarHost,
            onBackButton = { navigator?.pop() },
            onScanSuccess = {
                screenModel.onScanSuccess(it)
            },
            onScanFailure = {
                screenModel.onScanError()
            },
            isCameraPermissionGranted = cameraPermissionState.value,
            onRequestCameraPermission = {
                scope.launch {
                    permissionRequester.requestPermissionDialog(PermissionType.CAMERA)
                }
            }
        )

        FlowCollectEffect(
            flow = screenModel.events,
            key = screenModel
        ) { event ->
            when (event) {
                AuthorizationScanEvents.InvalidCode, AuthorizationScanEvents.CodeNotFound ->
                    scope.launch {
                        snackBarHost.showSnackbar(message = "Invalid QrCode")
                    }

                is AuthorizationScanEvents.ProceedToConfirmation -> navigator?.push(
                    AuthorizationConfirmationScreen(
                        codeContentId = event.contentId
                    )
                )
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun StateContent(
        state: AuthorizationScanState,
        snackBarHostState: SnackbarHostState,
        isCameraPermissionGranted: Boolean,
        onRequestCameraPermission: () -> Unit,
        onBackButton: () -> Unit,
        onScanSuccess: (String) -> Unit,
        onScanFailure: (Throwable) -> Unit,
    ) {
        Scaffold(
            snackbarHost = {
                SnackbarHost(snackBarHostState)
            },
            topBar = {
                TopAppBar(
                    title = { Text(stringResource(Res.string.qrcode_scan_title)) },
                    navigationIcon = { BackButton(onBackClick = onBackButton) }
                )
            }
        ) { scaffoldPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(scaffoldPadding)
            ) {
                when (state.screenType) {
                    AuthorizationScanMode.Loading -> LoadingState(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth(),
                        label = stringResource(Res.string.qrcode_scan_in_progress)
                    )

                    AuthorizationScanMode.CameraView ->
                        if (isCameraPermissionGranted)
                            QrCodeCameraView(
                                modifier = Modifier
                                    .weight(1f)
                                    .fillMaxWidth(),
                                onScan = onScanSuccess,
                                onFail = onScanFailure
                            )
                        else Button(onClick = onRequestCameraPermission) {
                            Text("Request camera permission")
                        }
                }
            }
        }
    }
}
