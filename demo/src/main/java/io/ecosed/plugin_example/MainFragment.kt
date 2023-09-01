package io.ecosed.plugin_example

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import io.ecosed.plugin.EcosedClient
import io.ecosed.plugin.PluginExecutor

class MainFragment : Fragment() {

    private lateinit var mClient: EcosedClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mClient = PluginExecutor.execMethodCall(
            fragment = this@MainFragment,
            name = LEDemo.channel,
            method = "client",
            objects = null
        ) as EcosedClient
        lifecycle.addObserver(mClient)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return mClient.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mClient.onViewCreated(view, savedInstanceState)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
    }

}