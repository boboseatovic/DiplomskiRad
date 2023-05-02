package hr.bornaseatovic.myapplication.data.model.remote


import com.google.gson.annotations.SerializedName

data class Monthly(
    @SerializedName("timestamp")
    val timestamp: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("variables")
    val variables: VariablesXXXX
)