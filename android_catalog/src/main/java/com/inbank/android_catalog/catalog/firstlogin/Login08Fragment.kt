package com.inbank.android_catalog.catalog.firstlogin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.navigation.Navigation
import com.inbank.android_catalog.R
import it.phoenixspa.inbank.theming.databinding.FragmentLogin08Binding
import it.phoenixspa.inbank.theming.utilities.GetActivity
import it.phoenixspa.inbank.theming.utilities.OnBackPressedKeyboardInterface
import it.phoenixspa.inbank.theming.utilities.ScrambledKeyboardV2

/**
 * PIN input screen, required before enabling quick PIN access.
 */
class Login08Fragment : Fragment(), OnBackPressedKeyboardInterface {

    private lateinit var binding: FragmentLogin08Binding
    private lateinit var scrambledKeyboardV2: ScrambledKeyboardV2
    private var showKeyboard = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLogin08Binding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.confirmButton.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_login08_to_login09)
        }
        binding.forgetPINLabel.setOnClickListener {
            Toast.makeText(requireContext(), "Label Clicked", Toast.LENGTH_SHORT).show()
        }

        // ScrambledKeyboard setup.

        val b = binding.scrambledKeyboard.binding
        val buttons = listOf(
            b.button0, b.button1, b.button2, b.button3,
            b.button4, b.button5, b.button6, b.button7,
            b.button8, b.button9, b.button10, b.button11,
            b.button12, b.button13, b.button14
        )

        scrambledKeyboardV2 = ScrambledKeyboardV2(this,
            binding.code,
            buttons,
            b.button15,
            binding.scrambledKeyboard,
            binding.root,
            getActivity = object : GetActivity {
                override val activity: FragmentActivity
                    get() = requireActivity()

            })

    }

    override fun onResume() {
        super.onResume()
        binding.scrambledKeyboard.isVisible = true
    }

    override fun onBackPressedKeyboard(): Boolean {
        return scrambledKeyboardV2.onBackPressedKeyboard()
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            Login08Fragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}