package io.ecosed.plugin

interface EcosedHost {
    fun isDebug(): Boolean
    fun getPluginEngine(): PluginEngine
    fun getPluginList(): Array<EcosedPlugin>
}