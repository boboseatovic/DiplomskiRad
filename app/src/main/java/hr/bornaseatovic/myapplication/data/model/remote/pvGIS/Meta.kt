package hr.bornaseatovic.myapplication.data.model.remote.pvGIS


import com.google.gson.annotations.SerializedName

data class Meta(
    @SerializedName("inputs")
    val inputs: InputsX,
    @SerializedName("outputs")
    val outputs: Outputs
)