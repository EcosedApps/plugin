package io.ecosed.plugin_example

import android.app.Activity
import android.content.Context
import android.content.Intent
import io.ecosed.plugin.EcosedPlugin
import io.ecosed.plugin.PluginBinding
import io.ecosed.plugin.PluginChannel

class DetailPlugin : EcosedPlugin, PluginChannel.MethodCallHandler {

    private lateinit var pluginChannel: PluginChannel

    private lateinit var appName: String
    private lateinit var launchActivity: Activity
    private lateinit var mContext: Context

    override fun onEcosedAdded(binding: PluginBinding) {
        pluginChannel = PluginChannel(binding = binding, channel = channel)
        pluginChannel.getAppName()?.let {
            appName = it
        }
        pluginChannel.getLaunchActivity()?.let {
            launchActivity = it
        }
        mContext = pluginChannel.getApplicationContext()


        pluginChannel.setMethodCallHandler(handler = this@DetailPlugin)
    }

    override fun onEcosedRemoved(binding: PluginBinding) {
        pluginChannel.setMethodCallHandler(handler = null)
    }

    override fun onEcosedMethodCall(call: PluginChannel.MethodCall, result: PluginChannel.Result) {
        when (call.method) {
            "name" -> result.success(appName)
            "launch" -> {
                val intent = Intent(mContext, launchActivity.javaClass)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                mContext.startActivity(intent)
            }
            else -> result.notImplemented()
        }
    }

    override val getPluginChannel: PluginChannel
        get() = pluginChannel

    companion object {
        const val channel: String = "DetailPlugin"
    }
}