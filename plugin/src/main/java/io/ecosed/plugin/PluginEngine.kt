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

    /** 上下文包装器的基本上下文 */
    private lateinit var mBase: Context

    /** 应用程序主机 */
    private lateinit var mHost: EcosedHost

    /** 应用程序全局上下文, 非UI上下文 */
    private var mContext: Context? = null

    /** 插件绑定器 */
    private var mBinding: PluginBinding? = null

    /** 插件列表 */
    private var mPluginList: ArrayList<EcosedPlugin>? = null

    /**
     * 将引擎附加到应用.
     */
    fun attach() {
        when {
            (mContext == null) or (mPluginList == null) or (mBinding == null) -> apply {
                // 获取应用程序全局上下文.
                mContext = mBase.applicationContext
                // 初始化插件绑定器.
                mBinding = PluginBinding(
                    context = mContext,
                    isDebug = mHost.isDebug,
                    packageName = mHost.getPackageName,
                    launch = mHost.getLaunchActivity
                )
                // 初始化插件列表.
                mPluginList = arrayListOf()
                // 加载LibEcosed框架 (如果使用了的话).
                mHost.getLibEcosed?.let { ecosed ->
                    mBinding?.let { binding ->
                        ecosed.apply {
                            try {
                                onEcosedAdded(binding = binding)
                                if (mHost.isDebug) {
                                    Log.d(tag, "LibEcosed框架已加载")
                                }
                            } catch (e: Exception) {
                                if (mHost.isDebug) {
                                    Log.e(tag, "LibEcosed框架加载失败!", e)
                                }
                            }
                        }
                    }.run {
                        mPluginList?.add(element = ecosed)
                        if (mHost.isDebug) {
                            Log.d(tag, "LibEcosed框架已添加")
                        }
                    }
                }
                // 添加所有插件.
                mHost.getPluginList?.let { plugins ->
                    mBinding?.let { binding ->
                        plugins.forEach { plugin ->
                            plugin.apply {
                                try {
                                    onEcosedAdded(binding = binding)
                                    if (mHost.isDebug) {
                                        Log.d(tag, "插件${plugin.javaClass.name}已加载")
                                    }
                                } catch (e: Exception) {
                                    if (mHost.isDebug) {
                                        Log.e(tag, "插件添加失败!", e)
                                    }
                                }
                            }
                        }
                    }.run {
                        plugins.forEach { plugin ->
                            mPluginList?.add(element = plugin)
                            if (mHost.isDebug) {
                                Log.d(tag, "插件${plugin.javaClass.name}已添加")
                            }
                        }
                    }
                }
            }

            else -> if (mHost.isDebug) {
                Log.e(tag, "请勿重复执行attach!")
            }
        }
    }

    /**
     * 将引擎与应用分离.
     */
    fun detach() {
        when {
            (mContext != null) or (mPluginList != null) or (mBinding != null) -> apply {
                // 销毁LibEcosed框架 (如果使用了的话).
                mHost.getLibEcosed?.let { ecosed ->
                    mBinding?.let { binding ->
                        ecosed.apply {
                            try {
                                onEcosedRemoved(binding = binding)
                                if (mHost.isDebug) {
                                    Log.d(tag, "LibEcosed框架已销毁")
                                }
                            } catch (e: Exception) {
                                if (mHost.isDebug) {
                                    Log.e(tag, "LibEcosed框架销毁失败!", e)
                                }
                            }
                        }
                    }.run {
                        mPluginList?.remove(element = ecosed)
                        if (mHost.isDebug) {
                            Log.d(tag, "LibEcosed框架已移除")
                        }
                    }
                }
                // 移除所有插件.
                mHost.getPluginList?.let { plugins ->
                    mBinding?.let { binding ->
                        plugins.forEach { plugin ->
                            plugin.apply {
                                try {
                                    onEcosedRemoved(binding = binding)
                                    if (mHost.isDebug) {
                                        Log.d(tag, "插件${plugin.javaClass.name}已销毁")
                                    }
                                } catch (e: Exception) {
                                    if (mHost.isDebug) {
                                        Log.e(tag, "移除插件失败!", e)
                                    }
                                }
                            }
                        }
                    }.run {
                        plugins.forEach { plugin ->
                            mPluginList?.remove(element = plugin)
                            if (mHost.isDebug) {
                                Log.d(tag, "插件${plugin.javaClass.name}已移除")
                            }
                        }
                    }
                }
                // 销毁应用程序全局上下文.
                mContext = null
                // 销毁插件列表.
                mPluginList = null
                // 销毁插件绑定器.
                mBinding = null
            }

            else -> if (mHost.isDebug) {
                Log.e(tag, "请勿重复执行detach!")
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
                plugin.getPluginChannel.let { channel ->
                    when (channel.getChannel()) {
                        name -> {
                            result = channel.execMethodCall(
                                name = name, method = method
                            )
                            if (mHost.isDebug) {
                                Log.d(
                                    tag, "插件代码调用成功!\n" +
                                            "通道名称:${name}.\n" +
                                            "方法名称:${method}.\n" +
                                            "返回结果:${
                                                if (result == null) {
                                                    "返回结果为空"
                                                } else result
                                            }."
                                )
                            }
                        }
                    }
                }
            }
        } catch (e: Exception) {
            if (mHost.isDebug) {
                Log.e(tag, "插件代码调用失败!", e)
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
         * @param application 传入EcosedApplication.
         * @param isUseHiddenApi 是否使用隐藏API - LibEcosed框架专用接口.
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
         * @param application 传入EcosedApplication.
         * @param isUseHiddenApi 是否使用隐藏API, LibEcosed框架专用接口.
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
                isUseHiddenApi?.let { hidden ->
                    if (use and hidden) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                            HiddenApiBypass.addHiddenApiExemptions("L")
                            if (application.getEngineHost.isDebug) {
                                Log.d(tag, "已启用非SDK接口限制绕过")
                            }
                        } else {
                            if (application.getEngineHost.isDebug) {
                                Log.d(tag, "Android版本小于9无需使用非SDK接口限制绕过")
                            }
                        }
                    }
                }
            }.let { engine ->
                engine.apply {
                    baseContext?.let { base ->
                        mBase = base
                    }
                    application.getEngineHost.let { app ->
                        mHost = app
                    }
                }
                return@build engine
            }
        }
    }
}