package hr.bornaseatovic.myapplication.data.model.remote


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Outputs(
    @SerialName("monthly")
    val monthly: Monthly,
    @SerialName("totals")
    val totals: Totals
)