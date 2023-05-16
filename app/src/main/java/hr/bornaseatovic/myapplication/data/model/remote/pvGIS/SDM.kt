package hr.bornaseatovic.myapplication.data.model.remote.pvGIS


import com.google.gson.annotations.SerializedName

data class SDM(
    @SerializedName("description")
    val description: String,
    @SerializedName("units")
    val units: String
)