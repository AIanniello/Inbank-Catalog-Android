package com.inbank.android_catalog.catalog.pin.disable

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment

import it.phoenixspa.inbank.theming.databinding.FragmentDisablePin02Binding

/**
 * Gestisce la disabilitazione del PIN.
 * Include il supporto a Fingerprint, quindi disabiliterà anche quest ultimo alla disabilitazione del PIN.
 */
class PinDisableCompleteFragment : Fragment() {

    private lateinit var binding: FragmentDisablePin02Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDisablePin02Binding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.endButton.let { btn ->
            btn.setOnClickListener { view ->
                (activity as? PinDisableFragment.PinDisableListener)?.onDisableCompleteButtonEndPressed()
            }
        }
    }


}