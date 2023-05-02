package hr.bornaseatovic.myapplication.data.model.remote


import com.google.gson.annotations.SerializedName

data class EconomicData(
    @SerializedName("interest")
    val interest: Any,
    @SerializedName("lifetime")
    val lifetime: Any,
    @SerializedName("system_cost")
    val systemCost: Any
)