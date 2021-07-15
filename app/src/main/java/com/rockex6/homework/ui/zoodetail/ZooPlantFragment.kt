package com.rockex6.homework.ui.zoodetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.transition.TransitionInflater
import com.rockex6.homework.databinding.FragmentZooPlantBinding
import com.rockex6.homework.loadImage
import com.rockex6.homework.ui.zoodetail.model.ZooPlantModel

class ZooPlantFragment(private val zooPlantModel: ZooPlantModel) : Fragment() {


    private var _binding: FragmentZooPlantBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentZooPlantBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        context?.let { binding.vPlantImg.loadImage(it, zooPlantModel.F_Pic01_URL) }
        binding.vPlantContent.text = zooPlantModel.getString()

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = TransitionInflater.from(context)
            .inflateTransition(android.R.transition.move)
        sharedElementReturnTransition = TransitionInflater.from(context)
            .inflateTransition(android.R.transition.move)
    }
}