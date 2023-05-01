package hr.bornaseatovic.myapplication.data.model.remote


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OutputsX(
    @SerialName("monthly")
    val monthly: MonthlyX,
    @SerialName("totals")
    val totals: TotalsX
)