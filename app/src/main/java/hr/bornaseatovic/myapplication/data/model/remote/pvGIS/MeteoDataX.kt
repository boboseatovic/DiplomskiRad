package hr.bornaseatovic.myapplication.data.model.remote.pvGIS


import com.google.gson.annotations.SerializedName

data class MeteoDataX(
    @SerializedName("description")
    val description: String,
    @SerializedName("variables")
    val variables: VariablesXX
)