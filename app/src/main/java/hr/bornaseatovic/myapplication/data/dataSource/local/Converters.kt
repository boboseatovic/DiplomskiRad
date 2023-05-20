package hr.bornaseatovic.myapplication.data.dataSource.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

inline fun <reified T> Gson.fromJson(json: String) =
    fromJson<T>(json, object : TypeToken<T>() {}.type)

class Converters {
    @TypeConverter
    fun saveListOfDoubles(value: List<Double>): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun getListOfDoubles(value: String): List<Double> {
        return Gson().fromJson<List<Double>>(value)
    }
}