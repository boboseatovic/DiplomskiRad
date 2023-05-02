package hr.bornaseatovic.myapplication.data.model.remote


import com.google.gson.annotations.SerializedName

data class PVCalculationsResponse(
    @SerializedName("inputs")
    val inputs: Inputs,
    @SerializedName("meta")
    val meta: Meta,
    @SerializedName("outputs")
    val outputs: OutputsX
)