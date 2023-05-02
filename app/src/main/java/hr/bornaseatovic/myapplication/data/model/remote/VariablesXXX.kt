package hr.bornaseatovic.myapplication.data.model.remote


import com.google.gson.annotations.SerializedName

data class VariablesXXX(
    @SerializedName("peak_power")
    val peakPower: PeakPower,
    @SerializedName("system_loss")
    val systemLoss: SystemLoss,
    @SerializedName("technology")
    val technology: Technology
)