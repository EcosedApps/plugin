package io.ecosed.plugin

import android.app.Activity

class EcosedPluginBinding(activity: Activity) {

    private val mActivity: Activity = activity

    internal fun getActivity(): Activity {
        return mActivity
    }
}