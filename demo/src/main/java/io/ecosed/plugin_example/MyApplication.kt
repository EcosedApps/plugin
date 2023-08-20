package io.ecosed.plugin_example

import android.app.Activity
import android.app.Application
import io.ecosed.plugin.EcosedApplication
import io.ecosed.plugin.EcosedHost
import io.ecosed.plugin.EcosedPlugin
import io.ecosed.plugin.LibEcosed
import io.ecosed.plugin.PluginEngine

class MyApplication : Application(), EcosedApplication {

    private lateinit var engine: PluginEngine

    override fun onCreate() {
        super.onCreate()
        engine = PluginEngine.build(
            application = this@MyApplication,
            isUseHiddenApi = true
        )
        engine.attach()
    }

    override fun onTerminate() {
        super.onTerminate()
        engine.detach()
    }

    override val getEngineHost: EcosedHost
        get() = object : EcosedHost {

            override val getPluginEngine: PluginEngine
                get() = engine

            override val getLibEcosed: LibEcosed
                get() = LEDemo()

            override val getPluginList: ArrayList<EcosedPlugin>
                get() = arrayListOf(ExamplePlugin(), ToastPlugin())

            override val getLaunchActivity: Activity
                get() = MainActivity()

            override val isDebug: Boolean
                get() = BuildConfig.DEBUG
        }
}