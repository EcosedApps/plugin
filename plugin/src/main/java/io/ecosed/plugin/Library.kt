package io.ecosed.plugin

import android.app.Activity
import android.app.Application
import android.app.Service

/**
 * 插件列表
 * @param plugins 传入你要使用的插件,可以传入多个.
 */
fun pluginArrayOf(vararg plugins: EcosedPlugin): ArrayList<EcosedPlugin> {
    return arrayListOf(elements = plugins)
}