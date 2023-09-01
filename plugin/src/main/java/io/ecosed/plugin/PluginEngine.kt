package io.ecosed.plugin

import android.app.Application
import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.Log
import org.json.JSONObject


/**
 * 作者: wyq0918dev
 * 仓库: https://github.com/ecosed/plugin
 * 时间: 2023/08/22
 * 描述: 插件引擎
 * 文档: https://github.com/ecosed/plugin/blob/master/README.md
 */
class PluginEngine {

    /** 应用程序 */
    private lateinit var mApp: Application

    private lateinit var mBase: Context

    /** 客户端组件 */
    private lateinit var mClient: EcosedClient

    /** 应用程序全局上下文, 非UI上下文 */
    private lateinit var mContext: Context

    /** 插件绑定器 */
    private var mBinding: PluginBinding? = null

    /** 插件列表 */
    private var mPluginList: ArrayList<EcosedPlugin>? = null

    /**
     * 将引擎附加到应用.
     */
    private fun attach() {
        when {
            (mPluginList == null) or (mBinding == null) -> apply {
                mClient.attach(base = mBase)
                // 初始化插件绑定器.
                mBinding = PluginBinding(
                    context = mContext,
                    client = mClient,
                    debug = mClient.isDebug,
                    libEcosed = mClient.getLibEcosed,
                    main = mClient.getMainFragment,
                    logo = mClient.getProductLogo
                )
                // 初始化插件列表.
                mPluginList = arrayListOf()
                // 加载框架扩展 (如果使用了的话).
                mClient.getExtension?.let { extension ->
                    mBinding?.let { binding ->
                        extension.apply {
                            try {
                                attache(base = mBase)
                                if (mClient.isDebug) {
                                    Log.d(tag, "框架扩展已附加基本上下文")
                                }
                                onEcosedAdded(binding = binding)
                                if (mClient.isDebug) {
                                    Log.d(tag, "框架扩展已加载")
                                }
                            } catch (e: Exception) {
                                if (mClient.isDebug) {
                                    Log.e(tag, "框架扩展加载失败!", e)
                                }
                            }
                        }
                    }.run {
                        mPluginList?.add(element = extension)
                        if (mClient.isDebug) {
                            Log.d(tag, "框架扩展已添加到插件列表")
                        }
                    }
                }
                // 加载LibEcosed框架 (如果使用了的话).
                mClient.getLibEcosed?.let { ecosed ->
                    mBinding?.let { binding ->
                        ecosed.apply {

                            try {
                                attach(base = mBase)
                                if (mClient.isDebug) {
                                    Log.d(tag, "LibEcosed框架已附加基本上下文")
                                }
                                init()
                                if (mClient.isDebug) {
                                    Log.d(tag, "LibEcosed框架已初始化")
                                }
                                synchronized(ecosed) {
                                    initSDKs(application = mApp)
                                    initSDKInitialized()
                                }
                                if (mClient.isDebug) {
                                    Log.d(tag, "LibEcosed框架已初始化SDK")
                                }
                                onEcosedAdded(binding = binding)
                                if (mClient.isDebug) {
                                    Log.d(tag, "LibEcosed框架已加载")
                                }
                            } catch (e: Exception) {
                                if (mClient.isDebug) {
                                    Log.e(tag, "LibEcosed框架加载失败!", e)
                                }
                            }
                        }
                    }.run {
                        mPluginList?.add(element = ecosed)
                        if (mClient.isDebug) {
                            Log.d(tag, "LibEcosed框架已添加到插件列表")
                        }
                    }
                }
                // 添加所有插件.
                mClient.getPluginList?.let { plugins ->
                    mBinding?.let { binding ->
                        plugins.forEach { plugin ->
                            plugin.apply {
                                try {
                                    onEcosedAdded(binding = binding)
                                    if (mClient.isDebug) {
                                        Log.d(tag, "插件${plugin.javaClass.name}已加载")
                                    }
                                } catch (e: Exception) {
                                    if (mClient.isDebug) {
                                        Log.e(tag, "插件添加失败!", e)
                                    }
                                }
                            }
                        }
                    }.run {
                        plugins.forEach { plugin ->
                            mPluginList?.add(element = plugin)
                            if (mClient.isDebug) {
                                Log.d(tag, "插件${plugin.javaClass.name}已添加到插件列表")
                            }
                        }
                    }
                }
            }

            else -> if (mClient.isDebug) {
                Log.e(tag, "请勿重复执行attach!")
            }
        }
    }

//    /**
//     * 将引擎与应用分离.
//     */
//    fun detach() {
//        when {
//            (mPluginList != null) or (mBinding != null) -> apply {
//                // 销毁框架扩展 (如果使用了的话).
//                mClient.getExtension?.let { extension ->
//                    mBinding?.let { binding ->
//                        extension.apply {
//                            try {
//                                onEcosedRemoved(binding = binding)
//                                if (mClient.isDebug) {
//                                    Log.d(tag, "框架扩展已销毁")
//                                }
//                            } catch (e: Exception) {
//                                if (mClient.isDebug) {
//                                    Log.e(tag, "框架扩展销毁失败!", e)
//                                }
//                            }
//                        }
//                    }.run {
//                        mPluginList?.remove(element = extension)
//                        if (mClient.isDebug) {
//                            Log.d(tag, "框架扩展已从插件列表移除")
//                        }
//                    }
//                }
//                // 销毁LibEcosed框架 (如果使用了的话).
//                mClient.getLibEcosed?.let { ecosed ->
//                    mBinding?.let { binding ->
//                        ecosed.apply {
//                            try {
//                                onEcosedRemoved(binding = binding)
//                                if (mClient.isDebug) {
//                                    Log.d(tag, "LibEcosed框架已销毁")
//                                }
//                            } catch (e: Exception) {
//                                if (mClient.isDebug) {
//                                    Log.e(tag, "LibEcosed框架销毁失败!", e)
//                                }
//                            }
//                        }
//                    }.run {
//                        mPluginList?.remove(element = ecosed)
//                        if (mClient.isDebug) {
//                            Log.d(tag, "LibEcosed框架已从插件列表移除")
//                        }
//                    }
//                }
//                // 移除所有插件.
//                mClient.getPluginList?.let { plugins ->
//                    mBinding?.let { binding ->
//                        plugins.forEach { plugin ->
//                            plugin.apply {
//                                try {
//                                    onEcosedRemoved(binding = binding)
//                                    if (mClient.isDebug) {
//                                        Log.d(tag, "插件${plugin.javaClass.name}已销毁")
//                                    }
//                                } catch (e: Exception) {
//                                    if (mClient.isDebug) {
//                                        Log.e(tag, "移除插件失败!", e)
//                                    }
//                                }
//                            }
//                        }
//                    }.run {
//                        plugins.forEach { plugin ->
//                            mPluginList?.remove(element = plugin)
//                            if (mClient.isDebug) {
//                                Log.d(tag, "插件${plugin.javaClass.name}已从插件列表移除")
//                            }
//                        }
//                    }
//                }
//                // 销毁插件列表.
//                mPluginList = null
//                // 销毁插件绑定器.
//                mBinding = null
//            }
//
//            else -> if (mClient.isDebug) {
//                Log.e(tag, "请勿重复执行detach!")
//            }
//        }
//    }

