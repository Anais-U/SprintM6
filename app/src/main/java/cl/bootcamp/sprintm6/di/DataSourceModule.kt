package cl.bootcamp.sprintm6.di

import android.content.Context
import androidx.room.Room
import cl.bootcamp.sprintm6.datasource.DbDataSource
import cl.bootcamp.sprintm6.datasource.RestDataSource
import cl.bootcamp.sprintm6.model.ProductDao
import cl.bootcamp.sprintm6.util.Constants.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataSourceModule {

    @Singleton
    @Provides
    fun providesRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun restDataSource(retrofit: Retrofit): RestDataSource =
        retrofit.create(RestDataSource::class.java)

    @Singleton
    @Provides
    fun dbDataSource(@ApplicationContext context: Context): DbDataSource {
        return Room.databaseBuilder(
            context,
            DbDataSource::class.java,
            "product_database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun productDao(db: DbDataSource): ProductDao = db.productDao()
}
