package hr.bornaseatovic.myapplication.data.model.remote.pvGIS


import com.google.gson.annotations.SerializedName

data class Fixed(
    @SerializedName("azimuth")
    val azimuth: Azimuth,
    @SerializedName("slope")
    val slope: Slope,
    @SerializedName("type")
    val type: String
)