package io.ecosed.plugin

interface EcosedResult {
    fun success(result: Any?)
    fun notImplemented()
}