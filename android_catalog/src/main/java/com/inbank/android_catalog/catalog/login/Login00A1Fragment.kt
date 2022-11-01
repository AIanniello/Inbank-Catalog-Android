package com.inbank.android_catalog.catalog.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.inbank.android_catalog.R
import it.phoenixspa.inbank.theming.databinding.FragmentIl00a1Binding


class Login00A1Fragment : Fragment() {

    private lateinit var binding: FragmentIl00a1Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentIl00a1Binding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val content = binding.content
        val contentFooter = binding.contentfooter

        content.enterButton.setOnClickListener {
//            Toast.makeText(context, binding.enterButton.text, Toast.LENGTH_SHORT).show()
            Navigation.findNavController(view).navigate(R.id.action_loginIL00A1_to_loginILA04)
        }
        binding.privacyLabel.setOnClickListener {
            Toast.makeText(context, binding.privacyLabel.text, Toast.LENGTH_SHORT).show()
        }
        content.needHelpLabel.setOnClickListener {
            Toast.makeText(context, content.needHelpLabel.text, Toast.LENGTH_SHORT).show()
        }
        content.loginWithPIN.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_loginIL00A1_to_loginIL00B1)
        }
        contentFooter.newProfileButton.setOnClickListener {
            Toast.makeText(context, contentFooter.newProfileButton.text, Toast.LENGTH_SHORT).show()
        }
        contentFooter.footerGroup.isVisible = true
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            Login00A1Fragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}