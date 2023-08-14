package io.ecosed.plugin

import android.content.Context

/**
 * 插件绑定器.
 */
class PluginBinding constructor(base: Context) {

    private val mBase: Context = base

    /**
     * 获取上下文.
     * @return Context.
     */
    internal fun getContext(): Context {
        return mBase.applicationContext
    }
}