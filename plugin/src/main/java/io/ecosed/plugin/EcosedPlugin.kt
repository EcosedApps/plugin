package io.ecosed.plugin

import android.content.Context

/**
 * 作者: wyq0918dev
 * 仓库: https://github.com/ecosed/plugin
 * 时间: 2023/08/08
 * 描述: 插件接口
 * 文档: https://github.com/ecosed/plugin/blob/master/README.md
 */
interface EcosedPlugin {

    /**
     * 插件被添加时执行.
     */
    fun onEcosedAdded(binding: EcosedPluginBinding)

    /**
     * 插件被移除时执行.
     */
    fun onEcosedRemoved(binding: EcosedPluginBinding)

    /**
     * 获取插件通信通道.
     */
    val getPluginChannel: PluginChannel

    /**
     * 插件绑定器.
     */
    class EcosedPluginBinding constructor(context: Context) {

        private val mContext: Context = context

        /**
         * 获取Activity.
         * @return Activity.
         */
        internal fun getContext(): Context {
            return mContext
        }
    }
}