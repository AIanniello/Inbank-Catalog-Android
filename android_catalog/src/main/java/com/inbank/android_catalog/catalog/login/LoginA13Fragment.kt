package com.inbank.android_catalog.catalog.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import it.phoenixspa.inbank.theming.databinding.FragmentIla13Binding


class LoginA13Fragment : Fragment() {

    private lateinit var binding: FragmentIla13Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentIla13Binding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.endButton.setOnClickListener {
            Toast.makeText(context, binding.endButton.text, Toast.LENGTH_SHORT).show()
        }
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            LoginA13Fragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}