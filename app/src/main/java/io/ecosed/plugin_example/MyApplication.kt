package io.ecosed.plugin_example

import android.app.Application
import android.content.Context
import io.ecosed.plugin.EcosedApplication
import io.ecosed.plugin.PluginEngine

class MyApplication : Application(), EcosedApplication {

    private lateinit var engine: PluginEngine

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        engine = PluginEngine.build(base = base)
    }



    override fun onCreate() {
        super.onCreate()
        // 引擎函数标准执行顺序
        // attach -> addPlugin -> 正常执行代码 -> removePlugin -> detach
        engine.attach()
        engine.addPlugin(ExamplePlugin(), ToastPlugin())
    }

    override fun onTerminate() {
        super.onTerminate()
        engine.removePlugin(ExamplePlugin(), ToastPlugin())
        engine.detach()
    }

    override val getPluginEngine: PluginEngine
        get() = engine
}