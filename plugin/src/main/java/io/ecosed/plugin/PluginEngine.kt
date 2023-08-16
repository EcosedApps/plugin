package io.ecosed.plugin

import android.content.Context
import android.os.Build
import android.util.Log
import org.lsposed.hiddenapibypass.HiddenApiBypass

/**
 * 作者: wyq0918dev
 * 仓库: https://github.com/ecosed/plugin
 * 时间: 2023/08/08
 * 描述: 插件引擎
 * 文档: https://github.com/ecosed/plugin/blob/master/README.md
 */
class PluginEngine {

    private lateinit var mBase: Context
    private lateinit var mHost: EcosedHost

    private var mContext: Context? = null
    private var mPluginList: ArrayList<EcosedPlugin>? = null
    private var mBinding: PluginBinding? = null

    /**
     * 将引擎附加到Application.
     */
    fun attach() {
        when {
            (mContext == null) and (mPluginList == null) and (mBinding == null) -> apply {
                mContext = mBase.applicationContext

                mPluginList = arrayListOf()
                mBinding = PluginBinding(
                    context = mContext!!,
                    isDebug = mHost.isDebug,
                    packageName = mHost.getPackageName(),
                    launch = mHost.getLaunchActivity()
                )


                mHost.getLibEcosed()?.let {


                    mBinding?.let { binding ->
                        it.apply {
                            try {
                                onEcosedAdded(binding = binding)
                            } catch (e: Exception) {
                                if (mHost.isDebug) {
                                    Log.e(tag, "addPlugin", e)
                                }
                            }
                        }
                    }.run {
                        mPluginList?.add(element = it)
                    }

                }

                mHost.getPluginList?.let {


                    mBinding?.let { binding ->
                        it.forEach { plugin ->
                            plugin.apply {
                                try {
                                    onEcosedAdded(binding = binding)
                                } catch (e: Exception) {
                                    if (mHost.isDebug) {
                                        Log.e(tag, "addPlugin", e)
                                    }
                                }
                            }
                        }
                    }.run {
                        it.forEach { plugin ->
                            mPluginList?.add(element = plugin)
                        }
                    }


                }
            }

            else -> if (mHost.isDebug) {
                Log.e(tag, "请勿重复执行attach")
            }
        }
    }

    /**
     * 把引擎从Activity分离.
     */
    fun detach() {
        when {
            (mContext != null) and (mPluginList != null) and (mBinding != null) -> apply {

                mHost.getLibEcosed()?.let {
                    mBinding?.let { binding ->
                        it.apply {
                            try {
                                onEcosedRemoved(binding = binding)
                            } catch (e: Exception) {
                                if (mHost.isDebug) {
                                    Log.e(tag, "removePlugin", e)
                                }
                            }
                        }
                    }.run {
                        mPluginList?.remove(element = it)
                    }
                }




                mHost.getPluginList?.let {

                    mBinding?.let { binding ->
                        it.forEach { plugin ->
                            plugin.apply {
                                try {
                                    onEcosedRemoved(binding = binding)
                                } catch (e: Exception) {
                                    if (mHost.isDebug) {
                                        Log.e(tag, "removePlugin", e)
                                    }
                                }
                            }
                        }
                    }.run {
                        it.forEach { plugin ->
                            mPluginList?.remove(element = plugin)
                        }
                    }



                }
                mContext = null
                mPluginList = null
                mBinding = null
            }

            else -> if (mHost.isDebug) {
                Log.e(tag, "请勿重复执行detach")
            }
        }
    }

//    /**
//     * 添加插件.
//     * @param plugins 传入你要添加的插件,可以传入多个.
//     */
//    private fun addPlugin(plugins: ArrayList<EcosedPlugin>) {
//        mBinding?.let { binding ->
//            plugins.forEach { plugin ->
//                plugin.apply {
//                    try {
//                        onEcosedAdded(binding = binding)
//                    } catch (e: Exception) {
//                        if (mApp.getEngineHost.isDebug) {
//                            Log.e(tag, "addPlugin", e)
//                        }
//                    }
//                }
//            }
//        }.run {
//            plugins.forEach { plugin ->
//                mPluginList?.add(element = plugin)
//            }
//        }
//    }
//
//    /**
//     * 移除插件.
//     * @param plugins 传入你要移除的插件,可以传入多个.
//     */
//    private fun removePlugin(plugins: ArrayList<EcosedPlugin>) {
//        mBinding?.let { binding ->
//            plugins.forEach { plugin ->
//                plugin.apply {
//                    try {
//                        onEcosedRemoved(binding = binding)
//                    } catch (e: Exception) {
//                        if (mApp.getEngineHost.isDebug) {
//                            Log.e(tag, "removePlugin", e)
//                        }
//                    }
//                }
//            }
//        }.run {
//            plugins.forEach { plugin ->
//                mPluginList?.remove(element = plugin)
//            }
//        }
//    }

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
                plugin.getPluginChannel.let { channel ->
                    when (channel.getChannel()) {
                        name -> result = channel.execMethodCall(
                            name = name, method = method
                        )
                    }
                }
            }
        } catch (e: Exception) {
            if (mHost.isDebug) {
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
         * @param application 传入EcosedApplication
         * @param isUseHiddenApi 是否使用隐藏API, LibEcosed框架专用接口
         * @return 返回已构建的引擎.
         */
        fun build(
            baseContext: Context?,
            application: EcosedApplication,
            isUseHiddenApi: Boolean? = false,
            content: (
                PluginEngine, (Boolean) -> Unit
            ) -> PluginEngine = { engine, hidden ->
                if (isUseHiddenApi != null) {
                    hidden(isUseHiddenApi).let {
                        return@let engine
                    }
                } else {
                    engine
                }
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
         * @param isUseHiddenApi 是否使用隐藏API, LibEcosed框架专用接口
         * @return 返回已构建的引擎.
         */
        override fun build(
            baseContext: Context?,
            application: EcosedApplication,
            isUseHiddenApi: Boolean?,
            content: (
                PluginEngine, (Boolean) -> Unit
            ) -> PluginEngine
        ): PluginEngine {
            content(
                PluginEngine()
            ) { use ->
                if (use and (isUseHiddenApi == true)) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                        HiddenApiBypass.addHiddenApiExemptions("L")
                    }
                }
            }.let { engine ->
                engine.apply {
                    baseContext?.let { base ->
                        mBase = base
                    }

                    mHost = application.getEngineHost
                }
                return@build engine
            }
        }
    }
}