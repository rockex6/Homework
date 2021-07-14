package com.rockex6.homework.ui.zoodetail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.rockex6.homework.R

class ZooDetailActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_zoo_detail)
        val zooDetailFragment = ZooDetailFragment()
        val bundle = intent.extras
        zooDetailFragment.arguments = bundle
        supportFragmentManager.beginTransaction()
            .replace(R.id.vDetailContent, zooDetailFragment)
            .commit()
    }
}