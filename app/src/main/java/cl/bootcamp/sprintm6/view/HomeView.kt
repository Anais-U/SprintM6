package cl.bootcamp.sprintm6.view

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import cl.bootcamp.sprintm6.components.CardProduct
import cl.bootcamp.sprintm6.viewmodel.ProductViewModel


@Composable
fun HomeView(
    viewModel: ProductViewModel,
    navController: NavController
) {


    LaunchedEffect(Unit) {
        viewModel.getAllAPI()
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
    ) { innerPadding ->
        val products by viewModel.products.collectAsState(listOf())

        LazyVerticalGrid(
            verticalArrangement = Arrangement.SpaceEvenly,
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()

        ) {
            items(products) { item ->
                CardProduct(
                    item.name,
                    item.price,
                    item.image,
                ) {
                    navController.navigate("DetailsView/${item.id}")
                }
            }
        }
    }
}

// Formato de precio
fun formatPrice(price: Int): String {
    return "$${price.toString().replace(Regex("(\\d)(?=(\\d{3})+(?!\\d))"), "$1.")}"
}