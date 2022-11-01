package it.phoenixspa.inbank.catalog.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import it.phoenixspa.inbank.theming.databinding.FragmentIla05Binding


class LoginILA05Fragment : Fragment() {

    private lateinit var binding: FragmentIla05Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentIla05Binding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        binding.endButton.setOnClickListener {
//            //Toast.makeText(context, binding.endButton.text, Toast.LENGTH_SHORT).show()
//            //Navigation.findNavController(view).navigate(R.id.)
//        }
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            LoginILA05Fragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}