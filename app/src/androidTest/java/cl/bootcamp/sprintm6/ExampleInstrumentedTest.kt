package cl.bootcamp.sprintm6

import androidx.activity.compose.setContent
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.lifecycle.ViewModelProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import cl.bootcamp.sprintm6.utils.FakeRepositoryModule
import cl.bootcamp.sprintm6.view.DetailsView
import cl.bootcamp.sprintm6.viewmodel.ProductViewModel
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class DetailsViewTest {

    @get:Rule(order = 1)
    var hiltTestRule = HiltAndroidRule(this)

    @get:Rule(order = 2)
    var composeTestRule = createAndroidComposeRule<MainActivity>()

    @Inject
    lateinit var productViewModelFactory: ViewModelProvider.Factory

    private lateinit var productViewModel: ProductViewModel

    @Test
    fun verificarSiBotonEnvioEmailFunciona() {
        hiltTestRule.inject() // Inyecta las dependencias

        // Inicializa el ViewModel usando ViewModelProvider
        productViewModel = ViewModelProvider(composeTestRule.activity, productViewModelFactory)
            .get(ProductViewModel::class.java)

        // Configura un repositorio falso para simular la respuesta
        val fakeRepository = FakeRepositoryModule()
        fakeRepository.addProduct(
            id = 1,
            name = "Producto de prueba",
            price = 50000,
            image = "https://via.placeholder.com/350",
            description = "Descripción del producto de prueba",
            lastPrice = 45000,
            credit = true
        )

        // Inyecta el repositorio falso en el ViewModel
        productViewModel = ProductViewModel(fakeRepository)

        // Configura la vista con los detalles del producto
        composeTestRule.activity.setContent {
            DetailsView(
                viewModel = productViewModel,
                navController = androidx.navigation.compose.rememberNavController(),
                id = 1 // Simulamos que cargamos el producto con ID 1
            )
        }

        // Verifica que el botón de enviar correo esté presente
        composeTestRule.onNodeWithTag("fab_send_email").assertIsDisplayed()

        // Simula el clic en el botón
        composeTestRule.onNodeWithTag("fab_send_email").performClick()

        // Aquí puedes agregar aserciones adicionales para verificar el resultado del clic
    }
}