package com.rockex6.homework.ui.zoodetail

import com.rockex6.homework.api.BaseView
import com.rockex6.homework.ui.zoodetail.model.ZooPlantModel

interface ZooDetailView : BaseView {

    fun onZooPlantListGet(zooPlantList: ArrayList<ZooPlantModel>)
}