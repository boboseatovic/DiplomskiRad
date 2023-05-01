package hr.bornaseatovic.myapplication.data.model.presentation


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FixedX(
    val eD: Double,
    val eM: Double,
    val eY: Double,
    val hiD: Double,
    val hiM: Double,
    val hiY: Double,
    val lAoi: Double,
    val lSpec: String,
    val lTg: Double,
    val lTotal: Double,
    val sDM: Double,
    val sDY: Double
)