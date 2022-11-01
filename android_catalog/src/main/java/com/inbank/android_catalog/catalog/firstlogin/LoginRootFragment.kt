package com.inbank.android_catalog.catalog.firstlogin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View


class LoginRootFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    companion object {

        @JvmStatic
        fun newInstance() =
                LoginRootFragment().apply {
                    arguments = Bundle().apply {
                    }
                }
    }
}