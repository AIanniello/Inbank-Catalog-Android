package com.inbank.android_catalog.catalog.fingerprint.disable

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.inbank.android_catalog.catalog.fingerprint.disable.FingerprintDisableStartFragment
import it.phoenixspa.inbank.theming.databinding.FragmentDisableFingerprint02Binding


class FingerprintDisableSuccessFragment : Fragment(){

    private lateinit var binding: FragmentDisableFingerprint02Binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDisableFingerprint02Binding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.endButton.setOnClickListener { view1: View? ->
            (activity as? FingerprintDisableStartFragment.OnFingerprintDisabledListener)?.onFingerprintDisabledSuccessful()
        }
    }


}