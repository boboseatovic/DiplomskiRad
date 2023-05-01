package hr.bornaseatovic.myapplication.data.model.remote


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class APICallResponse(
    @SerialName("inputs")
    val inputs: Inputs,
    @SerialName("meta")
    val meta: Meta,
    @SerialName("outputs")
    val outputs: OutputsX
)