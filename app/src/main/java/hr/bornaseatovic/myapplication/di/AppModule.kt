package hr.bornaseatovic.myapplication.di

import android.app.Application
import androidx.room.Room
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import hr.bornaseatovic.myapplication.data.dataSource.LocalDataSource
import hr.bornaseatovic.myapplication.data.dataSource.PVGISDataSource
import hr.bornaseatovic.myapplication.data.dataSource.local.Database
import hr.bornaseatovic.myapplication.data.dataSource.remote.PVgisAPI
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Named
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(app: Application): Database {
        return Room.databaseBuilder(
            app,
            Database::class.java,
            Database.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideLocalDataSource(
        database: Database
    ): LocalDataSource {
        return LocalDataSource(database)
    }

    @Provides
    @Singleton
    fun providePVGISDataSource(
        pVgisAPI: PVgisAPI
    ): PVGISDataSource {
        return  PVGISDataSource(pVgisAPI)
    }

    @Provides
    @Named("BaseHttpClient")
    fun getBaseHttpClient(): OkHttpClient {
        return OkHttpClient
            .Builder()
            .build()
    }

    @Provides
    fun getBaseJsonConfig(): Json =
        Json {
            ignoreUnknownKeys = true
            prettyPrint = true
        }

    @BaseRetrofit
    @Provides
    fun getBaseRetrofit(@Named("BaseHttpClient") client: OkHttpClient, json: Json): Retrofit {
        val contentType = "application/json".toMediaType()

        return Retrofit.Builder()
            .client(client)
            .addConverterFactory(json.asConverterFactory(contentType))
            .baseUrl("https://re.jrc.ec.europa.eu/")
            .build()
    }

    @Provides
    fun getPVgisAPI(@BaseRetrofit retrofit: Retrofit): PVgisAPI =
        retrofit.create(PVgisAPI::class.java)
}