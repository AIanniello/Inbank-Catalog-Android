package com.inbank.android_catalog.catalog.firstlogin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.inbank.android_catalog.R
import it.phoenixspa.inbank.theming.databinding.FragmentLogin07Binding

/**
 * Screen prompting the user to enable the PIN access.
 */
class Login07Fragment : Fragment() {

    private lateinit var binding: FragmentLogin07Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLogin07Binding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.continueButton.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_login07_to_login08)
        }
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            Login07Fragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}