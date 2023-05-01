package hr.bornaseatovic.myapplication.data.model.remote


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Variables(
    @SerialName("interest")
    val interest: Interest,
    @SerialName("lifetime")
    val lifetime: Lifetime,
    @SerialName("system_cost")
    val systemCost: SystemCost
)