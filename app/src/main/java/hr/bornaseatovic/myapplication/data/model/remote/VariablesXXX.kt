package hr.bornaseatovic.myapplication.data.model.remote


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VariablesXXX(
    @SerialName("peak_power")
    val peakPower: PeakPower,
    @SerialName("system_loss")
    val systemLoss: SystemLoss,
    @SerialName("technology")
    val technology: Technology
)