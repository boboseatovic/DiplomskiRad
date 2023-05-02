package hr.bornaseatovic.myapplication.data.model.remote


import com.google.gson.annotations.SerializedName

data class Slope(
    @SerializedName("optimal")
    val optimal: Boolean,
    @SerializedName("value")
    val value: Int
)