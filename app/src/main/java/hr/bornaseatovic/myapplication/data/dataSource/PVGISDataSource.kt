package hr.bornaseatovic.myapplication.data.dataSource

import hr.bornaseatovic.myapplication.data.Resource
import hr.bornaseatovic.myapplication.data.dataSource.remote.PVgisAPI
import hr.bornaseatovic.myapplication.data.model.presentation.APICallPresentation
import hr.bornaseatovic.myapplication.data.parsers.toAPICallPresentation
import hr.shape.data.errors.ErrorParser
import kotlinx.coroutines.flow.flow

class PVGISDataSource(
    private val pVgisAPI: PVgisAPI
) {

    suspend fun getInfo() = flow<Resource<APICallPresentation>> {
        try {
            val response = pVgisAPI.getAPICall()
            emit(Resource.Success(response.toAPICallPresentation()))
        } catch (e:Exception) {
            emit(Resource.Error(errorType = ErrorParser.parseError(e)))
        }
    }

}