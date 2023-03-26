package hr.bornaseatovic.myapplication.data.dataSource.local

import androidx.room.RoomDatabase
import hr.bornaseatovic.myapplication.data.model.ExampleDb

@androidx.room.Database(
    entities = [
        ExampleDb::class
               ],
    version = 1
)
abstract class Database: RoomDatabase() {

    abstract val dao: Dao

    companion object {
        const val DATABASE_NAME = "db"
    }
}