package org.android.go.sopt.feature.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import org.android.go.sopt.feature.home.gallery.GalleryFragment
import org.android.go.sopt.R
import org.android.go.sopt.feature.home.search.SearchFragment
import org.android.go.sopt.databinding.ActivityHomeBinding
import org.android.go.sopt.feature.home.home.HomeFragment

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)


        changeFragment(HomeFragment())
        clickNavigation()
    }

    private fun changeFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fcv_main, fragment)
            .commit()
    }

    private fun clickNavigation() {
        binding.bnvMain.setOnItemSelectedListener {item ->
            changeFragment(
                when (item.itemId) {
                    R.id.menu_photo -> { GalleryFragment() }
                    R.id.menu_search -> { SearchFragment() }
                    else -> HomeFragment()
                }
            )
            true
        }
    }
}