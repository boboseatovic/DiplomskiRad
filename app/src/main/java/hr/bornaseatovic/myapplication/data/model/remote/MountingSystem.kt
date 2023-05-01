package hr.bornaseatovic.myapplication.data.model.remote


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MountingSystem(
    @SerialName("fixed")
    val fixed: Fixed
)