package io.ecosed.plugin

import android.app.Activity
import android.app.Service

/**
 * 插件列表
 * @param plugins 传入你要使用的插件,可以传入多个.
 */
fun pluginArrayOf(vararg plugins: EcosedPlugin): ArrayList<EcosedPlugin> {
    return arrayListOf(elements = plugins)
}

/**
 * 调用插件代码的方法.
 * @param activity 传入Activity
 * @param name 要调用的插件的通道.
 * @param method 要调用的插件中的方法.
 * @return 返回方法执行后的返回值,类型为Any?.
 */
fun execMethodCall(
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
fun execMethodCall(
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