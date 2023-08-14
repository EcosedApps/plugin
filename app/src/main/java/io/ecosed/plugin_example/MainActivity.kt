package io.ecosed.plugin_example

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
        PluginExecutor.build {

            binding.textHello.text = execMethodCall(
                application = application,
                name = ExamplePlugin.channel,
                method = "getText"
            ).toString()

            binding.buttonToast.setOnClickListener {
                execMethodCall(
                    application = application,
                    name = ToastPlugin.channel,
                    method = "toast"
                )
            }
        }
    }
}