package io.ecosed.plugin

import android.content.Context

/**
 * 作者: wyq0918dev
 * 仓库: https://github.com/ecosed/plugin
 * 时间: 2023/08/15
 * 描述: 插件绑定器
 * 文档: https://github.com/ecosed/plugin/blob/master/README.md
 */
class PluginBinding constructor(baseContext: Context, isDebug: Boolean) {

    private val mBase: Context = baseContext
    private val mDebug: Boolean = isDebug

    /**
     * 获取上下文.
     * @return Context.
     */
    internal fun getApplicationContext(): Context {
        return mBase.applicationContext
    }

    /**
     * 是否调试模式
     * @return Boolean
     */
    internal fun isDebug(): Boolean {
        return mDebug
    }
}