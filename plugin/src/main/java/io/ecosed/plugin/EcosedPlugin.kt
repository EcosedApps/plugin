package io.ecosed.plugin

interface EcosedPlugin : EcosedMethodCallHandler, EcosedPluginMethodCall {
    fun onEcosedAttached(binding: EcosedPluginBinding)
    fun onEcosedDetached(binding: EcosedPluginBinding)
}