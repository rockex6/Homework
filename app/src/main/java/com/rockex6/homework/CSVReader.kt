package com.rockex6.homework

import com.github.doyaaaaaken.kotlincsv.dsl.csvReader
import org.json.JSONArray
import org.json.JSONObject
import java.io.InputStream

class CSVReader(
    private val inputStream: InputStream,
    private val charset: String,
    private val callback: (JSONObject) -> Unit) : Thread() {

    init {
        start()
    }

    override fun run() {
        super.run()
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