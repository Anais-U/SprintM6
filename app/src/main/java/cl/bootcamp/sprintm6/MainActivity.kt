package cl.bootcamp.sprintm6


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import cl.bootcamp.sprintm6.navigation.NavManager
import cl.bootcamp.sprintm6.ui.theme.SprintM6Theme
import cl.bootcamp.sprintm6.viewmodel.ProductViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        val productViewModel: ProductViewModel by viewModels()
        setContent {
            SprintM6Theme {
                NavManager(productViewModel)
            }
        }
    }
}