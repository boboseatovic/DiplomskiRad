package hr.bornaseatovic.myapplication.data.dataSource.local
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import hr.bornaseatovic.myapplication.data.model.ExampleDb

@Dao
interface Dao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExampleDb(exampleDb: ExampleDb)

    @Query("SELECT * FROM exampleDb")
    fun getExampleDb(): ExampleDb

}