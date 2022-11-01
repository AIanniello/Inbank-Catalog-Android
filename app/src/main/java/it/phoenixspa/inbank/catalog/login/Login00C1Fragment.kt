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
import it.phoenixspa.inbank.theming.databinding.FragmentIl00c1Binding
import it.phoenixspa.inbank.theming.databinding.ViewcontentIl00cBinding
import it.phoenixspa.inbank.theming.databinding.ViewcontentfooterIl00c1Binding
import it.phoenixspa.inbank.theming.utilities.GetActivity
import it.phoenixspa.inbank.theming.utilities.OnBackPressedKeyboardInterface
import it.phoenixspa.inbank.theming.utilities.ScrambledKeyboardV2


class Login00C1Fragment : Fragment(), OnBackPressedKeyboardInterface {

    private lateinit var bindingContentFooter: ViewcontentfooterIl00c1Binding
    private lateinit var bindingContent: ViewcontentIl00cBinding
    private lateinit var binding: FragmentIl00c1Binding
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
        binding = FragmentIl00c1Binding.inflate(inflater)
        bindingContent = binding.content
        bindingContentFooter = binding.contentfooter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        bindingContent.prompt.isVisible = true
        binding.logo.isVisible = true

        bindingContent.enterButton.setOnClickListener {
            Toast.makeText(context, bindingContent.enterButton.text, Toast.LENGTH_SHORT).show()
        }
        binding.privacyLabel.setOnClickListener {
            //Navigation.findNavController(view).navigate(R.id.)
            Toast.makeText(context, binding.privacyLabel.text, Toast.LENGTH_SHORT).show()
        }
        bindingContent.loginWithUsrAndPwd.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_loginIL00C1_to_loginIL00A1)
        }
        bindingContentFooter.newProfileButton.setOnClickListener {
            Toast.makeText(context, bindingContentFooter.newProfileButton.text, Toast.LENGTH_SHORT).show()
        }
        bindingContentFooter.footerGroup.isVisible = true

        val b = binding.scrambledKeyboard.binding
        val buttons = listOf(
            b.button0, b.button1, b.button2, b.button3,
            b.button4, b.button5, b.button6, b.button7,
            b.button8, b.button9, b.button10, b.button11,
            b.button12, b.button13, b.button14
        )

        scrambledKeyboardV2 = ScrambledKeyboardV2(this,
            bindingContent.pin,
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
            Login00C1Fragment().apply {
                arguments = Bundle().apply {
                }
            }
    }

    override fun onBackPressedKeyboard(): Boolean {
        return scrambledKeyboardV2.onBackPressedKeyboard()
    }
}