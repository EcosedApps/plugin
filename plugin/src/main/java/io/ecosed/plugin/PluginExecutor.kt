package io.ecosed.plugin

import android.app.Application

class PluginExecutor {

    internal interface Executor {

        fun build(content: Companion.() -> Unit)

        fun execMethodCall(
            application: Application,
            name: String,
            method: String
        ): Any?

    }

    companion object : Executor {

        override fun build(content: Companion.() -> Unit) {
            return content(Companion)
        }

        override fun execMethodCall(application: Application, name: String, method: String): Any? {
            var result: Any? = null

            if (application is EcosedApplication) {
                result = (application as EcosedApplication).getEngineHost.getPluginEngine().execMethodCall(name, method)
            } else {

            }

            return result
        }
    }
}