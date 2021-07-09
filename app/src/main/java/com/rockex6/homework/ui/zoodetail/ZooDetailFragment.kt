package com.rockex6.homework.ui.zoodetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.transition.TransitionInflater
import com.rockex6.homework.databinding.FragmentZooDetailBinding
import com.rockex6.homework.loadImage
import com.rockex6.homework.ui.zoolist.model.ZooListResult

class ZooDetailFragment : Fragment() {
    private lateinit var _binding: FragmentZooDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentZooDetailBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            val data = it["data"] as ZooListResult
            context?.let { it1 -> _binding.vZooImg.loadImage(it1, data.E_Pic_URL) }
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = TransitionInflater.from(context)
            .inflateTransition(android.R.transition.move)
    }
}