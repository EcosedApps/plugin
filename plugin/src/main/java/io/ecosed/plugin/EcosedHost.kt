package io.ecosed.plugin

import android.app.Activity

interface EcosedHost {

    /**
     * 获取是否调试模式
     */
    fun isDebug(): Boolean

    /**
     * 获取插件引擎
     */
    fun getPluginEngine(): PluginEngine

    /**
     * 获取插件列表
     */
    fun getPluginList(): ArrayList<EcosedPlugin>

    /**
     * 获取应用名称
     */
    fun getAppName(): String? = null

    /**
     * 获取启动应用的Activity
     */
    fun getLaunchActivity(): Activity? = null
}