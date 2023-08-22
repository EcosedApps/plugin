package io.ecosed.plugin_example

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.ecosed.plugin.PluginExecutor
import io.ecosed.plugin_example.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 设置布局
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 执行代码
        binding.textHello.text = PluginExecutor.execMethodCall(
            activity = this@MainActivity,
            name = ExamplePlugin.channel,
            method = "getText"
        ).toString()

        binding.buttonToast.setOnClickListener {
            PluginExecutor.execMethodCall(
                activity = this@MainActivity,
                name = ToastPlugin.channel,
                method = "toast"
            )
        }

        Intent(this, MainActivity().javaClass)

        binding.buttonLaunch.setOnClickListener {
            PluginExecutor.execMethodCall(
                activity = this@MainActivity,
                name = LEDemo.channel,
                method = "launch"
            )
        }

        binding.buttonSettings.setOnClickListener {
            PluginExecutor.execMethodCall(
                activity = this@MainActivity,
                name = LEDemo.channel,
                method = "settings"
            )
        }
    }
}