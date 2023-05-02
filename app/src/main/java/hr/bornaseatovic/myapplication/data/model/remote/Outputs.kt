package hr.bornaseatovic.myapplication.data.model.remote


import com.google.gson.annotations.SerializedName

data class Outputs(
    @SerializedName("monthly")
    val monthly: Monthly,
    @SerializedName("totals")
    val totals: Totals
)