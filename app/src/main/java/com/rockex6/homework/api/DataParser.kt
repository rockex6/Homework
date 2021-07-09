package com.rockex6.homework.api

import com.google.gson.*
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import com.google.gson.stream.JsonWriter
import java.io.StringReader

class DataParser {

    companion object {
        private class IntTypeAdapter : TypeAdapter<Number>() {
            override fun write(out: JsonWriter?, value: Number?) {
                out?.value(value)
            }

            override fun read(reader: JsonReader?): Number? {
                if (reader?.peek() == JsonToken.NULL) {
                    reader.nextNull()
                    return -1
                }
                return try {
                    val result = reader?.nextString()
                    if ("" == result) -1 else result?.let { Integer.parseInt(it) }
                } catch (e: Exception) {
                    -1
                }
            }

        }

        private class StringTypeAdapter : TypeAdapter<String>() {
            override fun write(out: JsonWriter?, value: String?) {
                out?.value(value)
            }

            override fun read(reader: JsonReader?): String? {
                if (reader?.peek() == JsonToken.NULL) {
                    reader.nextNull()
                    return ""
                }
                return try {
                    val result = reader?.nextString()
                    if ("" == result) "" else result
                } catch (e: Exception) {
                    ""
                }
            }
        }

        private val gson = GsonBuilder().setLenient()
            .registerTypeAdapter(Int::class.java, IntTypeAdapter())
            .registerTypeAdapter(Integer::class.java, IntTypeAdapter())
            .registerTypeAdapter(String::class.java, StringTypeAdapter())
            .create()

        fun getGson(): Gson {
            return gson
        }


        /**
         * @param value 資料來源
         * @param c     model class
         * @return JSONObject 資料
         */
        @Throws(JsonIOException::class, JsonSyntaxException::class)
        fun <T> parsing(value: String?, c: Class<*>): T {
            val jsonSR = StringReader(value)
            val reader = JsonReader(jsonSR)
            reader.isLenient = true
            return gson.fromJson(reader, c)
        }
    }

}