package com.inbank.android_catalog.catalog.firstlogin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.inbank.android_catalog.R
import it.phoenixspa.inbank.theming.databinding.FragmentLogin13Binding

/**
 * Alerts configuration screen.
 */
class Login13Fragment : Fragment() {

    private lateinit var binding: FragmentLogin13Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLogin13Binding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val number = "\n" + "+XX XXX XXXXXXX"
        binding.prompt.text = getString(R.string.security_alerts_prompt, number)
        binding.textSwitch1.getSwitch().setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {

            } else {

            }
        }
        // server per visualizzare lo switch
//        binding.textSwitch2.showSwitch = true
//        binding.textSwitch2.getSwitch().setOnCheckedChangeListener { buttonView, isChecked ->
//            if (isChecked) {
//
//            } else {
//
//            }
//        }
        val value = "500,00"
        binding.textSwitch2.subtitle =
            getString(R.string.security_alerts_inbank_payments_info_with_limit, value)
        //binding.textSwitch2.status = "Disabilitati"

        binding.continueButton.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_login13_to_login14)
        }
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            Login13Fragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}