package hr.bornaseatovic.myapplication.data.model.remote


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Totals(
    @SerialName("type")
    val type: String,
    @SerialName("variables")
    val variables: VariablesXXXXX
)