package hr.bornaseatovic.myapplication.data.dataSource.remote

import hr.bornaseatovic.myapplication.data.model.remote.APICallResponse
import retrofit2.http.GET

interface PVgisAPI {

    @GET("api/PVcalc?lat=45&lon=8&peakpower=1&loss=14&outputformat=json")
    suspend fun getAPICall(): APICallResponse
}