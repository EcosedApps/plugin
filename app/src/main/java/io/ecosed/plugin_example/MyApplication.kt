package io.ecosed.plugin_example

import android.app.Activity
import android.app.Application
import android.content.Context
import io.ecosed.plugin.BuildConfig
import io.ecosed.plugin.EcosedApplication
import io.ecosed.plugin.EcosedHost
import io.ecosed.plugin.EcosedPlugin
import io.ecosed.plugin.PluginEngine
import io.ecosed.plugin.pluginArrayOf

class MyApplication : Application(), EcosedApplication {

    private lateinit var engine: PluginEngine

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base).run {
            engine = PluginEngine.build(
                baseContext = base,
                application = this@MyApplication
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
        get() = object : EcosedHost {
            override val isDebug: Boolean
                get() = BuildConfig.DEBUG

            override val getPluginEngine: PluginEngine
                get() = engine

            override fun getPluginList(): ArrayList<EcosedPlugin> {
                return pluginArrayOf(ExamplePlugin(), ToastPlugin())
            }
        }
}