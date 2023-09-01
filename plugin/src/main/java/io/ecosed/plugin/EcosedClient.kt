package io.ecosed.plugin

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.ContextThemeWrapper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner

/**
 * 作者: wyq0918dev
 * 仓库: https://github.com/ecosed/plugin
 * 时间: 2023/08/22
 * 描述: 客户端组件
 * 文档: https://github.com/ecosed/plugin/blob/master/README.md
 */
abstract class EcosedClient : ContextThemeWrapper(), DefaultLifecycleObserver {

    /** 布局ID */
    @LayoutRes
    private var mContentLayoutId = 0

    /**
     * 附加基本上下文.
     */
    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
    }

    /** 获取是否调试模式. */
    abstract fun isDebug(): Boolean

    /**
     * 获取LibEcosed框架 - LibEcosed框架专用接口.
     */
    open fun getLibEcosed(): LibEcosed? {
        return null
    }

    /**
     * 产品图标 - LibEcosed框架专用接口.
     */
    open fun getProductLogo(): Drawable? {
        return null
    }

    /** 获取插件列表. */
    abstract fun getPluginList(): ArrayList<EcosedPlugin>?

    /**
     * 获取框架扩展.
     */
    open fun getExtension(): EcosedExtension? {
        return null
    }

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)

    }

    open fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return when (mContentLayoutId){
            0 -> null
            else -> inflater.inflate(
                mContentLayoutId,
                container,
                false
            )
        }
    }



    open fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    }

    override fun onStart(owner: LifecycleOwner) {
        super.onStart(owner)
    }

    override fun onResume(owner: LifecycleOwner) {
        super.onResume(owner)
    }

    override fun onStop(owner: LifecycleOwner) {
        super.onStop(owner)
    }

    override fun onDestroy(owner: LifecycleOwner) {
        super.onDestroy(owner)
    }

    /**
     * 设置布局ID
     */
    protected fun setContentView(@LayoutRes layoutResID: Int) {
        mContentLayoutId = layoutResID
    }

    /** 内部API - 客户端组件初始化. */
    internal fun attach(base: Context) {
        this@EcosedClient.attachBaseContext(
            base = base
        )
    }
}