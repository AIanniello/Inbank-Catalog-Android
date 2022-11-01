package com.inbank.android_catalog.catalog.firstlogin

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment


class Login03Fragment : Fragment() { //RetrievePasswordWebFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    companion object {

        @JvmStatic
        fun newInstance(ctx: Context) =
                Login03Fragment().apply {
                    //earguments = getBundle(ctx)
                }
    }
}