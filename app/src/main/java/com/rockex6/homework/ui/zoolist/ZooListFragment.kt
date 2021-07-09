package com.rockex6.homework.ui.zoolist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.rockex6.homework.R
import com.rockex6.homework.databinding.FragmentZooListBinding
import com.rockex6.homework.ui.zoolist.model.ZooListModel
import com.rockex6.homework.ui.zoolist.model.ZooListResult

class ZooListFragment : Fragment(), ZooListView {


    private lateinit var _binding: FragmentZooListBinding
    private val zooListPresenter = ZooListPresenterCompl(this)
    private val navController
        get() = findNavController(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentZooListBinding.inflate(inflater, container, false)
        zooListPresenter.getZooList()
        _binding.aaa.setOnClickListener {
            val extras = FragmentNavigatorExtras(it to "bbb")
            navController.navigate(R.id.navToZooDetailFragment, null, null, extras)
        }
        return _binding.root
    }

    override fun onZooListGet(zooListModel: ZooListModel) {
        _binding.vZooList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = ZooListAdapter(zooListModel.result.results, object : ItemClickListener {
                override fun onItemClickListener(item: ZooListResult, imageView: ImageView) {
                    val extras = FragmentNavigatorExtras(imageView to "zoo_img")
                    val bundle = bundleOf("data" to item)
                    findNavController().navigate(R.id.navToZooDetailFragment, bundle, null, extras)
                }
            })
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }
    }

    override fun onError(message: String) {

    }
}