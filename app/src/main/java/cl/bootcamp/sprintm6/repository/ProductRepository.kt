package cl.bootcamp.sprintm6.repository

import cl.bootcamp.sprintm6.datasource.RestDataSource
import cl.bootcamp.sprintm6.model.DetailsProduct
import cl.bootcamp.sprintm6.model.Product
import cl.bootcamp.sprintm6.model.ProductDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface ProductRepository {
    suspend fun getProductById(id: Int): DetailsProduct
    suspend fun getAllProductsAPI(): List<Product>
    fun getAllProductsRoom(): Flow<List<Product>>

}

class ProductRepositoryImp @Inject constructor(
    private val dataSource: RestDataSource,
    private val productDao: ProductDao
) : ProductRepository {

    override suspend fun getProductById(id: Int): DetailsProduct {
        return dataSource.getProductById(id).body()!!.let { data ->
            DetailsProduct(
                id = data.id,
                name = data.name,
                price = data.price,
                image = data.image,
                description = data.description,
                lastPrice = data.lastPrice,
                credit = data.credit
            )
        }
    }

    override suspend fun getAllProductsAPI(): List<Product> {
        val data = dataSource.getProducts()

        data.forEach {
            val product = Product(
                it.id,
                it.name,
                it.price,
                it.image,
                it.description,
                it.lastPrice,
                it.credit
            )
            productDao.insert(product)
        }
        return data
    }

    override fun getAllProductsRoom(): Flow<List<Product>> = productDao.getAll()
}
