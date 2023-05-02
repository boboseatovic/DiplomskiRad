package hr.bornaseatovic.myapplication.data.model.remote


import com.google.gson.annotations.SerializedName

data class Variables(
    @SerializedName("interest")
    val interest: Interest,
    @SerializedName("lifetime")
    val lifetime: Lifetime,
    @SerializedName("system_cost")
    val systemCost: SystemCost
)