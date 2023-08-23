package io.ecosed.plugin

import android.graphics.drawable.Drawable
import androidx.fragment.app.Fragment

/**
 * 作者: wyq0918dev
 * 仓库: https://github.com/ecosed/plugin
 * 时间: 2023/08/22
 * 描述: 应用程序主机
 * 文档: https://github.com/ecosed/plugin/blob/master/README.md
 */
interface EcosedHost {

    /** 获取插件引擎. */
    val getPluginEngine: PluginEngine

    /** 获取框架扩展. */
    val getExtension: EcosedExtension?
        get() = null

    /** 获取LibEcosed框架 - LibEcosed框架专用接口. */
    val getLibEcosed: LibEcosed?
        get() = null

    /** 获取插件列表. */
    val getPluginList: ArrayList<EcosedPlugin>?

    /** 应用入口主片段 - LibEcosed框架专用接口. */
    val getMainFragment: Fragment?
        get() = null

    /** 产品图标 - LibEcosed框架专用接口. */
    val getProductLogo: Drawable?
        get() = null

    /** 获取是否调试模式. */
    val isDebug: Boolean
}