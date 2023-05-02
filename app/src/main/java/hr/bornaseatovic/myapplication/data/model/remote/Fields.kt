package hr.bornaseatovic.myapplication.data.model.remote


import com.google.gson.annotations.SerializedName

data class Fields(
    @SerializedName("azimuth")
    val azimuth: AzimuthX,
    @SerializedName("slope")
    val slope: SlopeX
)