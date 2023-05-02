package hr.bornaseatovic.myapplication.data.model.remote


import com.google.gson.annotations.SerializedName

data class RadiationDb(
    @SerializedName("description")
    val description: String
)