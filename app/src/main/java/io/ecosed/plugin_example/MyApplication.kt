package io.ecosed.plugin_example

import android.app.Activity
import android.app.Application
import android.content.Context
import io.ecosed.plugin.EcosedApplication
import io.ecosed.plugin.EcosedHost
import io.ecosed.plugin.EcosedPlugin
import io.ecosed.plugin.LibEcosed
import io.ecosed.plugin.PluginEngine
import io.ecosed.plugin.execMethodCall
import io.ecosed.plugin.pluginArrayOf

class MyApplication : Application(), EcosedApplication {

    private lateinit var engine: PluginEngine

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base).run {
            engine = PluginEngine.build(
                baseContext = base,
                application = this@MyApplication,
                isUseHiddenApi = true
            )
        }
    }

    override fun onCreate() {
        super.onCreate()
        engine.attach()


        execMethodCall(
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

            override val getLibEcosed: LibEcosed
                get() = LEDemo()

            override val getPluginList: ArrayList<EcosedPlugin>
                get() = pluginArrayOf(ExamplePlugin(), ToastPlugin())

            override val getLaunchActivity: Activity
                get() = MainActivity()

            override val isDebug: Boolean
                get() = BuildConfig.DEBUG

            override val getPackageName: String
                get() = BuildConfig.APPLICATION_ID
        }
}