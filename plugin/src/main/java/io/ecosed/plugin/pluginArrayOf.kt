package io.ecosed.plugin

fun pluginArrayOf(vararg plugins: EcosedPlugin): ArrayList<EcosedPlugin> {
    return arrayListOf(elements = plugins)
}