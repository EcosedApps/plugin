package io.ecosed.plugin_example

import android.app.Activity
import android.app.Application
import io.ecosed.plugin.EcosedApplication
import io.ecosed.plugin.EcosedHost
import io.ecosed.plugin.EcosedPlugin
import io.ecosed.plugin.LibEcosedImpl
import io.ecosed.plugin.PluginEngine
import io.ecosed.plugin.PluginExecutor
import io.ecosed.plugin.pluginArrayOf

class MyApplication : Application(), EcosedApplication {

    private lateinit var engine: PluginEngine

    override fun onCreate() {
        super.onCreate()
        engine = PluginEngine.build(
            application = this@MyApplication,
            isUseHiddenApi = true
        )
        engine.attach()


        PluginExecutor.execMethodCall(
            application = this@MyApplication,
            name = ExamplePlugin.channel,
            method = "getText")
    }

    override fun onTerminate() {
        super.onTerminate()
        engine.detach()
    }

    override val getEngineHost: EcosedHost
        get() = object : EcosedHost {

            override val getPluginEngine: PluginEngine
                get() = engine

            override val getLibEcosed: LibEcosedImpl
                get() = LEDemo()

            override val getPluginList: ArrayList<EcosedPlugin>
                get() = pluginArrayOf(ExamplePlugin(), ToastPlugin())

            override val getLaunchActivity: Activity
                get() = MainActivity()

            override val isDebug: Boolean
                get() = BuildConfig.DEBUG
        }
}