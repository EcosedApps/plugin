package io.ecosed.plugin

import android.app.Activity

/**
 * 作者: wyq0918dev
 * 仓库: https://github.com/ecosed/EcosedPlugin
 * 时间: 2023/08/08
 * 描述: 插件绑定器
 * 文档: https://github.com/ecosed/EcosedPlugin/blob/master/README.md
 */
class EcosedPluginBinding constructor(activity: Activity) {

    private val mActivity: Activity = activity

    /**
     * 内部方法: 获取Activity
     */
    internal fun getActivity(): Activity {
        return mActivity
    }
}