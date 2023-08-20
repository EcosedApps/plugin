package io.ecosed.plugin

import android.app.Application

interface LibEcosedImpl : EcosedPlugin, PluginChannel.MethodCallHandler {

    /** 初始化SDK - LibEcosed框架专用接口 */
    fun initSDK(application: Application) = Unit
}