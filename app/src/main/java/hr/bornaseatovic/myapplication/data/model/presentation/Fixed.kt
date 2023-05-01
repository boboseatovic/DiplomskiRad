package hr.bornaseatovic.myapplication.data.model.presentation


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Fixed(
    val eD: Double,
    val eM: Double,
    val hiD: Double,
    val hiM: Double,
    val month: Int,
    val sDM: Double
)