package io.ecosed.plugin

import android.app.Activity
import android.app.Application
import android.app.Service

/**
 * 作者: wyq0918dev
 * 仓库: https://github.com/ecosed/plugin
 * 时间: 2023/08/21
 * 描述: 插件方法执行器
 * 文档: https://github.com/ecosed/plugin/blob/master/README.md
 */
class PluginExecutor {

    internal interface Executor {

        /**
         * 调用插件代码的方法.
         * @param activity 传入Activity
         * @param name 要调用的插件的通道.
         * @param method 要调用的插件中的方法.
         * @return 返回方法执行后的返回值,类型为Any?.
         */
        fun execMethodCall(
            activity: Activity, name: String, method: String
        ): Any?

        /**
         * 调用插件代码的方法.
         * @param service 传入Service
         * @param name 要调用的插件的通道.
         * @param method 要调用的插件中的方法.
         * @return 返回方法执行后的返回值,类型为Any?.
         */
        fun execMethodCall(
            service: Service, name: String, method: String
        ): Any?

        /**
         * 调用插件代码的方法.
         * @param application 传入Application
         * @param name 要调用的插件的通道.
         * @param method 要调用的插件中的方法.
         * @return 返回方法执行后的返回值,类型为Any?.
         */
        fun execMethodCall(
            application: Application, name: String, method: String
        ): Any?
    }

    companion object : Executor {

        /**
         * 调用插件代码的方法.
         * @param activity 传入Activity
         * @param name 要调用的插件的通道.
         * @param method 要调用的插件中的方法.
         * @return 返回方法执行后的返回值,类型为Any?.
         */
        override fun execMethodCall(
            activity: Activity, name: String, method: String
        ): Any? = when (activity.application) {
            is EcosedApplication -> {
                (activity.application as EcosedApplication).apply {
                    getEngineHost.getPluginEngine.apply {
                        return execMethodCall(name, method)
                    }
                }
            }

            else -> error(
                message = "EcosedApplication接口未实现"
            )
        }

        /**
         * 调用插件代码的方法.
         * @param service 传入Service
         * @param name 要调用的插件的通道.
         * @param method 要调用的插件中的方法.
         * @return 返回方法执行后的返回值,类型为Any?.
         */
        override fun execMethodCall(
            service: Service, name: String, method: String
        ): Any? = when (service.application) {
            is EcosedApplication -> {
                (service.application as EcosedApplication).apply {
                    getEngineHost.getPluginEngine.apply {
                        return execMethodCall(name, method)
                    }
                }
            }

            else -> error(
                message = "EcosedApplication接口未实现"
            )
        }

        /**
         * 调用插件代码的方法.
         * @param application 传入Application
         * @param name 要调用的插件的通道.
         * @param method 要调用的插件中的方法.
         * @return 返回方法执行后的返回值,类型为Any?.
         */
        override fun execMethodCall(
            application: Application, name: String, method: String
        ): Any? = when (application) {
            is EcosedApplication -> {
                (application as EcosedApplication).apply {
                    getEngineHost.getPluginEngine.apply {
                        return execMethodCall(name, method)
                    }
                }
            }

            else -> error(
                message = "EcosedApplication接口未实现"
            )
        }
    }
}