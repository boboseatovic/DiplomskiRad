package hr.bornaseatovic.myapplication.data.dataSource


import hr.bornaseatovic.myapplication.data.dataSource.local.Database
import hr.bornaseatovic.myapplication.data.model.ExampleDb


class LocalDataSource(
    private val database: Database
) {
    suspend fun insertExampleDb(exampleDb: ExampleDb) = database.dao.insertExampleDb(exampleDb)

    fun getExampleDb() = database.dao.getExampleDb()
}