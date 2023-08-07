package io.ecosed.plugin

import android.app.Activity

class PluginEngineBuilder {

    private lateinit var mActivity: Activity

    fun init(activity: Activity): PluginEngineBuilder {
        this@PluginEngineBuilder.mActivity = activity
        return this@PluginEngineBuilder
    }

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