package hr.bornaseatovic.myapplication.data.model.remote


import com.google.gson.annotations.SerializedName

data class HiM(
    @SerializedName("description")
    val description: String,
    @SerializedName("units")
    val units: String
)