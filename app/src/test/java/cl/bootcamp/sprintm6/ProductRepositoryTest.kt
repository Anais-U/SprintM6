package cl.bootcamp.sprintm6


import android.arch.core.executor.testing.InstantTaskExecutorRule
import cl.bootcamp.sprintm6.datasource.RestDataSource
import cl.bootcamp.sprintm6.model.Product
import cl.bootcamp.sprintm6.model.ProductDao
import cl.bootcamp.sprintm6.repository.ProductRepositoryImp
import cl.bootcamp.sprintm6.util.Constants.Companion.ENDPOINT_ALL_PRODUCTS
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.nio.charset.StandardCharsets

private val product1 = Product(0 , "Samsung j3", 150000 , "http://..." )
private val product2 = Product(1, "Motorola", 250000, "https://...")

class ProductRepositoryTest {


    val myDispatcher: Dispatcher = object : Dispatcher() {
        override fun dispatch(request: RecordedRequest): MockResponse {
            return when (request.path) {
                "/$ENDPOINT_ALL_PRODUCTS" -> MockResponse().apply { addResponse("api.products.json") }
                else -> MockResponse().setResponseCode(404)
            }
        }
    }

    // Create MockWebServer after dispatcher initialization
    private val mockWebServer = MockWebServer().apply {
        dispatcher = myDispatcher
    }

    private val restDataSource = Retrofit.Builder()
        .baseUrl(mockWebServer.url("/"))
        .client(OkHttpClient.Builder().build())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(RestDataSource::class.java)

    private val newsRepository = ProductRepositoryImp(restDataSource, MockProductDao())

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `Verificar si obtenemos correctamente los productos de la DB`() = runBlocking {
        val products = newsRepository.getAllProductsRoom().first()
        assertEquals(2, products.size)
    }

    fun MockResponse.addResponse(filePath: String): MockResponse {
        val inputStream = javaClass.classLoader?.getResourceAsStream(filePath)
        val source = inputStream?.source()?.buffer()
        source?.let {
            setResponseCode(200)
            setBody(it.readString(StandardCharsets.UTF_8))
        }
        return this
    }

    class MockProductDao : ProductDao {
        private val products = MutableStateFlow<List<Product>>(listOf(product1, product2))

        override fun insert(product: Product) {
            products.value = products.value.toMutableList().apply { add(product) }
        }

        override fun getAll(): Flow<List<Product>> = products

        override fun getProductById(productId: Int): Flow<Product> {
            TODO("Not yet implemented")
        }
    }
}
