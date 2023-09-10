package io.ecosed.plugin_example

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import io.ecosed.plugin.EcosedClient
import io.ecosed.plugin.PluginExecutor
import io.ecosed.plugin_example.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 设置布局
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val bundle = Bundle()
        bundle.putString("text", "Hello from bundle")

        // 执行代码
        binding.textHello.text = PluginExecutor.execMethodCall<String>(
            activity = this@MainActivity,
            name = ExamplePlugin.channel,
            method = "getText",
            bundle = bundle
        )

        binding.buttonToast.setOnClickListener {
            PluginExecutor.execMethodCall<Any>(
                activity = this@MainActivity,
                name = ToastPlugin.channel,
                method = "toast",
                bundle = null
            )
        }

        binding.buttonClient.setOnClickListener {
            Toast.makeText(
                this,
                (PluginExecutor.execMethodCall<EcosedClient>(
                    activity = this@MainActivity,
                    name = LEDemo.channel,
                    method = "client",
                    bundle = null
                ))!!.javaClass.name,
                Toast.LENGTH_SHORT
            ).show()
        }

        binding.logo.setImageDrawable(
            PluginExecutor.execMethodCall<Drawable>(
                activity = this@MainActivity,
                name = LEDemo.channel,
                method = "logo",
                bundle = null
            )
        )
    }
}