package io.ecosed.plugin

/**
 * 作者: wyq0918dev
 * 仓库: https://github.com/ecosed/plugin
 * 时间: 2023/08/15
 * 描述: 应用程序主机
 * 文档: https://github.com/ecosed/plugin/blob/master/README.md
 */
interface EcosedHost {

    /**
     * 获取是否调试模式
     */
    val isDebug: Boolean

    /**
     * 获取插件引擎
     */
    val getPluginEngine: PluginEngine

    /**
     * 获取插件列表
     */
    fun getPluginList(): ArrayList<EcosedPlugin>
}