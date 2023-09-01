package io.ecosed.plugin_example

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import io.ecosed.plugin.EcosedClient
import io.ecosed.plugin.PluginExecutor
import io.ecosed.plugin_example.databinding.ActivityMainBinding
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 设置布局
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val obj = JSONObject()
        obj.put("text", "Hello From JSON")



        // 执行代码
        binding.textHello.text = PluginExecutor.execMethodCall(
            activity = this@MainActivity,
            name = ExamplePlugin.channel,
            method = "getText",
            objects = obj
        ).toString()

        binding.buttonToast.setOnClickListener {
            PluginExecutor.execMethodCall(
                activity = this@MainActivity,
                name = ToastPlugin.channel,
                method = "toast",
                objects = null
            )
        }

        binding.buttonClient.setOnClickListener {
            Toast.makeText(
                this,
                (PluginExecutor.execMethodCall(
                    activity = this@MainActivity,
                    name = LEDemo.channel,
                    method = "client",
                    objects = null
                ) as EcosedClient).javaClass.name,
                Toast.LENGTH_SHORT
            ).show()
        }

        binding.logo.setImageDrawable(
            PluginExecutor.execMethodCall(
                activity = this@MainActivity,
                name = LEDemo.channel,
                method = "logo",
                objects = null
            ) as Drawable
        )
    }
}