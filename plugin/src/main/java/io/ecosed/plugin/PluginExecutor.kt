package io.ecosed.plugin

import android.app.Application

class PluginExecutor {

    internal interface Executor {

        fun build(application: Application, content: Companion.() -> Unit)

        fun execMethodCall(
            name: String,
            method: String
        ): Any?

    }

    companion object : Executor {

        private lateinit var mApplication: Application

        override fun build(application: Application, content: Companion.() -> Unit) {
            mApplication = application
            return content(Companion)
        }

        override fun execMethodCall(name: String, method: String): Any? {
            var result: Any? = null

            if (mApplication is EcosedApplication) {
                result = (mApplication as EcosedApplication).getEngineHost.getPluginEngine().execMethodCall(name, method)
            } else {

            }

            return result
        }
    }
}