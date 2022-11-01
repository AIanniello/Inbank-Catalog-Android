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
import it.phoenixspa.inbank.theming.databinding.FragmentIl01aBinding


class Login01AFragment : Fragment() {

    private lateinit var binding: FragmentIl01aBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentIl01aBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.activateButton.setOnClickListener {

            Toast.makeText(context, binding.activateButton.text, Toast.LENGTH_SHORT).show()
        }
        binding.privacyLabel.setOnClickListener {
            //Navigation.findNavController(view).navigate(R.id.)
            Toast.makeText(context, binding.privacyLabel.text, Toast.LENGTH_SHORT).show()
        }

        binding.user1.apply {
            isVisible = true
            // icon from drawable
            // circleImageRes = R.drawable.stk_profile_image_40dp
            val iconFromBitmap =
                BitmapFactory.decodeResource(resources, R.drawable.stk_profile_image_40dp)
            setCircleImageBitmap(iconFromBitmap)
            title = "Teresa Bianchi"
            subtitle = "74710949"
            setOnClickListener {
                //Toast.makeText(context, title, Toast.LENGTH_SHORT).show()
                Navigation.findNavController(view).navigate(R.id.action_loginIL01A_to_loginIL00A1)
            }
        }
        binding.user2.apply {
            isVisible = true
            title = "Stefano"
            subtitle = "00002043"
            setOnClickListener {
                Toast.makeText(context, title, Toast.LENGTH_SHORT).show()
            }
        }
        binding.user3.apply {
            isVisible = true
            title = "Massimo Verdi"
            subtitle = "99999999"
            hideSubtitle = true
            setOnClickListener {
                Navigation.findNavController(view).navigate(R.id.action_loginIL01A_to_loginIL00C1)
            }
        }
        binding.user4.apply {
            isVisible = false
//            isVisible = true
            title = "Anna Maria Rossi"
            subtitle = "99999999"
            hideSubtitle = true
            setOnClickListener {
                Toast.makeText(context, title, Toast.LENGTH_SHORT).show()
            }
        }
        binding.footerGroup.isVisible = !(binding.user1.isVisible && binding.user2.isVisible
                && binding.user3.isVisible && binding.user4.isVisible)
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            Login01AFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}