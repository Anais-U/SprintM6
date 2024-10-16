package cl.bootcamp.sprintm6.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.bootcamp.sprintm6.model.Product
import cl.bootcamp.sprintm6.repository.ProductRepository
import cl.bootcamp.sprintm6.state.ProductState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val productRepository: ProductRepository
) : ViewModel() {

    var state by mutableStateOf(ProductState())


    val products: Flow<List<Product>> by lazy {
        productRepository.getAllProductsRoom()
    }

    fun getAllAPI() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                productRepository.getAllProductsAPI()
                Log.d("PRODUCTS", productRepository.getAllProductsAPI().toString())
            }
        }
    }

    fun getProductById(id: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val result = productRepository.getProductById(id)
                state = state.copy(
                    name = result.name,
                    price = result.price,
                    image = result.image,
                    description = result.description,
                    lastPrice = result.lastPrice,
                    credit = result.credit
                )
            }
        }
    }


    fun clean() {
        state = state.copy(
            name = "",
            price = 0,
            image = "",
            description = null,
            lastPrice = null,
            credit = null
        )
    }
}
