package com.example.sembago

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val homeFragment = HomeFragment()
        val favouriteFragment = FavouriteFragment()
        val trolleyFragment = TrolleyFragment()
        val profileFragment =  ProfileFragment()

        setCurrentFragment(homeFragment)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)

        bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.miHome -> setCurrentFragment(homeFragment)
                R.id.miFavourite -> setCurrentFragment(favouriteFragment)
                R.id.miTrolley -> setCurrentFragment(trolleyFragment)
                R.id.miProfile -> setCurrentFragment(profileFragment)
                R.id.miAstar -> {
                    val intent = Intent(this, AstarActivity::class.java)
                    startActivity(intent)
                    return@setOnNavigationItemSelectedListener true
                }
            }
            true
        }

        bottomNavigationView.getOrCreateBadge(R.id.miTrolley).apply {
            number = 10
            isVisible = true
        }
    }
    private fun setCurrentFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment, fragment)
            commit()
        }
    }
}