package hr.bornaseatovic.myapplication.data.model.remote


import com.google.gson.annotations.SerializedName

data class Technology(
    @SerializedName("description")
    val description: String
)