package io.ecosed.plugin_example

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.ecosed.plugin.EcosedPluginEngine
import io.ecosed.plugin.PluginEngineBuilder
import io.ecosed.plugin_example.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var engine: EcosedPluginEngine

    /**
     * Activity创建时调用
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 设置布局
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // 初始化框架引擎
        engine = PluginEngineBuilder().init(
            activity = this@MainActivity
        ).build()
        engine.attach()
        // 添加插件
        engine.addPlugin(plugin = ExamplePlugin())
        engine.addPlugin(plugin = ToastPlugin())
        // 执行代码
        binding.textHello.text = engine.execMethodCall(
            channel = "ExamplePlugin",
            call = "getText"
        ).toString()
        engine.execMethodCall(
            channel = "ToastPlugin",
            call = "toast"
        )
    }


    /**
     * Activity销毁时调用
     */
    override fun onDestroy() {
        super.onDestroy()
        // 移除插件
        engine.removePlugin(plugin = ExamplePlugin())
        engine.removePlugin(plugin = ToastPlugin())
        engine.detach()
    }
}