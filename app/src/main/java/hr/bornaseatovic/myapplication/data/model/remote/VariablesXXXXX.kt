package hr.bornaseatovic.myapplication.data.model.remote


import com.google.gson.annotations.SerializedName

data class VariablesXXXXX(
    @SerializedName("E_d")
    val eD: ED,
    @SerializedName("E_m")
    val eM: EM,
    @SerializedName("E_y")
    val eY: EY,
    @SerializedName("H(i)_d")
    val hiD: HiD,
    @SerializedName("H(i)_m")
    val hiM: HiM,
    @SerializedName("H(i)_y")
    val hiY: HiY,
    @SerializedName("l_aoi")
    val lAoi: LAoi,
    @SerializedName("l_spec")
    val lSpec: LSpec,
    @SerializedName("l_tg")
    val lTg: LTg,
    @SerializedName("l_total")
    val lTotal: LTotal,
    @SerializedName("SD_m")
    val sDM: SDM,
    @SerializedName("SD_y")
    val sDY: SDY
)