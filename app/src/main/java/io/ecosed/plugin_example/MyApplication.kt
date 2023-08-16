package io.ecosed.plugin_example

import android.app.Activity
import android.app.Application
import android.content.Context
import io.ecosed.plugin.EcosedApplication
import io.ecosed.plugin.EcosedHost
import io.ecosed.plugin.EcosedPlugin
import io.ecosed.plugin.PluginEngine
import io.ecosed.plugin.pluginArrayOf

class MyApplication : Application(), EcosedApplication {

    private lateinit var engine: PluginEngine

    private val host: EcosedHost = object : EcosedHost {

        override val getPluginEngine: PluginEngine
            get() = engine

        override val getPluginList: ArrayList<EcosedPlugin>
            get() = pluginArrayOf(ExamplePlugin(), ToastPlugin())

        override val isDebug: Boolean
            get() = BuildConfig.DEBUG

        override fun getLibEcosed(): EcosedPlugin {
            return LEDemo()
        }

        override fun getPackageName(): String {
            return BuildConfig.APPLICATION_ID
        }

        override fun getLaunchActivity(): Activity {
            return MainActivity()
        }
    }

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
    }

    override fun onTerminate() {
        super.onTerminate()
        engine.detach()
    }

    override val getEngineHost: EcosedHost
        get() = host
}