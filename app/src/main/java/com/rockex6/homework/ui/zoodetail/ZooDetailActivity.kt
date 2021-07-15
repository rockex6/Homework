package com.rockex6.homework.ui.zoodetail

import android.os.Bundle
import com.rockex6.homework.BaseActivity
import com.rockex6.homework.R
import com.rockex6.homework.databinding.ActivityZooDetailBinding
import com.rockex6.homework.ui.zoolist.model.ZooListResult

class ZooDetailActivity : BaseActivity() {


    private lateinit var binding: ActivityZooDetailBinding
    private val data get() = intent.extras!!.getSerializable("data") as ZooListResult
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityZooDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val zooDetailFragment = ZooDetailFragment()
        zooDetailFragment.arguments = intent.extras

        initToolbar(data.E_Name, binding.vToolbar, true)
        supportFragmentManager.beginTransaction()
            .replace(R.id.vDetailContent, zooDetailFragment)
            .commitNowAllowingStateLoss()

    }

    override fun onBackPressed() {
        val fm = supportFragmentManager
        for (frag in fm.fragments) {
            if (frag.isVisible && frag.isResumed) {
                val childFm = frag.childFragmentManager
                if (childFm.backStackEntryCount > 0) {
                    supportActionBar?.title = data.E_Name
                    childFm.popBackStack()
                    return
                }
            }
        }
        super.onBackPressed()
    }
}