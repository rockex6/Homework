package com.rockex6.homework.ui.zoolist.model

import java.io.Serializable

data class ZooListModel(
    val result: ZooListResults)


data class ZooListResults(
    val results: MutableList<ZooListResult>)


data class ZooListResult(
    val E_Pic_URL: String,
    val E_Geo: String,
    val E_Info: String,
    val E_no: String,
    val E_Category: String,
    val E_Name: String,
    val _id: Int,
    val E_URL: String,
    var E_Memo: String) : Serializable