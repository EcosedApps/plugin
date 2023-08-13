package io.ecosed.plugin

import android.app.Activity
import android.util.Log

/**
 * 作者: wyq0918dev
 * 仓库: https://github.com/ecosed/plugin
 * 时间: 2023/08/08
 * 描述: 插件引擎
 * 文档: https://github.com/ecosed/plugin/blob/master/README.md
 */
class PluginEngine {

    private lateinit var mActivity: Activity
    private lateinit var mPluginList: ArrayList<EcosedPlugin>
    private var mBinding: EcosedPlugin.EcosedPluginBinding? = null

    /**
     * 将引擎附加到Activity.
     */
    fun attach() {
        mPluginList = arrayListOf()
        mBinding = EcosedPlugin.EcosedPluginBinding(activity = mActivity)
    }

    /**
     * 把引擎从Activity分离.
     */
    fun detach() {
        mPluginList.clear()
        mBinding = null
    }

    /**
     * 添加插件.
     * @param elements 传入你要添加的插件列表.
     */
    fun addPlugin(elements: Array<EcosedPlugin>) {
        mBinding?.let { binding ->
            elements.forEach {
                it.apply {
                    try {
                        onEcosedAdded(binding = binding)
                    } catch (e: Exception) {
                        Log.e(tag, "addPlugin", e)
                    }
                }
            }
        }.run {
            elements.forEach {
                mPluginList.add(element = it)
            }
        }
    }

    /**
     * 移除插件.
     * @param elements 传入你要移除的插件列表.
     */
    fun removePlugin(elements: Array<EcosedPlugin>) {
        mBinding?.let { binding ->
            elements.forEach {
                it.apply {
                    try {
                        onEcosedRemoved(binding = binding)
                    } catch (e: Exception) {
                        Log.e(tag, "removePlugin", e)
                    }
                }
            }
        }.run {
            elements.forEach {
                mPluginList.remove(element = it)
            }
        }
    }

    /**
     * 插件列表.
     * @param plugin 要添加的插件,用逗号隔开.
     */
    fun pluginArrayOf(vararg plugin: EcosedPlugin): Array<EcosedPlugin> {
        return arrayOf(elements = plugin)
    }

    /**
     * 调用插件代码的方法.
     * @param name 要调用的插件的通道.
     * @param method 要调用的插件中的方法.
     * @return 返回方法执行后的返回值,类型为Any?.
     */
    fun execMethodCall(name: String, method: String): Any? {
        var result: Any? = null
        try {
            mPluginList.forEach { plugin ->
                val channel: PluginChannel = plugin.getPluginChannel
                when (channel.getChannel()) {
                    name -> result = channel.execMethodCall(
                        name = name,
                        method = method
                    )

                    else -> if (BuildConfig.DEBUG) {
                        Log.e(tag, "请传入有效的通道名称")
                    }
                }
            }
        } catch (e: Exception) {
            if (BuildConfig.DEBUG) {
                Log.e(tag, "forEach error!")
            }
        }
        return result
    }

    /**
     * 用于构建引擎的接口.
     */
    internal interface Builder {

        /**
         * 构建引擎.
         * @param activity 传入Activity.
         * @param content 高级扩展用法.
         * @return 返回已构建的引擎.
         */
        fun build(
            activity: Activity?,
            content: (PluginEngine) -> PluginEngine = { engine ->
                engine
            }
        ): PluginEngine
    }

    companion object : Builder {

        /**
         * 用于打印日志的标签.
         */
        private const val tag: String = "PluginEngine"

        /**
         * 引擎构建函数.
         * @param activity 传入Activity.
         * @param content 高级扩展用法.
         * @return 返回已构建的引擎.
         */
        override fun build(
            activity: Activity?,
            content: (PluginEngine) -> PluginEngine
        ): PluginEngine {
            content(
                PluginEngine()
            ).let { engine ->
                activity?.let {
                    engine.apply {
                        mActivity = it
                    }
                }
                return@build engine
            }
        }
    }
}