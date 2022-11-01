package com.inbank.android_catalog.catalog.firstlogin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import it.phoenixspa.inbank.theming.databinding.FragmentLogin15Binding

/**
 * Activation result screen. Also, Notify installation prompt.
 */
class Login15Fragment : Fragment() {

    private lateinit var binding: FragmentLogin15Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentLogin15Binding.inflate(inflater)
        return binding.root
    }

    companion object {

        @JvmStatic
        fun newInstance() =
                Login15Fragment().apply {
                    arguments = Bundle().apply {
                    }
                }
    }
}