package io.ecosed.plugin

import android.app.Application
import android.content.Context
import android.content.ContextWrapper

/**
 * 作者: wyq0918dev
 * 仓库: https://github.com/ecosed/plugin
 * 时间: 2023/08/21
 * 描述: LibEcosed框架接口
 * 文档: https://github.com/ecosed/plugin/blob/master/README.md
 */
abstract class LibEcosed : ContextWrapper(null), EcosedPlugin, PluginChannel.MethodCallHandler {

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
    }

    abstract fun init()

    /** 初始化SDK - LibEcosed框架专用接口. */
    open fun initSDKs(application: Application) = Unit

    open fun initSDKInitialized() = Unit

    internal fun attach(base: Context?) {
        attachBaseContext(base)
    }
}