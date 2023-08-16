package io.ecosed.plugin

import android.app.Activity

/**
 * 作者: wyq0918dev
 * 仓库: https://github.com/ecosed/plugin
 * 时间: 2023/08/15
 * 描述: 应用程序主机
 * 文档: https://github.com/ecosed/plugin/blob/master/README.md
 */
interface EcosedHost {

    /**
     * 获取插件引擎
     */
    val getPluginEngine: PluginEngine

    /**
     * 获取插件列表
     */
    val getPluginList: ArrayList<EcosedPlugin>?

    /**
     * 获取是否调试模式
     */
    val isDebug: Boolean

    /**
     * 传入LibEcosed框架入口
     * LibEcosed框架专用接口
     */
    fun getLibEcosed(): EcosedPlugin? = null

    /**
     * 传入应用包名
     * LibEcosed框架专用接口
     */
    fun getPackageName(): String? = null

    /**
     * 设置应用启动入口Activity
     * LibEcosed框架专用接口
     */
    fun getLaunchActivity(): Activity? = null
}