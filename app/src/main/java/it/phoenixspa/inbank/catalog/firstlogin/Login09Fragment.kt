package it.phoenixspa.inbank.catalog.firstlogin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import it.phoenixspa.inbank.catalog.R
import it.phoenixspa.inbank.catalog.common.KeyboardHandler
import it.phoenixspa.inbank.theming.databinding.FragmentLogin09Binding
import it.phoenixspa.inbank.theming.utilities.Utils

/**
 * PIN creation input screen (1st PIN input).
 */
class Login09Fragment : Fragment() {

    private lateinit var binding: FragmentLogin09Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLogin09Binding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.confirmButton.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_login09_to_login10)
        }
        binding.code.setOnFocusChangeListener { view, focused ->
            if (!focused) {
               KeyboardHandler.hideKeyboard(view)
            }
        }
        // Chiude la tastiera
        (binding.root as? View)?.setOnClickListener {
            KeyboardHandler.hideKeyboard(it)
        }
    }

    override fun onResume() {
        super.onResume()
        activity?.let { KeyboardHandler.showKeyboard(binding.code, it) }
    }

    override fun onPause() {
        super.onPause()
        activity?.let { KeyboardHandler.hideKeyboard(it) }
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            Login09Fragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}