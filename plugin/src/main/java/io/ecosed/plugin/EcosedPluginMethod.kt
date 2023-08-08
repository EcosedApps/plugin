package io.ecosed.plugin

import android.app.Activity

/**
 * 作者: wyq0918dev
 * 仓库: https://github.com/ecosed/EcosedPlugin
 * 时间: 2023/08/08
 * 描述: 插件方法
 * 文档: https://github.com/ecosed/EcosedPlugin/blob/master/README.md
 */
class EcosedPluginMethod(binding: EcosedPluginBinding, channel: String) {

    private var mBinding: EcosedPluginBinding = binding
    private var mChannel: String = channel
    private lateinit var mCallBack: EcosedMethodCallHandler
    private var mResult: Any? = null

    /**
     * 设置方法调用
     * @param callBack 执行方法时调用EcosedMethodCallHandler
     */
    fun setMethodCallHandler(callBack: EcosedMethodCallHandler?) {
        callBack?.let {
            this.mCallBack = it
        }
    }

    /**
     * 获取Activity
     * @return 插件附加的Activity
     */
    fun getActivity(): Activity {
        return mBinding.getActivity()
    }

    /**
     * 获取通道
     * @return 通道名称
     */
    fun getChannel() :String {
        return mChannel
    }

    /**
     * 执行方法回调
     * @param channel 通道名称
     * @param call 方法名称
     * @return 方法执行后的返回值
     */
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