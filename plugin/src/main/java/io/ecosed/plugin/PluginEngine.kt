package io.ecosed.plugin

import android.content.Context
import android.util.Log

/**
 * 作者: wyq0918dev
 * 仓库: https://github.com/ecosed/plugin
 * 时间: 2023/08/08
 * 描述: 插件引擎
 * 文档: https://github.com/ecosed/plugin/blob/master/README.md
 */
class PluginEngine {

    private lateinit var mBase: Context
    private lateinit var mApp: EcosedApplication
    private var mPluginList: ArrayList<EcosedPlugin>? = null
    private var mBinding: PluginBinding? = null

    /**
     * 将引擎附加到Activity.
     */
    fun attach() {
        if ((mPluginList == null) and (mBinding == null)){
            mPluginList = arrayListOf()
            mBinding = PluginBinding(
                baseContext = mBase,
                appName = mApp.getEngineHost.getAppName(),
                launchActivity = mApp.getEngineHost.getLaunchActivity()
            )
        } else if (mApp.getEngineHost.isDebug()) {
            Log.e(tag, "请勿重复执行attach")
        }

        addPlugin(mApp.getEngineHost.getPluginList())

    }

    /**
     * 把引擎从Activity分离.
     */
    fun detach() {
        removePlugin(mApp.getEngineHost.getPluginList())

        if ((mPluginList != null) and (mBinding != null)){
            mPluginList = null
            mBinding = null
        } else if (mApp.getEngineHost.isDebug()){
            Log.e(tag, "请勿重复执行detach")
        }
    }

    /**
     * 添加插件.
     * @param plugins 传入你要添加的插件,可以传入多个.
     */
    private fun addPlugin(plugins: ArrayList<EcosedPlugin>) {
        mBinding?.let { binding ->
            plugins.forEach { plugin ->
                plugin.apply {
                    try {
                        onEcosedAdded(binding = binding)
                    } catch (e: Exception) {
                        if (mApp.getEngineHost.isDebug()) {
                            Log.e(tag, "addPlugin", e)
                        }
                    }
                }
            }
        }.run {
            plugins.forEach { plugin ->
                mPluginList?.add(element = plugin)
            }
        }
    }

    /**
     * 移除插件.
     * @param plugins 传入你要移除的插件,可以传入多个.
     */
    private fun removePlugin(plugins: ArrayList<EcosedPlugin>) {
        mBinding?.let { binding ->
            plugins.forEach { plugin ->
                plugin.apply {
                    try {
                        onEcosedRemoved(binding = binding)
                    } catch (e: Exception) {
                        if (mApp.getEngineHost.isDebug()) {
                            Log.e(tag, "removePlugin", e)
                        }
                    }
                }
            }
        }.run {
            plugins.forEach { plugin ->
                mPluginList?.remove(element = plugin)
            }
        }
    }

    /**
     * 调用插件代码的方法.
     * @param name 要调用的插件的通道.
     * @param method 要调用的插件中的方法.
     * @return 返回方法执行后的返回值,类型为Any?.
     */
    internal fun execMethodCall(name: String, method: String): Any? {
        var result: Any? = null
        try {
            mPluginList?.forEach { plugin ->
                plugin.getPluginChannel.let {
                    when (it.getChannel()) {
                        name -> result = it.execMethodCall(
                            name = name,
                            method = method
                        )
                    }
                }
            }
        } catch (e: Exception) {
            if (mApp.getEngineHost.isDebug()) {
                Log.e(tag, "forEach error!", e)
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
         * @param baseContext 传入Activity.
         * @param content 高级扩展用法.
         * @return 返回已构建的引擎.
         */
        fun build(
            baseContext: Context?,
            application: EcosedApplication,
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
         * @param baseContext 传入应用程序上下文包装器的基本上下文上下文.
         * @param application 传入EcosedApplication
         * @param content 高级扩展用法.
         * @return 返回已构建的引擎.
         */
        override fun build(
            baseContext: Context?,
            application: EcosedApplication,
            content: (PluginEngine) -> PluginEngine
        ): PluginEngine {
            content(
                PluginEngine()
            ).let { engine ->
                engine.apply {
                    mBase = baseContext!!
                    mApp = application
                }
                return@build engine
            }
        }
    }
}