package hr.bornaseatovic.myapplication.data.model.remote.geolocation


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("administrative_area")
    val administrativeArea: String?,
    @SerializedName("confidence")
    val confidence: Int?,
    @SerializedName("continent")
    val continent: String?,
    @SerializedName("country")
    val country: String?,
    @SerializedName("country_code")
    val countryCode: String?,
    @SerializedName("county")
    val county: String?,
    @SerializedName("label")
    val label: String?,
    @SerializedName("latitude")
    val latitude: Double?,
    @SerializedName("locality")
    val locality: String?,
    @SerializedName("longitude")
    val longitude: Double?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("neighbourhood")
    val neighbourhood: String?,
    @SerializedName("number")
    val number: String?,
    @SerializedName("postal_code")
    val postalCode: String?,
    @SerializedName("region")
    val region: String?,
    @SerializedName("region_code")
    val regionCode: String?,
    @SerializedName("street")
    val street: String?,
    @SerializedName("type")
    val type: String?
)