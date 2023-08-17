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
    context: Context?, isDebug: Boolean, isLibEcosed: EcosedPlugin?, packageName: String?, launch: Activity?
) {

    /** 应用程序全局上下文. */
    private val mContext: Context? = context

    /** 是否调试模式. */
    private val mDebug: Boolean = isDebug

    private val mLibEcosed: EcosedPlugin? = isLibEcosed

    /** 应用包名. */
    private val mPackage: String? = packageName

    /** 应用启动入口Activity. */
    private val mLaunch: Activity? = launch

    /**
     * 获取上下文.
     * @return Context.
     */
    internal fun getContext(): Context? {
        return mContext
    }

    /**
     * 是否调试模式.
     * @return Boolean.
     */
    internal fun isDebug(): Boolean {
        return mDebug
    }

    /**
     * 应用包名 - LibEcosed框架专用接口.
     * @param plugin 用于判断是否是LibEcosed
     * @return String?.
     */
    internal fun getPackageName(plugin: EcosedPlugin): String? {
        return when (plugin.javaClass.name) {
            mLibEcosed?.javaClass?.name -> mPackage
            else -> null
        }
    }

    /**
     * 应用启动入口Activity - LibEcosed框架专用接口.
     * @param plugin 用于判断是否是LibEcosed
     * @return Activity?.
     */
    internal fun getLaunchActivity(plugin: EcosedPlugin): Activity? {
        return when (plugin.javaClass.name) {
            mLibEcosed?.javaClass?.name -> mLaunch
            else -> null
        }
    }
}