package it.phoenixspa.inbank.catalog.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.navigation.Navigation
import it.phoenixspa.inbank.catalog.R
import it.phoenixspa.inbank.theming.databinding.FragmentIl00b1Binding
import it.phoenixspa.inbank.theming.utilities.GetActivity
import it.phoenixspa.inbank.theming.utilities.OnBackPressedKeyboardInterface
import it.phoenixspa.inbank.theming.utilities.ScrambledKeyboardV2


class Login00B1Fragment : Fragment(), OnBackPressedKeyboardInterface {

    private lateinit var binding: FragmentIl00b1Binding
    private lateinit var scrambledKeyboardV2: ScrambledKeyboardV2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentIl00b1Binding.inflate(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val content = binding.content
        val contentFooter = binding.contentfooter

        content.root.setOnClickListener {
            binding.scrambledKeyboard.isVisible = false
        }

        content.enterButton.setOnClickListener {
            Toast.makeText(context, content.enterButton.text, Toast.LENGTH_SHORT).show()
        }
        binding.privacyLabel.setOnClickListener {
            //Navigation.findNavController(view).navigate(R.id.)
            Toast.makeText(context, binding.privacyLabel.text, Toast.LENGTH_SHORT).show()
        }
        content.loginWithUsrAndPwd.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_loginIL00B1_to_loginIL00A1)
        }
        contentFooter.newProfileButton.setOnClickListener {
            Toast.makeText(context, contentFooter.newProfileButton.text, Toast.LENGTH_SHORT).show()
        }
        contentFooter.footerGroup.isVisible = true

        // ScrambledKeyboard setup.

        val b = binding.scrambledKeyboard.binding
        val buttons = listOf(
            b.button0, b.button1, b.button2, b.button3,
            b.button4, b.button5, b.button6, b.button7,
            b.button8, b.button9, b.button10, b.button11,
            b.button12, b.button13, b.button14
        )

        scrambledKeyboardV2 = ScrambledKeyboardV2(this,
            content.pin,
            buttons,
            b.button15,
            binding.scrambledKeyboard,
            binding.root,
            getActivity = object : GetActivity {
                override val activity: FragmentActivity
                    get() = requireActivity()

            })
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            Login00B1Fragment().apply {
                arguments = Bundle().apply {
                }
            }
    }

    override fun onBackPressedKeyboard(): Boolean {
        return scrambledKeyboardV2.onBackPressedKeyboard()
    }
}