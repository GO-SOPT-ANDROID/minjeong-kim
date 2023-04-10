package org.android.go.sopt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.android.go.sopt.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val currentFragment = supportFragmentManager.findFragmentById(R.id.fcv_main)
        if (currentFragment == null) {
            supportFragmentManager.beginTransaction().add(R.id.fcv_main, HomeFragment()).commit()
        }

        binding.bncMain.setOnItemSelectedListener { item ->
            changeFragment(
                when (item.itemId) {
                    R.id.menu_home -> {
                        changeFragment(HomeFragment())
                        return@setOnItemSelectedListener true
                    }
                    R.id.menu_search -> {
                        changeFragment(SearchFragment())
                        return@setOnItemSelectedListener true
                    }
                    R.id.menu_photo -> {
                        changeFragment(GalleryFragment())
                        return@setOnItemSelectedListener true
                    }
                    else -> {

                    }
                },
                false
            )
        }
    }
    private fun changeFragment(fragment: Unit): Boolean {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fcv_main, fragment)
            .commit()
    }


}