package hr.bornaseatovic.myapplication.data.model.remote


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MountingSystemX(
    @SerialName("choices")
    val choices: String,
    @SerialName("description")
    val description: String,
    @SerialName("fields")
    val fields: Fields
)