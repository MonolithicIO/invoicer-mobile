package io.github.monolithic.invoicer.foundation.utils.permissions

import androidx.compose.runtime.Composable

@Composable
actual fun rememberPermissionRequester(
    onResult: (PermissionResult) -> Unit
): PermissionRequester {
    return IosPermissionRequester()
}

@Composable
actual fun checkPermission(permissionType: PermissionType): Boolean {
    return true
}

internal class IosPermissionRequester : PermissionRequester {
    override suspend fun requestPermissionDialog(
        permissionType: PermissionType,
    ) = Unit
}
