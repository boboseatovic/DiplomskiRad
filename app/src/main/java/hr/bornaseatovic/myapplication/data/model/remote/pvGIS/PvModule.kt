package hr.bornaseatovic.myapplication.data.model.remote.pvGIS


import com.google.gson.annotations.SerializedName

data class PvModule(
    @SerializedName("peak_power")
    val peakPower: Double,
    @SerializedName("system_loss")
    val systemLoss: Double,
    @SerializedName("technology")
    val technology: String
)