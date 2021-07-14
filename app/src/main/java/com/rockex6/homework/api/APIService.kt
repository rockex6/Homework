package com.rockex6.homework.api

import com.rockex6.homework.ui.zoolist.model.ZooListModel
import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {


    @GET("5a0e5fbb-72f8-41c6-908e-2fb25eff9b8a?scope=resourceAquire")
    fun getZooListAPI(): Call<ZooListModel>


    @GET("f18de02f-b6c9-47c0-8cda-50efad621c14?scope=resourceAquire")
    fun getZooPlantList(@Query("q") keyword: String): Call<JSONObject>

}