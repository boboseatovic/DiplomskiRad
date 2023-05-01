package hr.bornaseatovic.myapplication.data.model.presentation


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Totals(
    val fixed: FixedX
)