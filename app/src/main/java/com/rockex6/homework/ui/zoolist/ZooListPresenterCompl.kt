package com.rockex6.homework.ui.zoolist

import com.rockex6.homework.api.APIManager
import com.rockex6.homework.api.APIService
import com.rockex6.homework.ui.zoolist.model.ZooListModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ZooListPresenterCompl(private val zooListView: ZooListView) : ZooListPresenter {

    private var retrofit = APIManager.getRetrofit("https://data.taipei/api/v1/dataset/")
        .create(APIService::class.java)

    override fun getZooList() {
        retrofit.getZooListAPI()
            .enqueue(object : Callback<ZooListModel?> {
                override fun onResponse(
                    call: Call<ZooListModel?>, response: Response<ZooListModel?>) {
                    if (response.isSuccessful) {
                        response.body()
                            ?.let {
                                zooListView.onZooListGet(it)
                            }
                    } else {
                        zooListView.onError(response.message())
                    }
                }

                override fun onFailure(call: Call<ZooListModel?>, t: Throwable) {
                    zooListView.onError(t.cause?.message.toString())
                }
            })
    }
}