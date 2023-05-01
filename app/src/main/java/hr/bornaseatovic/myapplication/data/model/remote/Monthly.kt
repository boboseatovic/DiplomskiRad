package hr.bornaseatovic.myapplication.data.model.remote


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Monthly(
    @SerialName("timestamp")
    val timestamp: String,
    @SerialName("type")
    val type: String,
    @SerialName("variables")
    val variables: VariablesXXXX
)