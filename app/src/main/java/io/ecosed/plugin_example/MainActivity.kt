package io.ecosed.plugin_example

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.ecosed.plugin.EngineGreeter
import io.ecosed.plugin.GreetMode
import io.ecosed.plugin.PluginEngine
import io.ecosed.plugin_example.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var engine: PluginEngine

    /**
     * Activity创建时调用
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 设置布局
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        // 初始化框架引擎
//        engine = PluginEngine.build(context = this@MainActivity)
//        engine.attach()
//        // 添加插件
//        engine.addPlugin(
//            ExamplePlugin(),
//            ToastPlugin()
//        )


        engine = EngineGreeter.getEngine(
            activity = this@MainActivity,
            mode = GreetMode.Activity
        )!!

        // 执行代码
        binding.textHello.text = engine.execMethodCall(
            name = ExamplePlugin.channel,
            method = "getText"
        ).toString()

        engine.execMethodCall(
            name = ToastPlugin.channel,
            method = "toast"
        )


    }

//    /**
//     * Activity销毁时调用
//     */
//    override fun onDestroy() {
//        super.onDestroy()
//        // 移除插件
//        engine.removePlugin(
//            ExamplePlugin(),
//            ToastPlugin()
//        )
//        engine.detach()
//    }


}