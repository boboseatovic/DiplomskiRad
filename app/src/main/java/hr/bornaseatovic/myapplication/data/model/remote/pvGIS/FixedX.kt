package hr.bornaseatovic.myapplication.data.model.remote.pvGIS


import com.google.gson.annotations.SerializedName

data class FixedX(
    @SerializedName("E_d")
    val eD: Double,
    @SerializedName("E_m")
    val eM: Double,
    @SerializedName("H(i)_d")
    val hiD: Double,
    @SerializedName("H(i)_m")
    val hiM: Double,
    @SerializedName("month")
    val month: Int,
    @SerializedName("SD_m")
    val sDM: Double
)