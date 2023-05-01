package hr.bornaseatovic.myapplication.data.model.remote


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EconomicDataX(
    @SerialName("description")
    val description: String,
    @SerialName("variables")
    val variables: Variables
)