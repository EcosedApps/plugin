package io.ecosed.plugin_example

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import io.ecosed.plugin.EcosedClient
import io.ecosed.plugin.EcosedPlugin
import io.ecosed.plugin.LibEcosed
import io.ecosed.plugin.PluginChannel

class MyClient : EcosedClient() {

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)


        getActivity()
        getApplication()
        getFragment()
        getFragmentManager()
        getWindow()
        getWindowManager()

        execMethodCall<Any>(name = channel, "", null)
    }

    override fun onEcosedMethodCall(call: PluginChannel.MethodCall, result: PluginChannel.Result) {
        super.onEcosedMethodCall(call, result)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        return TextView(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (view as TextView).text = "Hello World!"
    }

    override fun getLibEcosed(): LibEcosed {
        super.getLibEcosed()
        return LEDemo()
    }

    override fun getPluginList(): ArrayList<EcosedPlugin> {
        return arrayListOf(ExamplePlugin(), ToastPlugin())
    }

    override fun getProductLogo(): Drawable? {
        super.getProductLogo()
        return ContextCompat.getDrawable(this@MyClient, R.drawable.baseline_android_24)
    }

    override fun isDebug(): Boolean {
        return BuildConfig.DEBUG
    }
}
