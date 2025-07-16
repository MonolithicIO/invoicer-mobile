package io.github.monolithic.invoicer.foundation.navigation.extensions

import cafe.adriel.voyager.core.registry.ScreenRegistry
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.Navigator
import io.github.monolithic.invoicer.foundation.navigation.InvoicerScreen

/**
 * Push screen to the front of the stack removing previous screens of the same type
 * */
inline fun <reified T : Screen> Navigator.pushToFront(destination: T) {
    val popOldInstances = items.filter { it !is T }
    val newStack = popOldInstances + destination
    replaceAll(newStack)
}

fun getScreen(screen: InvoicerScreen): Screen {
    return ScreenRegistry.get(screen)
}
