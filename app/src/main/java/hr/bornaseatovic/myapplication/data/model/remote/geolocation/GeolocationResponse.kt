package hr.bornaseatovic.myapplication.data.model.remote.geolocation


import com.google.gson.annotations.SerializedName

data class GeolocationResponse(
    @SerializedName("data")
    val `data`: List<Data>?
)