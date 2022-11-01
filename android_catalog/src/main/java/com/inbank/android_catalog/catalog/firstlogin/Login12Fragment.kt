package com.inbank.android_catalog.catalog.firstlogin

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.inbank.android_catalog.R
import it.phoenixspa.inbank.catalog.messages.Rule
import it.phoenixspa.inbank.catalog.messages.Score
import it.phoenixspa.inbank.theming.auth.password2.net.DataForPasswordChange
import it.phoenixspa.inbank.theming.auth.password2.net.OtherRule
import it.phoenixspa.inbank.theming.auth.password2.net.PasswordScoreResponse
import it.phoenixspa.inbank.theming.databinding.FragmentLogin12Binding
import java.util.regex.Pattern

/**
 * Password creation input screen, with rules.
 */
class Login12Fragment : Fragment() {

    private lateinit var scoreResponse: PasswordScoreResponse
    private lateinit var binding: FragmentLogin12Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLogin12Binding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val textInputLayout1 = binding.pwdLayout
        scoreResponse = PasswordScoreResponse(
            Score(
                60, "BUONA"
            ),
            listOf(
                Rule("Password lunga almeno 8 caratteri", false, true, "^.{8,}$", false),
                Rule("Almeno una lettera maiuscola", false, true, "^.*[A-Z].*\$", true),
                Rule("Almeno una lettera maiuscola2", false, true, "^.*[A-Z].*\$", true),
                Rule("", false, true, "^.*[A-Z].*\$", true),
                Rule("Almeno una lettera minuscola", true, true, "^.*[a-z].*\$", false),
                Rule(
                    "Almeno un numero o un carattere speciale",
                    true,
                    true,
                    "^.*[\\\\dàèéìòùäöüßčšž\\\\[\\\\(\\\\)\\\\]_\\\\-\\\\+\\\\/\\\\*=|!?\$%&.,@#].*\$",
                    false
                ),
                Rule(
                    "Non più di 4 caratteri uguali in sequenza",
                    true,
                    false,
                    "^(?!.*(.)\\\\1{4,}).*\$",
                    true
                )
            ),
            true
        )
        binding.pwd.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {

                //binding.changePasswordRulesLayout.updateUIAfterResponse(scoreResponse, binding.pwd.text.toString())
                binding.changePasswordRulesLayout.updateUIAfterResponse(scoreResponse.toDataForPasswordChange(binding.pwd.text.toString()), binding.pwd.text.toString())
            }
        })
        //binding.changePasswordRulesLayout.updateUIAfterResponse2(scoreResponse.toDataForPasswordChange(binding.pwd.text.toString()), binding.pwd.text.toString())
        binding.repeatPwd.apply {
            addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun afterTextChanged(p0: Editable?) {
                    //textInputLayout1.isHelperTextEnabled = true
                    val showError = text?.let {
                        if (it.length > 2) true else false
                    } ?: false
                    if (showError) {
                        error = "Example error text"

                    } else {
                        error = null
                    }

                }

            })
        }
        binding.confirmButton.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_login12_to_login13)
        }
    }

    private fun PasswordScoreResponse.toDataForPasswordChange(newPassword: String): DataForPasswordChange {
        val ruleNotPassed: String = rules?.mapNotNull {
            when{
                !it.isError -> null
                !it.isVisible -> null
                else -> {
                    val pattern = Pattern.compile(it.regexp ?: "")
                    val matcher = pattern.matcher(newPassword)
                    val matchFound = matcher.find()
                    if (!matchFound && it.text?.isNotEmpty() == true) {
                        it.text?.capitalize()
                    } else null
                }
            }

        }?.joinToString(separator = "\n") ?: ""
        return DataForPasswordChange(isValid, passwordScore?.score ?: 0, passwordScore?.text ?: "",
            rules?.mapNotNull { if(!it.isError) OtherRule(it.text ?: "", it.isPassed, it.isVisible ) else null } ?: listOf(),
            ruleNotPassed
        )
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            Login12Fragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}