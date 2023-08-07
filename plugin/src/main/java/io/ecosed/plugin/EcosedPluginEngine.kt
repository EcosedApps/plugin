package io.ecosed.plugin

import android.app.Activity
import android.util.Log

class EcosedPluginEngine(activity: Activity) {

    private val mActivity: Activity = activity
    private var mBinding: EcosedPluginBinding? = null
    private var mMethod: EcosedPluginMethod? = null

    fun addPlugin(plugin: EcosedPlugin) {
        mBinding = EcosedPluginBinding(activity = mActivity)
        mBinding?.let { binding ->
            plugin.apply {
                onEcosedAttached(binding = binding)
            }
        }
        mMethod = plugin.getEcosedPluginMethod()
    }

    fun removePlugin(plugin: EcosedPlugin) {
        mBinding?.let { binding ->
            plugin.apply {
                onEcosedDetached(
                    binding = binding
                )
            }
        }
        mBinding = null
        mMethod = null
    }

    fun execMethodCall(call: String): Any? {
        return if (mMethod != null) {
            mMethod?.execMethodCall(call = call)
        } else if (BuildConfig.DEBUG) {
            Log.e("tag", "")
            null
        } else null
    }
}