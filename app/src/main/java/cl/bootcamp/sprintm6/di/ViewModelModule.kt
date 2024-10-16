package cl.bootcamp.sprintm6.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import cl.bootcamp.sprintm6.repository.ProductRepository
import cl.bootcamp.sprintm6.viewmodel.ProductViewModel
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject
import javax.inject.Singleton


class ViewModelFactory @Inject constructor(
    private val repository: ProductRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProductViewModel::class.java)) {
            return ProductViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

@Module
@InstallIn(SingletonComponent::class)
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}