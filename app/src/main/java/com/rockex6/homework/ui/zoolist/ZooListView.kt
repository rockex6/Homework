package com.rockex6.homework.ui.zoolist

import com.rockex6.homework.api.BaseView
import com.rockex6.homework.ui.zoolist.model.ZooListModel

interface ZooListView : BaseView {
    fun onZooListGet(zooListModel: ZooListModel)
}