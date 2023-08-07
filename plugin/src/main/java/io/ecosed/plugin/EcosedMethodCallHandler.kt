package io.ecosed.plugin

interface EcosedMethodCallHandler {
    fun onEcosedMethodCall(call: String, result: EcosedResult)
}