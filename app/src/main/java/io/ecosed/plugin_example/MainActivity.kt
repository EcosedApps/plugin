package io.ecosed.plugin_example

import android.os.Bundle
import android.widget.Toast
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
        PluginExecutor.build(application = application) {

            binding.textHello.text = execMethodCall(
                name = ExamplePlugin.channel,
                method = "getText"
            ).toString()

            binding.buttonToast.setOnClickListener {
                execMethodCall(
                    name = ToastPlugin.channel,
                    method = "toast"
                )
            }

            binding.buttonAppName.setOnClickListener {
                execMethodCall(
                    name = DetailPlugin.channel,
                    method = "name"
                )?.let {
                    Toast.makeText(
                        this@MainActivity,
                        it.toString(),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            binding.buttonLaunch.setOnClickListener {
                execMethodCall(
                    name = DetailPlugin.channel,
                    method = "launch"
                )
            }
        }
    }
}