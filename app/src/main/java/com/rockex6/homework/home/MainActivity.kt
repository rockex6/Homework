package com.rockex6.homework.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.rockex6.homework.api.Logger
import com.rockex6.homework.databinding.ActivityMainBinding
import com.rockex6.homework.home.model.ZooListModel

class MainActivity : AppCompatActivity(), ZooListView {

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val zooListPresenter = ZooListPresenterCompl(this)
        zooListPresenter.getZooList()

    }


    override fun onZooListGet(zooListModel: ZooListModel) {
        binding.vZooList.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = ZooListAdapter(zooListModel.result.results)
            addItemDecoration(
                DividerItemDecoration(this@MainActivity, DividerItemDecoration.VERTICAL))
        }
    }

    override fun onError(message: String) {
        Logger.e("AAA", message)
    }

}