package hr.bornaseatovic.myapplication.data.model.remote


import com.google.gson.annotations.SerializedName

data class LocationX(
    @SerializedName("description")
    val description: String,
    @SerializedName("variables")
    val variables: VariablesX
)