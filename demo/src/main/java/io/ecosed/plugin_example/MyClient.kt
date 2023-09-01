package io.ecosed.plugin_example

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import io.ecosed.plugin.EcosedClient
import io.ecosed.plugin.EcosedExtension
import io.ecosed.plugin.EcosedPlugin
import io.ecosed.plugin.LibEcosed

class MyClient : EcosedClient() {

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        setContentView(R.layout.ecosed_client_main)


        getActivity()
        getApplication()
        getFragment()
        getFragmentManager()
        getWindow()
        getWindowManager()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val hello = findViewById<TextView>(R.id.textView)
        hello?.text = "Hello World\nfindViewById in EcosedClient"
    }

    override fun getLibEcosed(): LibEcosed {
        super.getLibEcosed()
        return LEDemo()
    }

    override fun getExtension(): EcosedExtension {
        super.getExtension()
        return FWDemo()
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
