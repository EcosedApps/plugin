package io.ecosed.plugin_example

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.Intent
import android.util.Log
import io.ecosed.plugin.LibEcosedImpl
import io.ecosed.plugin.PluginBinding
import io.ecosed.plugin.PluginChannel

class LEDemo : LibEcosedImpl {

    private lateinit var pluginChannel: PluginChannel

    private lateinit var mActivity: Activity
    private lateinit var mContext: Context

    /**
     * 插件被添加时执行
     */
    override fun onEcosedAdded(binding: PluginBinding) {
        pluginChannel = PluginChannel(binding = binding, channel = channel)
        pluginChannel.getContext()?.let {
            mContext = it
        }
        pluginChannel.getLaunchActivity(this@LEDemo)?.let {
            mActivity = it
        }
        pluginChannel.setMethodCallHandler(handler = this@LEDemo)


        Log.i("LEDemo", "onEcosedAdded")
    }

    /**
     * 插件被移除时执行
     */
    override fun onEcosedRemoved(binding: PluginBinding) {
        pluginChannel.setMethodCallHandler(handler = null)
        Log.i("LEDemo", "onEcosedRemoved")
    }

    /**
     * 执行代码时调用
     */
    override fun onEcosedMethodCall(call: PluginChannel.MethodCall, result: PluginChannel.Result) {
        when (call.method) {
            "launch" -> {
                val intent = Intent(mContext, mActivity.javaClass)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                mContext.startActivity(intent)
            }

            else -> result.notImplemented()
        }
    }

    override fun initSDK(application: Application) {
        super.initSDK(application)
    }

    /**
     * 返回pluginChannel
     */
    override val getPluginChannel: PluginChannel
        get() = pluginChannel

    companion object {
        const val channel: String = "LEDemo"
    }
}