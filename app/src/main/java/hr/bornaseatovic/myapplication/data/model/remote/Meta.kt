package hr.bornaseatovic.myapplication.data.model.remote


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Meta(
    @SerialName("inputs")
    val inputs: InputsX,
    @SerialName("outputs")
    val outputs: Outputs
)