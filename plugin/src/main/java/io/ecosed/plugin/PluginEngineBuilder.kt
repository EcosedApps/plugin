package io.ecosed.plugin

import android.app.Activity

/**
 * 作者: wyq0918dev
 * 仓库: https://github.com/ecosed/EcosedPlugin
 * 时间: 2023/08/08
 * 描述: 插件引擎构建器
 * 文档: https://github.com/ecosed/EcosedPlugin/blob/master/README.md
 */
class PluginEngineBuilder {

    private lateinit var mActivity: Activity

    /**
     * 初始化构建器
     * @param activity 传入Activity
     */
    fun init(activity: Activity): PluginEngineBuilder {
        this.mActivity = activity
        return this@PluginEngineBuilder
    }

    /**
     * 构建插件引擎
     */
    fun build(
        content: (
            plugin: EcosedPluginEngine
        ) -> EcosedPluginEngine = { plugin ->
            plugin
        }
    ): EcosedPluginEngine {
        content(
            EcosedPluginEngine(
                activity = mActivity
            )
        ).let { client ->
            return@build client
        }
    }
}