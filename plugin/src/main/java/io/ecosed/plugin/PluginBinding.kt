package io.ecosed.plugin

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.fragment.app.Fragment

/**
 * 作者: wyq0918dev
 * 仓库: https://github.com/ecosed/plugin
 * 时间: 2023/08/22
 * 描述: 插件绑定器
 * 文档: https://github.com/ecosed/plugin/blob/master/README.md
 */
class PluginBinding constructor(
    context: Context?,
    isDebug: Boolean,
    libEcosed: LibEcosed?,
    main: Fragment?,
    logo: Drawable?
) {

    /** 应用程序全局上下文. */
    private val mContext: Context? = context

    /** 是否调试模式. */
    private val mDebug: Boolean = isDebug

    /** LibEcosed. */
    private val mLibEcosed: LibEcosed? = libEcosed

    /** 应用入口片段. */
    private val mMain: Fragment? = main

    /** 产品图标. */
    private val mLogo: Drawable? = logo

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
     * 应用入口主片段 - LibEcosed框架专用接口.
     * @param ecosed 用于判断是否是LibEcosed.
     * @return Fragment?.
     */
    internal fun getMainFragment(ecosed: LibEcosed): Fragment? {
        return when (ecosed.javaClass) {
            mLibEcosed?.javaClass -> mMain
            else -> null
        }
    }

    /**
     * 获取产品图标 - LibEcosed框架专用接口.
     * @param ecosed 用于判断是否是LibEcosed.
     * @return Drawable?.
     */
    internal fun getProductLogo(ecosed: LibEcosed): Drawable? {
        return when (ecosed.javaClass) {
            mLibEcosed?.javaClass -> mLogo
            else -> null
        }
    }
}