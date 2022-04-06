package com.ajinkya.formula1.ui.activity

import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.ajinkya.formula1.R
import com.ajinkya.formula1.databinding.ActivityBottomNavigationBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BottomNavigationActivity : BaseActivity<ActivityBottomNavigationBinding>(
    ActivityBottomNavigationBinding::inflate
) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.toolbar.title = getString(R.string.app_name)

        val navController = findNavController(R.id.nav_host_fragment_activity_bottom_navigation)

        binding.navView.setupWithNavController(navController)
    }

    override fun onBackPressed() {
        finish()
    }
}