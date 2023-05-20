package hr.bornaseatovic.myapplication.data.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ProductionDb(
    @PrimaryKey val productionKey: String = "productionKey",
    val monthlyProduction: List<Double>,
    val yearlyProduction: Double
)