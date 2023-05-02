package hr.bornaseatovic.myapplication.data.model.remote


import com.google.gson.annotations.SerializedName

data class MonthlyX(
    @SerializedName("fixed")
    val fixed: List<FixedX>
)