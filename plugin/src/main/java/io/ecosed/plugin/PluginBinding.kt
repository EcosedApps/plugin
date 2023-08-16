package io.ecosed.plugin

import android.app.Activity
import android.content.Context

/**
 * 作者: wyq0918dev
 * 仓库: https://github.com/ecosed/plugin
 * 时间: 2023/08/15
 * 描述: 插件绑定器
 * 文档: https://github.com/ecosed/plugin/blob/master/README.md
 */
class PluginBinding constructor(
    context: Context?, isDebug: Boolean, packageName: String?, launch: Activity?
) {

    private val mContext: Context? = context
    private val mDebug: Boolean = isDebug
    private val mPackage: String? = packageName
    private val mLaunch: Activity? = launch

    /**
     * 获取上下文.
     * @return Context.
     */
    internal fun getContext(): Context? {
        return mContext
    }

    /**
     * 是否调试模式
     * @return Boolean
     */
    internal fun isDebug(): Boolean {
        return mDebug
    }

    internal fun getPackageName(): String? {
        return mPackage
    }

    internal fun getLaunchActivity(): Activity? {
        return mLaunch
    }
}