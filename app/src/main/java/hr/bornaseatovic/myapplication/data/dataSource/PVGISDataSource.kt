package hr.bornaseatovic.myapplication.data.dataSource

import hr.bornaseatovic.myapplication.data.Resource
import hr.bornaseatovic.myapplication.data.dataSource.remote.PVgisAPI
import hr.bornaseatovic.myapplication.data.model.presentation.PVCalculationsPresentation
import hr.bornaseatovic.myapplication.data.parsers.toPVCalculationsPresentation
import hr.shape.data.errors.ErrorParser
import kotlinx.coroutines.flow.flow

class PVGISDataSource(
    private val pVgisAPI: PVgisAPI
) {

    suspend fun fetchPVCalculations(lat: Float, long: Float) = flow<Resource<PVCalculationsPresentation>> {
        try {
            val response = pVgisAPI.fetchPVCalculations(lat, long)
            emit(Resource.Success(response.toPVCalculationsPresentation()))
        } catch (e:Exception) {
            emit(Resource.Error(errorType = ErrorParser.parseError(e)))
        }
    }

}