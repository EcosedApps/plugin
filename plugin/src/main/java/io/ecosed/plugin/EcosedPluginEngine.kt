package io.ecosed.plugin

import android.app.Activity
import android.util.Log

/**
 * 作者: wyq0918dev
 * 仓库: https://github.com/ecosed/EcosedPlugin
 * 时间: 2023/08/08
 * 描述: 插件引擎
 * 文档: https://github.com/ecosed/EcosedPlugin/blob/master/README.md
 */
class EcosedPluginEngine(activity: Activity) {

    private val mActivity: Activity = activity
    private var mBinding: EcosedPluginBinding? = null
    private var mPluginList: ArrayList<EcosedPlugin> = arrayListOf()

    /**
     * 将引擎附加到Activity
     */
    fun attach() {
        mPluginList.clear()
        mBinding = EcosedPluginBinding(activity = mActivity)
    }

    /**
     * 把引擎从Activity分离
     */
    fun detach() {
        mPluginList.clear()
        mBinding = null
    }

    /**
     * 添加插件
     * @param plugin 传入你要添加的插件
     */
    fun addPlugin(plugin: EcosedPlugin) {
        mBinding?.let { binding ->
            plugin.apply {
                try {
                    onEcosedAttached(binding = binding)
                } catch (e: Exception) {
                    Log.e(tag, "addPlugin", e)
                }
            }
        }.run {
            mPluginList.add(element = plugin)
        }
    }

    /**
     * 移除插件
     * @param plugin 传入你要移除的插件
     */
    fun removePlugin(plugin: EcosedPlugin) {
        mBinding?.let { binding ->
            plugin.apply {
                try {
                    onEcosedDetached(binding = binding)
                } catch (e: Exception) {
                    Log.e(tag, "removePlugin", e)
                }
            }
        }.run {
            mPluginList.remove(element = plugin)
        }
    }

    /**
     * 调用插件代码的方法
     * @param channel 要调用的插件的通道
     * @param call 要调用的插件中的方法
     * @return 返回方法执行后的返回值,类型为Any?。
     */
    fun execMethodCall(channel: String, call: String): Any? {
        var result: Any? = null
        mPluginList.forEach { plugin ->
            val method: EcosedPluginMethod = plugin.getEcosedPluginMethod()
            when (method.getChannel()) {
                channel -> result = method.execMethodCall(
                    channel = channel, call = call
                )

                else -> if (BuildConfig.DEBUG) {
                    Log.e(tag, "请传入有效的通道名称")
                }
            }
        }
        return result
    }

    companion object {
        const val tag: String = "EcosedPluginEngine"
    }
}