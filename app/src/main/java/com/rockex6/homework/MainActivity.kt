package com.rockex6.homework

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.navigation.ui.AppBarConfiguration
import com.rockex6.homework.databinding.ActivityMainBinding
import com.rockex6.homework.ui.zoodetail.ZooDetailFragment
import com.rockex6.homework.ui.zoolist.ZooListFragment


class MainActivity : AppCompatActivity() {
    //    private val navController
//        get() = findNavController(R.id.nav_host_fragment_content_main);
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val listFragment = ZooListFragment()


        supportFragmentManager.beginTransaction()
            .setReorderingAllowed(true)
            .replace(binding.vContent.id, listFragment)
            .addToBackStack("aaa")
            .commit()
    }


    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) {
//            supportFragmentManager.popBackStack("aaa",0)
            supportFragmentManager.popBackStack("aaa",0);
            return
        }
//        for (fragment in supportFragmentManager.fragments) {
//            if (fragment is ZooDetailFragment) {
//                supportFragmentManager.popBackStack()
//                return
//            }
//        }
        super.onBackPressed()
    }
}