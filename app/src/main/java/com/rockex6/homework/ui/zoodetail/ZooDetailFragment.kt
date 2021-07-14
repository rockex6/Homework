package com.rockex6.homework.ui.zoodetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.transition.TransitionInflater
import com.rockex6.homework.R
import com.rockex6.homework.databinding.FragmentZooDetailBinding
import com.rockex6.homework.ui.zoodetail.model.ZooPlantModel
import com.rockex6.homework.ui.zoolist.model.ZooListResult


class ZooDetailFragment : Fragment(), ZooDetailView {

    private lateinit var _binding: FragmentZooDetailBinding
    private val zooDetailPresenter by lazy { context?.let { ZooDetailPresenterCompl(it, this) } }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentZooDetailBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val activity = activity as AppCompatActivity
        activity.setSupportActionBar(_binding.vToolbar)
        activity.supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        init()
    }


    private fun init() {
        arguments?.let {
            val data = it["data"] as ZooListResult
            _binding.vZooDescription.text = data.E_Info
            _binding.vZooArea.text = data.E_Category
            _binding.vZooImg.transitionName = data.E_no
            context?.let { context ->
                _binding.vZooMemo.text = if (data.E_Memo.isEmpty()) {
                    context.getString(R.string.list_no_memo)
                } else {
                    data.E_Memo
                }
                _binding.vZooImg.loadImage(context, data.E_Pic_URL)
            }

            zooDetailPresenter?.getZooPlantList(data.E_Name)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = TransitionInflater.from(context)
            .inflateTransition(R.transition.transition_img)
        sharedElementReturnTransition = TransitionInflater.from(context)
            .inflateTransition(R.transition.transition_img)
        setHasOptionsMenu(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> requireActivity().onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onZooPlantListGet(zooPlantList: ArrayList<ZooPlantModel>) {
        activity?.runOnUiThread {
            _binding.vProgress.visibility = View.GONE
            _binding.vPlantList.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = ZooPlantAdapter(context, zooPlantList, object : ItemClickListener {
                    override fun onItemClickListener(item: ZooPlantModel, imageView: ImageView) {

                    }
                })
            }
        }
    }

    override fun onError(message: String) {
    }
}