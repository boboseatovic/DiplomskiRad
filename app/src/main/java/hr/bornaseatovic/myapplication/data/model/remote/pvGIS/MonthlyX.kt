package hr.bornaseatovic.myapplication.data.model.remote.pvGIS


import com.google.gson.annotations.SerializedName

data class MonthlyX(
    @SerializedName("fixed")
    val fixed: List<FixedX>
)