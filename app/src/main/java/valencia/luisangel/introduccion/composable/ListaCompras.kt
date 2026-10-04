package valencia.luisangel.introduccion.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import valencia.luisangel.introduccion.ui.theme.IntroduccionTheme

data class Articulo(
    val id: Int,
    val nombre: String,
    val cantidad: Int
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListaCompras() {

    val lista = remember {
        mutableStateListOf(
            Articulo(
                id = 1,
                nombre = "Cuaderno",
                cantidad = 2
            ),
            Articulo(
                id = 2,
                nombre = "Pluma",
                cantidad = 5
            )
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Lista de compras")
                }
            )
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {

            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {

                items(
                    items = lista,
                    key = { articulo -> articulo.id }
                ) { articulo ->

                    ItemLista(articulo = articulo)
                }
            }
        }
    }
}

@Composable
fun ItemLista(articulo: Articulo) {

    Card(
        modifier = Modifier.padding(8.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {

        Text(
            text = "${articulo.nombre} x${articulo.cantidad}",
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ListaComprasPreview() {
    IntroduccionTheme {
        ListaCompras()
    }
}