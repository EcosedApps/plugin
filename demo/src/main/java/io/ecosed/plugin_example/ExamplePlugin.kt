package io.ecosed.plugin_example

import io.ecosed.plugin.EcosedPlugin
import io.ecosed.plugin.PluginBinding
import io.ecosed.plugin.PluginChannel

class ExamplePlugin : EcosedPlugin, PluginChannel.MethodCallHandler {

    private lateinit var pluginChannel: PluginChannel
    private var isDebug: Boolean? = null

    /**
     * 插件被添加时执行
     */
    override fun onEcosedAdded(binding: PluginBinding) {
        pluginChannel = PluginChannel(binding = binding, channel = channel)
        isDebug = pluginChannel.isDebug()

        pluginChannel.setMethodCallHandler(handler = this@ExamplePlugin)
    }

    /**
     * 执行代码时调用
     */
    override fun onEcosedMethodCall(call: PluginChannel.MethodCall, result: PluginChannel.Result) {
        when (call.method) {
            "getText" -> result.success(call.objects?.getString("text"))
            else -> result.notImplemented()
        }
    }

    /**
     * 返回pluginChannel
     */
    override val getPluginChannel: PluginChannel
        get() = pluginChannel

    companion object {
        const val channel: String = "ExamplePlugin"
    }
}