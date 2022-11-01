package com.inbank.android_catalog.catalog.pin.disable

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import it.phoenixspa.inbank.theming.databinding.FragmentDisablePin01Binding

/**
 * Gestisce la disabilitazione del PIN.
 * Include il supporto a Fingerprint, quindi disabiliterà anche quest ultimo alla disabilitazione del PIN.
 */
class PinDisableFragment : Fragment() {

    private lateinit var binding: FragmentDisablePin01Binding

    interface PinDisableListener {
        fun onDisablePinSuccess()
        fun onDisableCompleteButtonEndPressed()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDisablePin01Binding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.disableButton.let {
            it.setOnClickListener {
                (activity as? PinDisableListener)?.onDisablePinSuccess()
            }
        }
    }

}