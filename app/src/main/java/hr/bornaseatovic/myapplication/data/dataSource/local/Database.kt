package hr.bornaseatovic.myapplication.data.dataSource.local

import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import hr.bornaseatovic.myapplication.data.model.local.ProductionDb

@androidx.room.Database(
    entities = [
        ProductionDb::class,
               ],
    version = 1
)

@TypeConverters(Converters::class)
abstract class Database: RoomDatabase() {

    abstract val dao: Dao

    companion object {
        const val DATABASE_NAME = "db"
    }
}