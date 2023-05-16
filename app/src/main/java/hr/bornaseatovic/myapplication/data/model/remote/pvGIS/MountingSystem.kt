package hr.bornaseatovic.myapplication.data.model.remote.pvGIS


import com.google.gson.annotations.SerializedName

data class MountingSystem(
    @SerializedName("fixed")
    val fixed: Fixed
)