package com.inbank.android_catalog.catalog.activities

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.NavHostFragment
import it.phoenixspa.inbank.theming.utilities.OnBackPressedKeyboardInterface


open class DimensionActivity: AppCompatActivity() {

    override fun onBackPressed() {
        (this as? OnBackPressedKeyboardInterface)?.also {
            if (it.onBackPressedKeyboard()) {
                return
            }
        }
        if (getCurrentFragment()?.let { fragment ->
                (fragment as? OnBackPressedKeyboardInterface)?.onBackPressedKeyboard() ?: let{
                    val fragmentManager = (fragment as? NavHostFragment)?.childFragmentManager
                    (getCurrentFragment(fragmentManager) as? OnBackPressedKeyboardInterface)?.onBackPressedKeyboard() == true
                }
            } == true){
            return
        }
        super.onBackPressed()
    }

    private fun getCurrentFragment(fm: FragmentManager?): Fragment? {
        return fm?.fragments?.let {
            if (it.isEmpty()) null else it.last()
        }
    }

    protected open fun getCurrentFragment(): Fragment? {
        val list = supportFragmentManager.fragments
        var currentFragment: Fragment? = null
        if (list.size >= 1) for (i in list.indices.reversed()) {
            currentFragment = list[i]
//            if (currentFragment != null && currentFragment !is SupportRequestManagerFragment) {
//                break
//            }
        }
        return currentFragment
    }

}