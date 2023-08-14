package io.ecosed.plugin

import android.app.Activity
import android.content.Context

/**
 * 插件绑定器.
 */
class PluginBinding constructor(
    baseContext: Context,
    appName: String?,
    launchActivity: Activity?
) {

    private val mBase: Context = baseContext
    private val mAppName: String? = appName
    private val mLaunchActivity: Activity? = launchActivity

    /**
     * 获取上下文.
     * @return Context.
     */
    internal fun getApplicationContext(): Context {
        return mBase.applicationContext
    }

    internal fun getAppName(): String? {
        return mAppName
    }

    internal fun getLaunchActivity(): Activity? {
        return mLaunchActivity
    }
}