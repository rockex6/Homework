package com.rockex6.homework.ui.zoodetail

import android.content.Context
import android.os.Build
import com.rockex6.homework.CSVReader
import com.rockex6.homework.api.APIManager
import com.rockex6.homework.api.APIService
import com.rockex6.homework.api.DataParser
import com.rockex6.homework.ui.zoodetail.model.ZooPlantModel
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ZooDetailPresenterCompl(
    private val context: Context, private val zooDetailView: ZooDetailView) : ZooDetailPresenter {

    private var retrofit = APIManager.getRetrofit("https://data.taipei/api/v1/dataset/")
        .create(APIService::class.java)

    override fun getZooPlantList(keyword: String) {
        retrofit.getZooPlantList(keyword)
            .enqueue(object : Callback<JSONObject> {
                override fun onResponse(call: Call<JSONObject>, response: Response<JSONObject>) {
                    if (response.isSuccessful) {
                        if (response.body()
                                ?.length() == 0
                        ) {
                            getPlantListFromCSV(keyword)
                        } else {
//                            zooDetailView.onZooPlantListGet(result)
                        }

                    } else {
                        getPlantListFromCSV(keyword)
                    }
                }

                override fun onFailure(call: Call<JSONObject>, t: Throwable) {
                    try {
                        getPlantListFromCSV(keyword)
                    } catch (e: Exception) {
                        zooDetailView.onError(t.cause?.message.toString())
                    }
                }
            })
    }

    private fun getPlantListFromCSV(keyword: String) {
        CSVReader.getDataFromCsv(context.assets.open("zoo_plant.csv"), "UTF-8") {
            val jsonArray = it.optJSONArray("results")!!
            val tempList = ArrayList<ZooPlantModel>()
            val result = ArrayList<ZooPlantModel>()
            for (i in 0 until jsonArray.length()) {
                val data = DataParser.getGson()
                    .fromJson(jsonArray.getJSONObject(i)
                        .toString(), ZooPlantModel::class.java)
                tempList.add(data)
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                result.addAll(tempList.filter {
                    it.F_Location.contains(keyword)
                })
            } else {
                for (model in tempList) {
                    if (model.F_Location.contains(keyword)) {
                        result.add(model)
                    }
                }
            }

            zooDetailView.onZooPlantListGet(result)
        }
    }
}