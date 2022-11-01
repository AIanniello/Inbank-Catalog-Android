package it.phoenixspa.inbank.catalog.firstlogin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.navigation.Navigation
import it.phoenixspa.inbank.catalog.R
import it.phoenixspa.inbank.theming.databinding.FragmentLogin04Binding

/**
 * Profile name input screen.
 */
class Login04Fragment : Fragment() {


    private lateinit var binding: FragmentLogin04Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentLogin04Binding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.profileName.doOnTextChanged { text, start, count, after ->
            validateButton()
        }
        binding.continueButton.setOnClickListener {
            // Il NOME PROFILO è univoco sul device?
            // TODO implementare il controllo
            val uniqueProfileName = true
            if (uniqueProfileName) {
                Navigation.findNavController(view).navigate(R.id.go_from_04_to_05)
            } else {

            }
        }
        validateButton()
    }

    private fun validateButton() {
        // TODO usare il metodo di abilitazione corretto
        //button.isEnabled = !(usrInputEditText.text?.isEmpty() ?: true || pwdInputEditText.text?.isEmpty() ?: true)
    }

    companion object {

        @JvmStatic
        fun newInstance() =
                Login04Fragment().apply {
                    arguments = Bundle().apply {
                    }
                }
    }
}