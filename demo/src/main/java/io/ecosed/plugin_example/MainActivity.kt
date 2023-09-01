package io.ecosed.plugin_example

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
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
            method = "getText", null
        ).toString()

        binding.buttonToast.setOnClickListener {
            PluginExecutor.execMethodCall(
                activity = this@MainActivity,
                name = ToastPlugin.channel,
                method = "toast", null
            )
        }

        Intent(this, MainActivity().javaClass)

        binding.buttonLaunch.setOnClickListener {
            Toast.makeText(this, (PluginExecutor.execMethodCall(
                activity = this@MainActivity,
                name = LEDemo.channel,
                method = "main", null
            ) as Fragment).javaClass.name, Toast.LENGTH_SHORT).show()

        }

        binding.logo.setImageDrawable(PluginExecutor.execMethodCall(
            activity = this@MainActivity,
            name = LEDemo.channel,
            method = "logo", null
        ) as Drawable)
    }
}