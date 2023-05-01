package hr.bornaseatovic.myapplication.data.model.presentation


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class APICallPresentation(
    val monthly: Monthly,
    val totals: Totals
)