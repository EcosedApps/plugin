package io.ecosed.plugin

import android.app.Application

/**
 * 作者: wyq0918dev
 * 仓库: https://github.com/ecosed/plugin
 * 时间: 2023/08/21
 * 描述: LibEcosed框架接口
 * 文档: https://github.com/ecosed/plugin/blob/master/README.md
 */
interface LibEcosedImpl : EcosedPlugin, PluginChannel.MethodCallHandler {

    /** 初始化SDK - LibEcosed框架专用接口 */
    fun initSDK(application: Application) = Unit
}