package com.inbank.android_catalog.catalog.firstlogin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.navigation.Navigation
import com.inbank.android_catalog.R
import it.phoenixspa.inbank.theming.databinding.FragmentLogin05Binding

/**
 * OTP input screen.
 */
class Login05Fragment : Fragment() {

    private lateinit var binding: FragmentLogin05Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLogin05Binding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.code.doOnTextChanged { text, start, count, after ->

        }
        binding.sendCodeButton.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_login05_to_login06)
        }
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            Login05Fragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}