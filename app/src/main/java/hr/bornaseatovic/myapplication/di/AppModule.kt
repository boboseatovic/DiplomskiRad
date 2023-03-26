package hr.bornaseatovic.myapplication.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import hr.bornaseatovic.myapplication.data.dataSource.LocalDataSource
import hr.bornaseatovic.myapplication.data.dataSource.local.Database
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(app: Application): Database{
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
}