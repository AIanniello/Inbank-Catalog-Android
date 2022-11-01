package it.phoenixspa.inbank.catalog.firstlogin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import it.phoenixspa.inbank.catalog.R
import it.phoenixspa.inbank.theming.databinding.FragmentLogin10Binding

/**
 * PIN creation confirmation screen (2nd PIN input).
 */
class Login10Fragment : Fragment() {

    private lateinit var binding: FragmentLogin10Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLogin10Binding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.confirmButton.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_login10_to_login11)
        }
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            Login10Fragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}