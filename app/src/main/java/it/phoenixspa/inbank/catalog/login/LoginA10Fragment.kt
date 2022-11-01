package it.phoenixspa.inbank.catalog.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import it.phoenixspa.inbank.catalog.R
import it.phoenixspa.inbank.theming.databinding.FragmentIla10Binding


class LoginA10Fragment : Fragment() {

    private lateinit var binding: FragmentIla10Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentIla10Binding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.endButton.setOnClickListener {
//            Toast.makeText(context, binding.endButton.text, Toast.LENGTH_SHORT).show()
            Navigation.findNavController(view).navigate(R.id.action_loginILA10_to_loginILA13)
        }
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            LoginA10Fragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}