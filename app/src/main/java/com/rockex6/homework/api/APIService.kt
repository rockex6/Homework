package com.rockex6.homework.api

import com.rockex6.homework.home.model.ZooListModel
import retrofit2.Call
import retrofit2.http.GET

interface APIService {


    @GET("5a0e5fbb-72f8-41c6-908e-2fb25eff9b8a?scope=resourceAquire")
    fun getZooListAPI(): Call<ZooListModel>


}