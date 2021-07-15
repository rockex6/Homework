package com.rockex6.homework

import com.github.doyaaaaaken.kotlincsv.dsl.csvReader
import org.json.JSONArray
import org.json.JSONObject
import java.io.InputStream
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit

object CSVReader {
    private val threadPoolExecutor =
        ThreadPoolExecutor(2, 3, 60, TimeUnit.SECONDS, LinkedBlockingQueue())


    fun getDataFromCsv(inputStream: InputStream, charset: String, callback: (JSONObject) -> Unit) {
        threadPoolExecutor.execute {

            val fileText = inputStream.bufferedReader(charset(charset))
                .use {
                    it.readText()
                }

            val tsvReader = csvReader {}
            var rows = tsvReader.readAll(fileText)
            val columns = rows[0]
            rows = rows.drop(1)
            val result = JSONObject()
            result.put("results", JSONArray())
            for (i in rows.indices) {
                val jsonObject = JSONObject()
                for (a in columns.indices) {
                    jsonObject.put(columns[a], rows[i][a])
                }
                result.getJSONArray("results")
                    .put(jsonObject)
            }
            callback.invoke(result)
        }
    }
}