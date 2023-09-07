package com.emilabdurahmanli.bankingapp.ui.activity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.emilabdurahmanli.bankingapp.R
import com.emilabdurahmanli.bankingapp.databinding.ActivityHomeBinding
import com.emilabdurahmanli.bankingapp.ui.fragment.HomeFragment
import com.emilabdurahmanli.bankingapp.ui.fragment.StatsFragment
import com.emilabdurahmanli.bankingapp.ui.fragment.SupportFragment
import com.google.android.material.navigation.NavigationBarView

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loadFragment(HomeFragment())
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> {
                    loadFragment(HomeFragment())
                    true
                }

                R.id.stats -> {
                    loadFragment(StatsFragment())
                    true
                }

                R.id.support -> {
                    loadFragment(SupportFragment())
                    true
                }

                else -> {
                    loadFragment(HomeFragment())
                    true
                }
            }
        }
    }

    private fun loadFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.commit()
    }
}