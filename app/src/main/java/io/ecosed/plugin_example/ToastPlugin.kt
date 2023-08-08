package io.ecosed.plugin_example

import android.content.Context
import android.widget.Toast
import io.ecosed.plugin.EcosedPlugin
import io.ecosed.plugin.EcosedPluginBinding
import io.ecosed.plugin.EcosedPluginMethod
import io.ecosed.plugin.EcosedResult

class ToastPlugin : EcosedPlugin {

    private lateinit var pluginMethod: EcosedPluginMethod
    private lateinit var mContext: Context

    /**
     * 插件被添加时执行
     */
    override fun onEcosedAttached(binding: EcosedPluginBinding) {
        pluginMethod = EcosedPluginMethod(binding = binding, channel = channel)
        mContext = pluginMethod.getActivity()
        pluginMethod.setMethodCallHandler(callBack = this@ToastPlugin)
    }

    /**
     * 插件被移除时执行
     */
    override fun onEcosedDetached(binding: EcosedPluginBinding) {
        pluginMethod.setMethodCallHandler(callBack = null)
    }

    /**
     * 执行代码时调用
     */
    override fun onEcosedMethodCall(call: String, result: EcosedResult) {
        when (call) {
            "toast" -> Toast.makeText(mContext, "Hello World", Toast.LENGTH_SHORT).show()
            else -> result.notImplemented()
        }
    }

    /**
     * 返回EcosedPluginMethod
     */
    override fun getEcosedPluginMethod(): EcosedPluginMethod {
        return pluginMethod
    }

    companion object {
        const val channel: String = "ToastPlugin"
    }
}