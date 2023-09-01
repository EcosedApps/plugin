package io.ecosed.plugin_example

import android.app.Application
import android.content.Context
import android.graphics.drawable.Drawable
import android.util.Log
import io.ecosed.plugin.EcosedClient
import io.ecosed.plugin.LibEcosed
import io.ecosed.plugin.PluginBinding
import io.ecosed.plugin.PluginChannel

class LEDemo : LibEcosed() {

    private lateinit var pluginChannel: PluginChannel

    private lateinit var mClient: EcosedClient
    private lateinit var mLogo: Drawable
    private lateinit var mContext: Context

    override fun init() {
        Log.d("LibEcosed", "init()")
    }

    override fun initSDKs(application: Application) {
        super.initSDKs(application)
        Log.d("LibEcosed", "initSDKs()")
    }

    override fun initSDKInitialized() {
        super.initSDKInitialized()
        Log.d("LibEcosed", "initSDKInitialized()")
    }

    /**
     * 插件被添加时执行
     */
    override fun onEcosedAdded(binding: PluginBinding) {
        pluginChannel = PluginChannel(binding = binding, channel = channel)
        pluginChannel.getContext()?.let {
            mContext = it
        }
        pluginChannel.getClient(this@LEDemo)?.let {
            mClient = it
        }
        pluginChannel.getProductLogo(this@LEDemo)?.let {
            mLogo = it
        }
        pluginChannel.setMethodCallHandler(handler = this@LEDemo)


        Log.i("LEDemo", "onEcosedAdded")
    }

    /**
     * 执行代码时调用
     */
    override fun onEcosedMethodCall(call: PluginChannel.MethodCall, result: PluginChannel.Result) {
        when (call.method) {
            "client" -> result.success(mClient)
            "logo" -> result.success(mLogo)
            else -> result.notImplemented()
        }
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