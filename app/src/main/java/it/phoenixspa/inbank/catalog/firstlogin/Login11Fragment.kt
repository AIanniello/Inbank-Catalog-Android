package it.phoenixspa.inbank.catalog.firstlogin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import it.phoenixspa.inbank.catalog.R
import it.phoenixspa.inbank.theming.databinding.FragmentLogin11Binding

/**
 * PIN creation result fragment.
 */
class Login11Fragment : Fragment() {

    private lateinit var binding: FragmentLogin11Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLogin11Binding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.activateButton.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_login11_to_login12)
        }
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            Login11Fragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}