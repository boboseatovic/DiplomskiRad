package hr.bornaseatovic.myapplication.data.dataSource.remote

import hr.bornaseatovic.myapplication.data.model.remote.pvGIS.PVCalculationsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface PVgisAPI {
    @GET("api/PVcalc")
    suspend fun fetchPVCalculations(
        @Query("lat") lat: Float,
        @Query("lon") long: Float,
        @Query("peakpower") peakPower: Float = 1f,
        @Query("loss") loss: Float = 14f,
        @Query("outputformat") outputFormat: String = "json",
        @Query("usehorizon") useHorizon: Int = 1,
        @Query("raddatabase") radDatabase: String = "PVGIS-SARAH",
        @Query("mountingplace") mountingPlace: String = "building",
        @Query("fixed") fixed: Int = 1,
        @Query("angle") angle: Float = 0f,
        @Query("aspect") aspect: Float = 0f

    ): PVCalculationsResponse
}