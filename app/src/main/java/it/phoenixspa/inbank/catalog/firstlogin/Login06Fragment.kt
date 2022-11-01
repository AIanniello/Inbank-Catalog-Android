package it.phoenixspa.inbank.catalog.firstlogin

import androidx.biometric.BiometricPrompt
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import it.phoenixspa.inbank.catalog.R
import it.phoenixspa.inbank.theming.databinding.FragmentLogin06Binding

/**
 * Screen prompting the user to enable the biometric access.
 */
class Login06Fragment : Fragment() {

    private lateinit var binding: FragmentLogin06Binding
    private lateinit var biometricPrompt: BiometricPrompt

    private val authenticationCallback: BiometricPrompt.AuthenticationCallback =
        object : BiometricPrompt.AuthenticationCallback() {
            override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                println("onAuthenticationError")
            }

            override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                println("onAuthenticationSucceeded")
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
        biometricPrompt = BiometricPrompt(this,
            ContextCompat.getMainExecutor(requireActivity()),
            authenticationCallback)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLogin06Binding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.confirmButton.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_login06_to_login07)
        }

        biometricPrompt.authenticate(
            BiometricPrompt.PromptInfo.Builder()
                .setTitle("Title")
                .setSubtitle("Subtitle")
                .setNegativeButtonText(getString(R.string.cancel_button))
                .build()
        )
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            Login06Fragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}