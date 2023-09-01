package io.ecosed.plugin_example

import android.app.Application
import io.ecosed.plugin.EcosedApplication
import io.ecosed.plugin.EcosedClient
import io.ecosed.plugin.PluginEngine

class MyApplication : Application(), EcosedApplication {

    private lateinit var engine: PluginEngine

    override fun onCreate() {
        super.onCreate()
        engine = PluginEngine.create(application = this@MyApplication)
    }

    override fun getPluginEngine(): PluginEngine {
        return engine
    }

    override fun getEcosedClient(): EcosedClient {
        return MyClient()
    }
}