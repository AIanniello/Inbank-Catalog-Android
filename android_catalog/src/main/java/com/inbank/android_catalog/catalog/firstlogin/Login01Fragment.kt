package com.inbank.android_catalog.catalog.firstlogin

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.inbank.android_catalog.R
import com.inbank.android_catalog.catalog.AndroidConstantCatalog
import it.phoenixspa.inbank.theming.databinding.FragmentLogin01Binding

/**
 * Welcome screen, prompting the user to proceed with the activation.
 */
class Login01Fragment : Fragment() {

    private lateinit var binding: FragmentLogin01Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLogin01Binding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.activateButton.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.go_from_01_to_02)
        }
        binding.privacyLabel.setOnClickListener {
            //Navigation.findNavController(view).navigate(R.id.go_from_01_to_externalPrivacy)
        }
        binding.greenNumber.setOnClickListener {
            goToGreenNumber()
        }
    }

    private fun goToGreenNumber() {
        val intent = Intent(
            Intent.ACTION_DIAL, Uri
                .parse(AndroidConstantCatalog.NUMERO_VERDE)
        )
        // apre in un task differente
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            Login01Fragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}