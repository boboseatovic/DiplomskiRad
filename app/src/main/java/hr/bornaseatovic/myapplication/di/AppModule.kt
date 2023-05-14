package hr.bornaseatovic.myapplication.di

import android.app.Application
import androidx.room.Room
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import hr.bornaseatovic.myapplication.data.dataSource.LocalDataSource
import hr.bornaseatovic.myapplication.data.dataSource.PVGISDataSource
import hr.bornaseatovic.myapplication.data.dataSource.local.Database
import hr.bornaseatovic.myapplication.data.dataSource.remote.PVgisAPI
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
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
    @Singleton
    fun providePVgisAPI(): PVgisAPI {
        return Retrofit.Builder()
            .baseUrl("https://re.jrc.ec.europa.eu/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PVgisAPI::class.java)
    }

    @Singleton
    @Provides
    fun providesCoroutineScope(): CoroutineScope {
        return CoroutineScope(SupervisorJob() + Dispatchers.Default)
    }

    @Provides
    @Singleton
    fun provideFusedLocationProviderClient(app: Application): FusedLocationProviderClient {
        return LocationServices.getFusedLocationProviderClient(app)
    }
}
