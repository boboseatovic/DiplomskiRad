package hr.bornaseatovic.myapplication.data.model.remote


import com.google.gson.annotations.SerializedName

data class MountingSystem(
    @SerializedName("fixed")
    val fixed: Fixed
)