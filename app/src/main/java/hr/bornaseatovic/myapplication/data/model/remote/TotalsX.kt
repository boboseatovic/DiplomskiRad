package hr.bornaseatovic.myapplication.data.model.remote


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TotalsX(
    @SerialName("fixed")
    val fixed: FixedXX
)