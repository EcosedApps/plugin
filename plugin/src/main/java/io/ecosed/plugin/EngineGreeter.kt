package io.ecosed.plugin

import android.app.Activity
import android.app.Service

class EngineGreeter {

    internal interface Greeter {

        fun getEngine(
            activity: Activity? = null,
            service: Service? = null,
            mode: GreetMode
        ): PluginEngine?
    }

    companion object : Greeter {

        override fun getEngine(
            activity: Activity?,
            service: Service?,
            mode: GreetMode
        ): PluginEngine? {
            when (mode) {
                GreetMode.Activity -> activity?.apply {
                    return if (application is EcosedApplication) {
                        (application as EcosedApplication).getPluginEngine
                    } else {
                        null
                    }
                }

                GreetMode.Service -> service?.apply {
                    return if (application is EcosedApplication) {
                        (application as EcosedApplication).getPluginEngine
                    } else {
                        null
                    }
                }
            }
            return null
        }
    }
}