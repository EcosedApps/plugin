package io.ecosed.plugin

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.ContextThemeWrapper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
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

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
    }


    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)

    }

    @LayoutRes
    private var mContentLayoutId = 0

    open fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return if (mContentLayoutId != 0) {
            inflater.inflate(mContentLayoutId, container, false)
        } else null
    }

    protected fun setContentView(@LayoutRes layoutResID: Int) {
        mContentLayoutId = layoutResID
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

    /** 获取框架扩展. */
    open val getExtension: EcosedExtension?
        get() = null

    /** 获取LibEcosed框架 - LibEcosed框架专用接口. */
    open val getLibEcosed: LibEcosed?
        get() = null

    /** 获取插件列表. */
    abstract val getPluginList: ArrayList<EcosedPlugin>?

    /** 应用入口主片段 - LibEcosed框架专用接口. */
    open val getMainFragment: Fragment?
        get() = null

    /** 产品图标 - LibEcosed框架专用接口. */
    open val getProductLogo: Drawable?
        get() = null

    /** 获取是否调试模式. */
    abstract val isDebug: Boolean

    /** 初始化 */
    internal fun attach(base: Context) {
        attachBaseContext(base)
    }
}