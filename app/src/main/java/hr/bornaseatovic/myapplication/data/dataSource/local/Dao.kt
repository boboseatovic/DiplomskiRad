package hr.bornaseatovic.myapplication.data.dataSource.local
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import hr.bornaseatovic.myapplication.data.model.local.ProductionDb

@Dao
interface Dao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertProduction(ProductionDb: ProductionDb)

    @Query("SELECT * FROM productionDb")
    suspend fun getMonthlyProduction(): ProductionDb?
}