package hr.bornaseatovic.myapplication.data.model.remote


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MeteoDataX(
    @SerialName("description")
    val description: String,
    @SerialName("variables")
    val variables: VariablesXX
)