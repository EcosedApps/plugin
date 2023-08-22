package io.ecosed.plugin

import android.app.Activity

/**
 * 作者: wyq0918dev
 * 仓库: https://github.com/ecosed/plugin
 * 时间: 2023/08/22
 * 描述: 应用程序主机
 * 文档: https://github.com/ecosed/plugin/blob/master/README.md
 */
interface EcosedHost {

    /** 获取插件引擎. */
    val getPluginEngine: PluginEngine

    /** 获取框架扩展. */
    val getExtension: EcosedExtension?
        get() = null

    /** 获取LibEcosed框架 - LibEcosed框架专用接口. */
    val getLibEcosed: LibEcosed?
        get() = null

    /** 获取插件列表. */
    val getPluginList: ArrayList<EcosedPlugin>?

    /** 应用启动入口Activity - LibEcosed框架专用接口. */
    val getLaunchActivity: Activity?
        get() = null

    /** 应用设置Activity - LibEcosed框架专用接口. */
    val getSettingsActivity: Activity?
        get() = null

    /** 获取是否调试模式. */
    val isDebug: Boolean
}