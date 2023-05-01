package hr.bornaseatovic.myapplication.data.model.presentation


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Monthly(
    val fixed: List<Fixed>
)