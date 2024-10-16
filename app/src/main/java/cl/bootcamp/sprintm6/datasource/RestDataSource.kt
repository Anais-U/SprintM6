package cl.bootcamp.sprintm6.datasource

import cl.bootcamp.sprintm6.model.Product
import cl.bootcamp.sprintm6.util.Constants.Companion.ENDPOINT_ALL_PRODUCTS
import cl.bootcamp.sprintm6.util.Constants.Companion.ENDPOINT_PRODUCT_DETAILS
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface RestDataSource {

    @GET(ENDPOINT_ALL_PRODUCTS)
    suspend fun getProducts(): List<Product>

    @GET(ENDPOINT_PRODUCT_DETAILS)
    suspend fun getProductById(@Path("id") id: Int): Response<Product>
}
