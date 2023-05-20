package hr.bornaseatovic.myapplication.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ExampleDb(
    @PrimaryKey val key: Int = 0,
    val example: String = ""
)
