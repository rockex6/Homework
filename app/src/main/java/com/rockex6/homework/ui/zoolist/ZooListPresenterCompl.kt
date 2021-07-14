package com.rockex6.homework.ui.zoolist

import android.content.Context
import com.rockex6.homework.CSVReader
import com.rockex6.homework.api.APIManager
import com.rockex6.homework.api.APIService
import com.rockex6.homework.api.DataParser
import com.rockex6.homework.ui.zoolist.model.ZooListModel
import com.rockex6.homework.ui.zoolist.model.ZooListResults
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ZooListPresenterCompl(private val context: Context, private val zooListView: ZooListView) :
    ZooListPresenter {

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
                                zooListView.onZooListGet(it.result)
                            }
                    } else {
                        zooListView.onError(response.message())
                    }
                }

                override fun onFailure(call: Call<ZooListModel?>, t: Throwable) {
                    try {
                        getZooListFromCSV()
                    } catch (e: Exception) {
                        zooListView.onError(t.cause?.message.toString())
                    }
                }
            })
    }

    private fun getZooListFromCSV() {
        CSVReader(context.assets.open("ist.csv"), "Big5") {
            val zooListResults = DataParser.getGson()
                .fromJson(it.toString(), ZooListResults::class.java)
            zooListView.onZooListGet(zooListResults)
        }
    }
}