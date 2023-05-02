package hr.bornaseatovic.myapplication.data.model.remote


import com.google.gson.annotations.SerializedName

data class MeteoDb(
    @SerializedName("description")
    val description: String
)