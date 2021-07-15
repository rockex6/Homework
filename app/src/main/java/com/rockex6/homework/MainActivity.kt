package com.rockex6.homework

import android.os.Bundle
import com.rockex6.homework.databinding.ActivityMainBinding
import com.rockex6.homework.ui.zoolist.ZooListFragment


class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initToolbar(getString(R.string.home_list), binding.vToolbar, false)

        val listFragment = ZooListFragment()
        supportFragmentManager.beginTransaction()
            .setReorderingAllowed(true)
            .replace(binding.vContent.id, listFragment)
            .commit()
    }
}