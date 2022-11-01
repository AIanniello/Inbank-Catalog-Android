package com.inbank.android_catalog.catalog.fingerprint.disable

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import it.phoenixspa.inbank.theming.databinding.FragmentDisableFingerprint01Binding


class FingerprintDisableStartFragment : Fragment() {

    private lateinit var binding: FragmentDisableFingerprint01Binding

    interface OnFingerprintDisabledListener {
        fun onFingerprintDisableClick()

        fun onFingerprintDisabledSuccessful()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentDisableFingerprint01Binding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.disableButton.setOnClickListener { view1: View? ->
            (activity as? OnFingerprintDisabledListener)?.onFingerprintDisableClick()
        }
    }

}