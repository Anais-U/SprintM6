package cl.bootcamp.sprintm6.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import cl.bootcamp.sprintm6.repository.ProductRepository
import cl.bootcamp.sprintm6.repository.ProductRepositoryImp

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun productRepository(productRepositoryImp: ProductRepositoryImp): ProductRepository
}
