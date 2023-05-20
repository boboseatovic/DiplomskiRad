package hr.bornaseatovic.myapplication.data.dataSource


import hr.bornaseatovic.myapplication.data.dataSource.local.Database


class LocalDataSource(
    private val database: Database
) {
    suspend fun getProduction() = database.dao.getMonthlyProduction()
}