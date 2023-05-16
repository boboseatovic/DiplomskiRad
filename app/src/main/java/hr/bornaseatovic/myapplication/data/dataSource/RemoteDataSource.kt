package hr.bornaseatovic.myapplication.data.dataSource

import hr.bornaseatovic.myapplication.data.Resource
import hr.bornaseatovic.myapplication.data.dataSource.remote.GeolocationAPI
import hr.bornaseatovic.myapplication.data.dataSource.remote.PVgisAPI
import hr.bornaseatovic.myapplication.data.model.presentation.GeolocationPresentation
import hr.bornaseatovic.myapplication.data.model.presentation.PVCalculationsPresentation
import hr.bornaseatovic.myapplication.data.parsers.toGeolocationPresentation
import hr.bornaseatovic.myapplication.data.parsers.toPVCalculationsPresentation
import hr.shape.data.errors.ErrorParser
import kotlinx.coroutines.flow.flow

class RemoteDataSource(
    private val pVgisAPI: PVgisAPI,
    private val geolocationAPI: GeolocationAPI
) {

    suspend fun fetchPVCalculations(lat: Float, long: Float) = flow {
        try {
            val response = pVgisAPI.fetchPVCalculations(lat, long)
            emit(Resource.Success(response.toPVCalculationsPresentation()))
        } catch (e:Exception) {
            emit(Resource.Error(errorType = ErrorParser.parseError(e)))
        }
    }

    suspend fun fetchGeolocation() = flow {
        try {
            val response = geolocationAPI.getGeolocation()
            emit(Resource.Success(response.toGeolocationPresentation()))
        } catch (e: java.lang.Exception) {
            emit(Resource.Error(errorType = ErrorParser.parseError(e)))
        }
    }



}