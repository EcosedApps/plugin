package io.ecosed.plugin

import android.app.Activity
import android.content.Context

/**
 * 作者: wyq0918dev
 * 仓库: https://github.com/ecosed/plugin
 * 时间: 2023/08/21
 * 描述: 插件绑定器
 * 文档: https://github.com/ecosed/plugin/blob/master/README.md
 */
class PluginBinding constructor(
    context: Context?,
    isDebug: Boolean,
    libEcosed: LibEcosed?,
    launch: Activity?,
    settings: Activity?
) {

    /** 应用程序全局上下文. */
    private val mContext: Context? = context

    /** 是否调试模式. */
    private val mDebug: Boolean = isDebug

    /** LibEcosed */
    private val mLibEcosed: LibEcosed? = libEcosed

    /** 应用启动入口Activity. */
    private val mLaunch: Activity? = launch

    /** 应用设置Activity. */
    private val mSettings: Activity? = settings
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
     * 应用启动入口Activity - LibEcosed框架专用接口.
     * @param ecosed 用于判断是否是LibEcosed
     * @return Activity?.
     */
    internal fun getLaunchActivity(ecosed: LibEcosed): Activity? {
        return when (ecosed.javaClass) {
            mLibEcosed?.javaClass -> mLaunch
            else -> null
        }
    }

    /**
     * 应用设置Activity - LibEcosed框架专用接口.
     * @param ecosed 用于判断是否是LibEcosed
     * @return Activity?.
     */
    internal fun getSettingsActivity(ecosed: LibEcosed): Activity? {
        return when (ecosed.javaClass) {
            mLibEcosed?.javaClass -> mSettings
            else -> null
        }
    }
}