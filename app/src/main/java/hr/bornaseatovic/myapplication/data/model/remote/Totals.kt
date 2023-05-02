package hr.bornaseatovic.myapplication.data.model.remote


import com.google.gson.annotations.SerializedName

data class Totals(
    @SerializedName("type")
    val type: String,
    @SerializedName("variables")
    val variables: VariablesXXXXX
)