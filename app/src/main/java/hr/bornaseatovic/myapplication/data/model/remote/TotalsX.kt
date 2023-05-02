package hr.bornaseatovic.myapplication.data.model.remote


import com.google.gson.annotations.SerializedName

data class TotalsX(
    @SerializedName("fixed")
    val fixed: FixedXX
)