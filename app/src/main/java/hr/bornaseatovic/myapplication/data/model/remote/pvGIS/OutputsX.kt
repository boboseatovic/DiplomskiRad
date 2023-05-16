package hr.bornaseatovic.myapplication.data.model.remote.pvGIS


import com.google.gson.annotations.SerializedName

data class OutputsX(
    @SerializedName("monthly")
    val monthly: MonthlyX,
    @SerializedName("totals")
    val totals: TotalsX
)