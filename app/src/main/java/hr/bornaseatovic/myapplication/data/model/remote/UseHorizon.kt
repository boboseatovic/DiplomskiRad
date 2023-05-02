package hr.bornaseatovic.myapplication.data.model.remote


import com.google.gson.annotations.SerializedName

data class UseHorizon(
    @SerializedName("description")
    val description: String
)