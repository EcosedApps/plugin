package io.ecosed.plugin_example

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import io.ecosed.plugin.EcosedClient
import io.ecosed.plugin.EcosedExtension
import io.ecosed.plugin.EcosedPlugin
import io.ecosed.plugin.LibEcosed

class MyClient : EcosedClient() {


    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }









    override val getLibEcosed: LibEcosed
        get() = LEDemo()

    override val getExtension: EcosedExtension
        get() = FWDemo()

    override val getPluginList: ArrayList<EcosedPlugin>
        get() = arrayListOf(ExamplePlugin(), ToastPlugin())

    override val getMainFragment: Fragment
        get() = MainFragment()

    override val getProductLogo: Drawable?
        get() = ContextCompat.getDrawable(this@MyClient, R.drawable.baseline_android_24)

    override val isDebug: Boolean
        get() = BuildConfig.DEBUG

}
