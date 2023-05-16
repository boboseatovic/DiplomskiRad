package hr.bornaseatovic.myapplication.data.model.remote.pvGIS


import com.google.gson.annotations.SerializedName

data class MountingSystemX(
    @SerializedName("choices")
    val choices: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("fields")
    val fields: Fields
)