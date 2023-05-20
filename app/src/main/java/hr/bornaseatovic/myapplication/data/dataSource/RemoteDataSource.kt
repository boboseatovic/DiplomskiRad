package hr.bornaseatovic.myapplication.data.dataSource

import hr.bornaseatovic.myapplication.data.Resource
import hr.bornaseatovic.myapplication.data.dataSource.local.Database
import hr.bornaseatovic.myapplication.data.dataSource.remote.GeolocationAPI
import hr.bornaseatovic.myapplication.data.dataSource.remote.PVgisAPI
import hr.bornaseatovic.myapplication.data.parsers.toGeolocationPresentation
import hr.bornaseatovic.myapplication.data.parsers.toProductionDb
import hr.shape.data.errors.ErrorParser
import kotlinx.coroutines.flow.flow

class RemoteDataSource(
    private val pVgisAPI: PVgisAPI,
    private val geolocationAPI: GeolocationAPI,
    private val database: Database
) {
    suspend fun fetchPVCalculations(lat: Float, long: Float) = flow {
        try {
            val response = pVgisAPI.fetchPVCalculations(lat, long)
            database.dao.insertProduction(response.toProductionDb())
            emit(Resource.Success(Unit))
        } catch (e:Exception) {
            emit(Resource.Error(errorType = ErrorParser.parseError(e)))
        }
    }


    suspend fun fetchGeolocation(searchValue: String) = flow {
        try {
            val response = geolocationAPI.getGeolocation(query = searchValue)
            emit(Resource.Success(response.toGeolocationPresentation()))
        } catch (e: java.lang.Exception) {
            emit(Resource.Error(errorType = ErrorParser.parseError(e)))
        }
    }



}