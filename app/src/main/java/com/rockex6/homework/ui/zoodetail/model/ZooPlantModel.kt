package com.rockex6.homework.ui.zoodetail.model

import com.google.gson.annotations.SerializedName

data class ZooPlantModel(
    val F_Name_Ch: String,
    val F_Summary: String,
    val F_Keywords: String,
    val F_AlsoKnown: String,
    val F_Geo: String,
    val F_Location: String,
    val F_Name_En: String,
    val F_Name_Latin: String,
    val F_Family: String,
    val F_Genus: String,
    val F_Brief: String,
    val F_Feature: String,
    @SerializedName("F_Function&Application") val F_Function: String,
    val F_Code: String,
    val F_Pic01_ALT: String,
    val F_Pic01_URL: String,
    val F_Update: String)