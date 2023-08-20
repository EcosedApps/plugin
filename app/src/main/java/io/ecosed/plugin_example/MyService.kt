package io.ecosed.plugin_example

import android.app.Service
import android.content.Intent
import android.os.IBinder
import io.ecosed.plugin.PluginExecutor

class MyService : Service() {

    override fun onBind(intent: Intent): IBinder? {


        PluginExecutor.execMethodCall(
            service = this@MyService,
            name = ExamplePlugin.channel,
            method = "getText"
        )

        return null
    }
}