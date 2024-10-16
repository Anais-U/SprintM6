package cl.bootcamp.sprintm6.utils

import cl.bootcamp.sprintm6.model.DetailsProduct
import cl.bootcamp.sprintm6.model.Product
import cl.bootcamp.sprintm6.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeRepositoryModule : ProductRepository {

    private val products = mutableListOf<Product>()
    private var productDetails: DetailsProduct? = null

    override suspend fun getProductById(id: Int): DetailsProduct {
        return productDetails ?: DetailsProduct(
            id = 1,
            name = "Producto de prueba",
            price = 50000,
            image = "https://via.placeholder.com/350",
            description = "Descripción del producto de prueba",
            lastPrice = 45000,
            credit = true
        )
    }

    override suspend fun getAllProductsAPI(): List<Product> {
        return products
    }

    override fun getAllProductsRoom(): Flow<List<Product>> {
        return flowOf(products)
    }

    fun addProduct(
        id: Int,
        name: String,
        price: Int,
        image: String,
        description: String?,
        lastPrice: Int?,
        credit: Boolean?
    ) {
        val product = Product(id, name, price, image, description, lastPrice, credit)
        products.add(product)
        productDetails = DetailsProduct(id, name, price, image, description, lastPrice, credit)
    }
}