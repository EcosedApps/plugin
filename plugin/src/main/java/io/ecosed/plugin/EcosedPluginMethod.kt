package io.ecosed.plugin

import android.app.Activity

class EcosedPluginMethod(binding: EcosedPluginBinding, channel: String) {

    private var mBinding: EcosedPluginBinding = binding
    private var mChannel: String = channel
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

    fun getChannel() :String {
        return mChannel
    }

    fun execMethodCall(channel: String, call: String?): Any? {
        if (channel == mChannel){
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
        }

        return mResult
    }
}