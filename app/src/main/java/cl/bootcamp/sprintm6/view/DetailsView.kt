package cl.bootcamp.sprintm6.view

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import cl.bootcamp.sprintm6.R
import cl.bootcamp.sprintm6.components.TopBarComponent
import cl.bootcamp.sprintm6.viewmodel.ProductViewModel
import coil.compose.rememberAsyncImagePainter

@Composable
fun DetailsView(
    viewModel: ProductViewModel,
    navController: NavController,
    id: Int
) {
    LaunchedEffect(Unit) {
        viewModel.getProductById(id)
    }

    DisposableEffect(Unit) {
        onDispose {
            viewModel.clean()
        }
    }

    Scaffold(
        topBar = {
            TopBarComponent(
                title = "Detalles de ${viewModel.state.name}",
                showBotton = true
            ) { navController.popBackStack() }
        }
    ) {
        ContentDetailView(it, viewModel, id)
    }
}

@Composable
fun ContentDetailView(
    paddingValues: PaddingValues,
    viewModel: ProductViewModel,
    id: Int
) {
    val state = viewModel.state
    val context = LocalContext.current
    val image = rememberAsyncImagePainter(model = state.image)
    val email = stringResource(R.string.email_recipient)
    val asunto = stringResource(R.string.subject_template, state.name, id)
    val mensaje = stringResource(R.string.message_template, state.name, id)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        // Imagen del producto
        Image(
            painter = image,
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .fillMaxWidth()
                .height(350.dp)
                .padding(16.dp)
        )

        // Descripción del producto
        Column(
            modifier = Modifier.padding(8.dp),
            horizontalAlignment = Alignment.Start

        ) {
            Text(
                text = state.name,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = state.description ?: stringResource(R.string.details_not_available),
                fontWeight = FontWeight.Normal,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Text(
                text = "Último precio: ${
                    state.lastPrice?.let { formatPrice(it) } ?: stringResource(R.string.details_not_available)
                }",
                fontWeight = FontWeight.Medium
            )

            Text(
                text = if (state.credit == true) stringResource(R.string.credit_yes) else
                        stringResource(R.string.credit_no),
                fontWeight = FontWeight.Medium
            )


            // Botón flotante
            FloatingActionButton(
                onClick = {
                    val intent = Intent(Intent.ACTION_SEND).apply {
                        putExtra(Intent.EXTRA_EMAIL, arrayOf(email))
                        putExtra(Intent.EXTRA_SUBJECT, asunto)
                        putExtra(Intent.EXTRA_TEXT, mensaje)
                        type = "message/rfc822"
                    }
                    context.startActivity(Intent.createChooser(intent, "Email del cliente"))
                },
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(16.dp)
                    .testTag("fab_send_email")
            ) {
                Icon(
                    imageVector = Icons.Default.Email,
                    contentDescription = "Enviar correo"
                )
            }
        }
    }
}