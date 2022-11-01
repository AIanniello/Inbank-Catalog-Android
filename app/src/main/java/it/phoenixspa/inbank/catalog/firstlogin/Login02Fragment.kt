package it.phoenixspa.inbank.catalog.firstlogin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import it.phoenixspa.inbank.catalog.R
import it.phoenixspa.inbank.theming.databinding.FragmentLogin02Binding

/**
 * Activation credentials input screen.
 */
class Login02Fragment : Fragment() {

    private lateinit var binding: FragmentLogin02Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLogin02Binding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.needHelpLabel.setOnClickListener {
//            val bundle = Bundle()
//            bundle.putString(WebLoadFragment.ARG_SERVICE_ID, AndroidConstant.CodiciRicercaUrl.SERVIZIO_FAQ) //"info-recupero"
//            bundle.putString(WebLoadFragment.ARG_ABI_CODE, GlobalUser.getUserCodAbiStazione())
//            Navigation.findNavController(view).navigate(R.id.go_from_02_to_03, bundle)
        }
        //it.phoenixspa.inbank.theming.
        binding.user.doOnTextChanged { text, start, count, after ->
            validateButton()
        }
        binding.password.doOnTextChanged { text, start, count, after ->
            validateButton()
        }
        // serve per verificare che l'app non crash al cambio di fucus del campo di testo
        //binding.confirmButton.isEnabled = false
        binding.confirmButton.setOnClickListener {
            // Nome utente e password sono state inserite?
            if (binding.user.text?.isEmpty() ?: true || binding.user.text?.isEmpty() ?: true) {
                // inserire
            }
            // Il NOME PROFILO è univoco sul device?
            // TODO implementare il controllo
            val uniqueProfileName = false
            if (uniqueProfileName) {
                Navigation.findNavController(view).navigate(R.id.go_from_02_to_05)
            } else {
                Navigation.findNavController(view).navigate(R.id.go_from_02_to_04)
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
            Login02Fragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}