package com.rockex6.homework.ui.zoodetail

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.transition.TransitionInflater
import com.rockex6.homework.R
import com.rockex6.homework.databinding.FragmentZooDetailBinding
import com.rockex6.homework.loadImage
import com.rockex6.homework.ui.webview.WebActivity
import com.rockex6.homework.ui.zoodetail.model.ZooPlantModel
import com.rockex6.homework.ui.zoolist.model.ZooListResult


class ZooDetailFragment : Fragment(), ZooDetailView {

    private var _binding: FragmentZooDetailBinding? = null
    private val binding get() = _binding!!
    private val zooDetailPresenter by lazy { context?.let { ZooDetailPresenterCompl(it, this) } }
    private val data get() = requireArguments()["data"] as ZooListResult

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentZooDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        binding.vZooDescription.text = data.E_Info
        binding.vZooArea.text = data.E_Category
        binding.vZooImg.transitionName = data.E_no
        binding.vZooUrl.setOnClickListener {
            val intent = Intent(context, WebActivity::class.java)
            intent.putExtras(WebActivity.getWebActivityBundle(data.E_Name, data.E_URL))
            startActivity(intent)
        }
        context?.let { context ->
            binding.vZooMemo.text = if (data.E_Memo.isEmpty()) {
                context.getString(R.string.list_no_memo)
            } else {
                data.E_Memo
            }
            binding.vZooImg.loadImage(context, data.E_Pic_URL)
        }
        zooDetailPresenter?.getZooPlantList(data.E_Name)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = TransitionInflater.from(context)
            .inflateTransition(R.transition.transition_img)
        sharedElementReturnTransition = TransitionInflater.from(context)
            .inflateTransition(R.transition.transition_img)
    }

    override fun onZooPlantListGet(zooPlantList: ArrayList<ZooPlantModel>) {
        activity?.runOnUiThread {
            binding.vProgress.visibility = View.GONE
            binding.vPlantList.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = ZooPlantAdapter(context, zooPlantList) { zooPlantModel ->
                    (requireActivity() as AppCompatActivity).supportActionBar?.title =
                        zooPlantModel.F_Name_Ch
                    childFragmentManager.beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.vContent, ZooPlantFragment(zooPlantModel))
                        .commit()
                }
            }
        }
    }


    override fun onError(message: String) {
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}