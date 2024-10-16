package cl.bootcamp.sprintm6.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarComponent(
    title: String,
    showBotton: Boolean = false,
    onClick: () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.secondary
            )
        },

        navigationIcon = {
            if (showBotton) {
                IconButton(onClick = { onClick() }) {
                    Icon(
                        Icons.AutoMirrored.Default.ArrowBack,
                        contentDescription = "Volver"
                    )
                }
            }
        }
    )
}