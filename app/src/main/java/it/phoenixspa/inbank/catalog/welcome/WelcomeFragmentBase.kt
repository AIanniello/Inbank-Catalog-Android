package it.phoenixspa.inbank.catalog.welcome


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import it.phoenixspa.inbank.catalog.R
import it.phoenixspa.inbank.theming.databinding.FragmentWelcomeBinding

class WelcomeFragmentBase(): Fragment() {

    private lateinit var binding: FragmentWelcomeBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentWelcomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            binding.fragmentWelcomeImageView.setImageResource(it.getInt(IMAGE))
            binding.fragmentWelcomeImageView.contentDescription = getString(it.getInt(IMAGE_DESCRIPTION))
            binding.fragmentWelcomeTitle.text = getString(it.getInt(TITLE))
            binding.fragmentWelcomeSubtitle.text = getString(it.getInt(SUBTITLE))
            it.getInt(PRIVACY).let { privacyStrRes ->
                if (privacyStrRes != 0) {
                    binding.fragmentWelcomePrivacy.text = getString(privacyStrRes)
                    binding.fragmentWelcomePrivacy.setOnClickListener { goToPrivacy() }
                }
                else binding.fragmentWelcomePrivacy.isVisible = false
            }
        }
    }

    private fun goToPrivacy() {
       Toast.makeText(requireContext(), getString(R.string.privacy_policy), Toast.LENGTH_SHORT).show()
    }

    companion object {
        private const val IMAGE = "arg:image"
        private const val TITLE = "arg:title"
        private const val SUBTITLE = "arg:subtitle"
        private const val PRIVACY = "arg:privacy"
        private const val IMAGE_DESCRIPTION = "arg:imageContentDescription"

        fun getInstance(@DrawableRes image: Int,
                        @StringRes title: Int,
                        @StringRes subtitle: Int,
                        @StringRes privacy: Int = 0,
                        @StringRes imageContentDescr: Int
                        = R.string.contentdescription_imageview): WelcomeFragmentBase {
            val fragment = WelcomeFragmentBase()
            val bundle = Bundle().apply {
                putInt(IMAGE, image)
                putInt(TITLE, title)
                putInt(SUBTITLE, subtitle)
                putInt(PRIVACY, privacy)
                putInt(IMAGE_DESCRIPTION, imageContentDescr)
            }
            fragment.arguments = bundle
            return fragment
        }
    }
}