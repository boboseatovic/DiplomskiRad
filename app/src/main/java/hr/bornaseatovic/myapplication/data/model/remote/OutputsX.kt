package hr.bornaseatovic.myapplication.data.model.remote


import com.google.gson.annotations.SerializedName

data class OutputsX(
    @SerializedName("monthly")
    val monthly: MonthlyX,
    @SerializedName("totals")
    val totals: TotalsX
)