package it.phoenixspa.inbank.catalog.firstlogin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import it.phoenixspa.inbank.catalog.R
import it.phoenixspa.inbank.theming.databinding.FragmentLogin14Binding

/**
 * Passphrase screen.
 */
class Login14Fragment : Fragment() {

    private lateinit var binding: FragmentLogin14Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLogin14Binding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.forwardButton.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_login14_to_login15)
        }
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            Login14Fragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}