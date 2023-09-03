package io.ecosed.plugin

import android.app.Activity
import android.app.Application
import android.app.Service
import android.os.Bundle
import androidx.fragment.app.Fragment

/**
 * 作者: wyq0918dev
 * 仓库: https://github.com/ecosed/plugin
 * 时间: 2023/09/02
 * 描述: 插件方法执行器
 * 文档: https://github.com/ecosed/plugin/blob/master/README.md
 */
class PluginExecutor {

    /**
     * 执行器接口.
     */
    internal interface Executor {

        /**
         * 调用插件代码的方法.
         * @param activity 传入Activity.
         * @param name 要调用的插件的通道.
         * @param method 要调用的插件中的方法.
         * @param bundle 通过Bundle传递参数.
         * @return 返回方法执行后的返回值,类型为Any?.
         */
        fun <T> execMethodCall(
            activity: Activity,
            name: String,
            method: String,
            bundle: Bundle?
        ): T?

        /**
         * 调用插件代码的方法.
         * @param fragment 传入Fragment.
         * @param name 要调用的插件的通道.
         * @param method 要调用的插件中的方法.
         * @param bundle 通过Bundle传递参数.
         * @return 返回方法执行后的返回值,类型为Any?.
         */
        fun execMethodCall(
            fragment: Fragment,
            name: String,
            method: String,
            bundle: Bundle?
        ): Any?

        /**
         * 调用插件代码的方法.
         * @param service 传入Service.
         * @param name 要调用的插件的通道.
         * @param method 要调用的插件中的方法.
         * @param bundle 通过Bundle传递参数.
         * @return 返回方法执行后的返回值,类型为Any?.
         */
        fun execMethodCall(
            service: Service,
            name: String,
            method: String,
            bundle: Bundle?
        ): Any?

        /**
         * 调用插件代码的方法.
         * @param application 传入Application.
         * @param name 要调用的插件的通道.
         * @param method 要调用的插件中的方法.
         * @param bundle 通过Bundle传递参数.
         * @return 返回方法执行后的返回值,类型为Any?.
         */
        fun execMethodCall(
            application: Application,
            name: String,
            method: String,
            bundle: Bundle?
        ): Any?

        /**
         * 调用插件代码的方法.
         * @param client 传入EcosedClient.
         * @param name 要调用的插件的通道.
         * @param method 要调用的插件中的方法.
         * @param bundle 通过Bundle传递参数.
         * @return 返回方法执行后的返回值,类型为Any?.
         */
        fun execMethodCall(
            client: EcosedClient,
            name: String,
            method: String,
            bundle: Bundle?
        ): Any?
    }

    companion object : Executor {

        /** 错误信息 */
        private const val errorMessage: String = "错误:EcosedApplication接口未实现.\n" +
                "提示1:可能未在应用的Application全局类实现EcosedApplication接口.\n" +
                "提示2:应用的Application全局类可能未在AndroidManifest.xml中注册."

        /**
         * 调用插件代码的方法.
         * @param activity 传入Activity.
         * @param name 要调用的插件的通道.
         * @param method 要调用的插件中的方法.
         * @param bundle 通过Bundle传递参数.
         * @return 返回方法执行后的返回值,类型为Any?.
         */
        override fun <T: Any?> execMethodCall(
            activity: Activity,
            name: String,
            method: String,
            bundle: Bundle?
        ): T? {
            if (activity.application is EcosedApplication){
                (activity.application as EcosedApplication).apply {
                    return getPluginEngine().execMethodCall<T>(
                        name = name,
                        method = method,
                        bundle = bundle
                    )
                }
            } else error(
                message = errorMessage
            )
        }

        /**
         * 调用插件代码的方法.
         * @param fragment 传入Fragment.
         * @param name 要调用的插件的通道.
         * @param method 要调用的插件中的方法.
         * @param bundle 通过Bundle传递参数.
         * @return 返回方法执行后的返回值,类型为Any?.
         */
        override fun execMethodCall(
            fragment: Fragment,
            name: String,
            method: String,
            bundle: Bundle?
        ): Any? = when (fragment.requireActivity().application) {
            is EcosedApplication -> {
                (fragment.requireActivity().application as EcosedApplication).apply {
                    getPluginEngine().apply {
                        return execMethodCall(
                            name = name,
                            method = method,
                            bundle = bundle
                        )
                    }
                }
            }

            else -> error(
                message = errorMessage
            )
        }

        /**
         * 调用插件代码的方法.
         * @param service 传入Service.
         * @param name 要调用的插件的通道.
         * @param method 要调用的插件中的方法.
         * @param bundle 通过Bundle传递参数.
         * @return 返回方法执行后的返回值,类型为Any?.
         */
        override fun execMethodCall(
            service: Service,
            name: String,
            method: String,
            bundle: Bundle?
        ): Any? = when (service.application) {
            is EcosedApplication -> {
                (service.application as EcosedApplication).apply {
                    getPluginEngine().apply {
                        return execMethodCall(
                            name = name,
                            method = method,
                            bundle = bundle
                        )
                    }
                }
            }

            else -> error(
                message = errorMessage
            )
        }

        /**
         * 调用插件代码的方法.
         * @param application 传入Application.
         * @param name 要调用的插件的通道.
         * @param method 要调用的插件中的方法.
         * @param bundle 通过Bundle传递参数.
         * @return 返回方法执行后的返回值,类型为Any?.
         */
        override fun execMethodCall(
            application: Application,
            name: String,
            method: String,
            bundle: Bundle?
        ): Any? = when (application) {
            is EcosedApplication -> {
                (application as EcosedApplication).apply {
                    getPluginEngine().apply {
                        return execMethodCall(
                            name = name,
                            method = method,
                            bundle = bundle
                        )
                    }
                }
            }

            else -> error(
                message = errorMessage
            )
        }

        /**
         * 调用插件代码的方法.
         * @param client 传入EcosedClient.
         * @param name 要调用的插件的通道.
         * @param method 要调用的插件中的方法.
         * @param bundle 通过Bundle传递参数.
         * @return 返回方法执行后的返回值,类型为Any?.
         */
        override fun execMethodCall(
            client: EcosedClient,
            name: String,
            method: String,
            bundle: Bundle?
        ): Any? = when (client.getApplication()) {
            is EcosedApplication -> {
                (client.getApplication() as EcosedApplication).apply {
                    getPluginEngine().apply {
                        return execMethodCall(
                            name = name,
                            method = method,
                            bundle = bundle
                        )
                    }
                }
            }

            else -> error(
                message = errorMessage
            )
        }
    }
}