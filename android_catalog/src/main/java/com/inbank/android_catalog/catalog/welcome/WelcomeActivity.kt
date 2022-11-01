package com.inbank.android_catalog.catalog.welcome

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.inbank.android_catalog.R
import com.inbank.android_catalog.catalog.login.LoginActivity
import it.phoenixspa.inbank.theming.databinding.ActivityWelcomeBinding

//import androidx.databinding.DataBindingUtil

private const val NUM_PAGES = 4

class WelcomeActivity: AppCompatActivity() {

    private lateinit var binding: ActivityWelcomeBinding

    private var userId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeBinding.inflate(LayoutInflater.from(this), null, false)
        setContentView(binding.root)

        userId = intent.getStringExtra(USER)

        binding.welcomeViewPager.adapter = WelcomePagerAdapter(this)
        binding.welcomeViewPager.registerOnPageChangeCallback(WelcomeOnChangePageCallback())

        TabLayoutMediator(binding.welcomeTabLayout, binding.welcomeViewPager) { tab, pos -> tab.select() }.attach()

        binding.welcomeNext.setOnClickListener {
            val current = binding.welcomeViewPager.currentItem
            if (current >= NUM_PAGES - 2) {
                binding.welcomeNext.visibility = View.GONE
                binding.welcomeActivate.visibility = View.VISIBLE
            }
            binding.welcomeViewPager.currentItem = current + 1
        }

        binding.welcomeActivate.setOnClickListener { goToLoginActivity() }
        binding.root.background = getDrawable(R.drawable.ic_gradient_background_)
    }

    private inner class WelcomeOnChangePageCallback: ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            if(position >= NUM_PAGES - 1) {
                binding.welcomeNext.visibility = View.GONE
                binding.welcomeActivate.visibility = View.VISIBLE
            } else {
                binding.welcomeNext.visibility = View.VISIBLE
                binding.welcomeActivate.visibility = View.GONE
            }
        }
    }

    private inner class WelcomePagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {

        override fun getItemCount(): Int = NUM_PAGES

        override fun createFragment(position: Int): Fragment {
            return when(position){
                0 -> {
                    WelcomeFragmentBase.getInstance(
                        image = R.drawable.img_no_image_placeholder_1000px,
                        title = R.string.it01_welcome_text,
                        subtitle = R.string.it01_welcome_body,
                        privacy = R.string.welcome_privacy_button
                    )
                }
                1 -> {
                    WelcomeFragmentBase.getInstance(
                        image = R.drawable.img_no_image_placeholder_1000px,
                        title = R.string.it02_welcome_text,
                        subtitle = R.string.it02_welcome_body
                    )
                }
                2 -> {
                    WelcomeFragmentBase.getInstance(
                        image = R.drawable.img_no_image_placeholder_1000px,
                        title = R.string.it03_welcome_text,
                        subtitle = R.string.it03_welcome_body
                    )
                }
                3 -> {
                    WelcomeFragmentBase.getInstance(
                        image = R.drawable.img_no_image_placeholder_1000px,
                        title = R.string.it04_welcome_text,
                        subtitle = R.string.it04_welcome_body
                    )
                }
                else -> throw IndexOutOfBoundsException()
            }
        }
    }

    private fun goToLoginActivity() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivityForResult(intent, REQUEST_CODE)
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

    companion object {
        const val USER = "arg:user"

        const val REQUEST_CODE = 9999
    }
}