    /**
     * 调用插件代码的方法.
     * @param name 要调用的插件的通道.
     * @param method 要调用的插件中的方法.
     * @return 返回方法执行后的返回值,类型为Any?.
     */
    internal fun execMethodCall(
        name: String,
        method: String,
        objects: JSONObject?
    ): Any? {
        var result: Any? = null
        try {
            mPluginList?.forEach { plugin ->
                plugin.getPluginChannel.let { channel ->
                    when (channel.getChannel()) {
                        name -> {
                            result = channel.execMethodCall(
                                name = name,
                                method = method,
                                objects = objects
                            )
                            if (mClient.isDebug) {
                                Log.d(
                                    tag,
                                    "插件代码调用成功!\n" +
                                            "通道名称:${name}.\n" +
                                            "方法名称:${method}.\n" +
                                            "返回结果:${result}."
                                )
                            }
                        }
                    }
                }
            }
        } catch (e: Exception) {
            if (mClient.isDebug) {
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
         * 引擎构建函数.
         * @param application 传入Application.
         * @param isUseHiddenApi 是否使用隐藏API - LibEcosed框架专用接口.
         * @return 返回已构建的引擎.
         */
        fun build(
            application: Application,
            isUseHiddenApi: Boolean? = false
        ): PluginEngine
    }

    companion object : Builder {

        /** 用于打印日志的标签. */
        private const val tag: String = "PluginEngine"

        /**
         * 引擎构建函数.
         * @param application 传入Application.
         * @param isUseHiddenApi 是否使用隐藏API - LibEcosed框架专用接口.
         * @return 返回已构建的引擎.
         */
        override fun build(
            application: Application,
            isUseHiddenApi: Boolean?
        ): PluginEngine = PluginEngine().let {
            return@let it.apply {
                if (application is EcosedApplication) {
                    application.apply {
                        mApp = application
                        mBase = baseContext
                        mContext = applicationContext
                        mClient = getEcosedClient()

                    }.run {
                        attach()
                    }
                } else error(
                    message = "错误:EcosedApplication接口未实现.\n" +
                            "提示1:可能未在应用的Application全局类实现EcosedApplication接口.\n" +
                            "提示2:应用的Application全局类可能未在AndroidManifest.xml中注册."
                )
            }
        }
    }
}