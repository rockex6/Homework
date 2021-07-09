package com.rockex6.homework.home

import com.rockex6.homework.api.BaseView
import com.rockex6.homework.home.model.ZooListModel

interface ZooListView : BaseView {
    fun onZooListGet(zooListModel: ZooListModel)
}