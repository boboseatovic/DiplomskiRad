package hr.bornaseatovic.myapplication.data.model.remote.pvGIS


import com.google.gson.annotations.SerializedName

data class Fields(
    @SerializedName("azimuth")
    val azimuth: AzimuthX,
    @SerializedName("slope")
    val slope: SlopeX
)