package hr.bornaseatovic.myapplication.data.dataSource.remote


import hr.bornaseatovic.myapplication.data.model.remote.geolocation.GeolocationResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface GeolocationAPI {

    @GET("forward")
    suspend fun getGeolocation(
        @Query("access_key") accessKey : String = "6c4611357976fc81217eaddcb9f5964f",
        @Query("query") query: String = "Našička ulica 61D"
    ): GeolocationResponse
}