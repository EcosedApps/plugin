package io.ecosed.plugin

import android.app.Activity

class EcosedPluginMethod(binding: EcosedPluginBinding) {

    private var mBinding: EcosedPluginBinding = binding
    private lateinit var mCallBack: EcosedMethodCallHandler
    private var mResult: Any? = null

    fun setMethodCallHandler(callBack: EcosedMethodCallHandler?) {
        callBack?.let {
            this.mCallBack = it
        }
    }

    fun getActivity(): Activity {
        return mBinding.getActivity()
    }

    fun execMethodCall(call: String?): Any? {
        call?.let {
            mCallBack.onEcosedMethodCall(call = call, result = object : EcosedResult {
                override fun success(result: Any?) {
                    mResult = result
                }

                override fun notImplemented() {
                    mResult = null
                }
            })
        }
        return mResult
    }
}