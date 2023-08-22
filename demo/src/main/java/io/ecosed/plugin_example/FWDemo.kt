package io.ecosed.plugin_example

import io.ecosed.plugin.EcosedExtension
import io.ecosed.plugin.PluginBinding
import io.ecosed.plugin.PluginChannel

class FWDemo : EcosedExtension {

    private lateinit var pluginChannel: PluginChannel

    override fun onEcosedAdded(binding: PluginBinding) {
        pluginChannel = PluginChannel(binding, channel)
        pluginChannel.setMethodCallHandler(this)
    }

    override fun onEcosedRemoved(binding: PluginBinding) {
        pluginChannel.setMethodCallHandler(null)
    }

    override val getPluginChannel: PluginChannel
        get() = pluginChannel

    override fun onEcosedMethodCall(call: PluginChannel.MethodCall, result: PluginChannel.Result) {
        when (call.method){
            "" -> result.success("")
            else -> result.notImplemented()
        }
    }

    companion object {
        const val channel: String = "FWDemo"
    }
}