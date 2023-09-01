package io.ecosed.plugin

import android.content.Context
import android.content.ContextWrapper

/**
 * 作者: wyq0918dev
 * 仓库: https://github.com/ecosed/plugin
 * 时间: 2023/08/22
 * 描述: 框架扩展接口
 * 文档: https://github.com/ecosed/plugin/blob/master/README.md
 */
abstract class EcosedExtension : ContextWrapper(null), EcosedPlugin,
    PluginChannel.MethodCallHandler {

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
    }

    internal fun attache(base: Context?) {
        this@EcosedExtension.attachBaseContext(
            base = base
        )
    }
}