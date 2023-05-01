package hr.bornaseatovic.myapplication.data.model.remote


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MonthlyX(
    @SerialName("fixed")
    val fixed: List<FixedX>
)