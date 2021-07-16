package com.rockex6.homework.ui.zoolist

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.util.Pair
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.rockex6.homework.databinding.FragmentZooListBinding
import com.rockex6.homework.ui.zoodetail.ZooDetailActivity
import com.rockex6.homework.ui.zoolist.model.ZooListResults


class ZooListFragment : Fragment(), ZooListView {


    private var _binding: FragmentZooListBinding? = null
    private val binding get() = _binding!!
    private val zooListPresenter by lazy { context?.let { ZooListPresenterCompl(it, this) } }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentZooListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getZooList()
    }

    override fun onZooListGet(zooListResult: ZooListResults) {
        activity?.runOnUiThread {
            binding.vProgressBar.visibility = View.GONE
            binding.vZooList.apply {
                layoutManager = LinearLayoutManager(context)
                adapter =
                    ZooListAdapter(context, zooListResult.results) { zooListResult, imageView ->
                        val pairs = Pair<View, String>(imageView, zooListResult.E_no)
                        val options = ActivityOptions.makeSceneTransitionAnimation(activity, pairs)
                        val i = Intent(activity, ZooDetailActivity::class.java)
                        i.putExtras(bundleOf("data" to zooListResult))
                        if (options != null) {
                            startActivity(i, options.toBundle())
                        } else {
                            startActivity(i)
                        }
                    }
                addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            }

        }
    }

    private fun getZooList() {
        binding.vProgressBar.visibility = View.VISIBLE
        binding.refreshRL.refreshRL.visibility = View.GONE
        zooListPresenter?.getZooList()
    }

    override fun onError(message: String) {
        binding.vProgressBar.visibility = View.GONE
        binding.refreshRL.refreshRL.visibility = View.VISIBLE
        binding.refreshRL.alertTV.text = message
        binding.refreshRL.refreshRL.setOnClickListener {
            getZooList()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}