package it.phoenixspa.inbank.catalog.login

import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import it.phoenixspa.inbank.catalog.R
import it.phoenixspa.inbank.theming.databinding.FragmentIla04Binding


class LoginA04Fragment : Fragment() {

    private lateinit var binding: FragmentIla04Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentIla04Binding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.endButton.setOnClickListener {
            //Toast.makeText(context, binding.endButton.text, Toast.LENGTH_SHORT).show()
            Navigation.findNavController(view).navigate(R.id.action_loginILA04_to_loginILA10)
        }
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            LoginA04Fragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}