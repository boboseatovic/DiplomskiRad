package hr.bornaseatovic.myapplication.data.model.remote


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EconomicData(
    @SerialName("interest")
    val interest: Any,
    @SerialName("lifetime")
    val lifetime: Any,
    @SerialName("system_cost")
    val systemCost: Any
)