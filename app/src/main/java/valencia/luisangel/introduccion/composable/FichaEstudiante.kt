package valencia.luisangel.introduccion.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FichaEstudiante() {

    var nombre by remember {
        mutableStateOf("Luisangel Valencia")
    }

    var carrera by remember {
        mutableStateOf("Ingenieria en Electronica y Computacion")
    }

    var semestre by remember {
        mutableStateOf("8vo semestre")
    }

    var meGusta by remember {
        mutableStateOf(0)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("MI FICHA")
                }
            )
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Box(
                        modifier = Modifier
                            .size(120.dp)
                            .clip(CircleShape)
                            .background(
                                MaterialTheme.colorScheme.primaryContainer
                            ),
                        contentAlignment = Alignment.Center
                    ) {

                        Text(
                            text = nombre
                                .firstOrNull()
                                ?.uppercase()
                                ?: "?",
                            style = MaterialTheme.typography.displayLarge
                        )
                    }

                    Text(
                        text = nombre.ifEmpty {
                            "Sin nombre"
                        },
                        fontSize = 24.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(top = 12.dp)
                    )

                    Text(
                        text = carrera.ifEmpty {
                            "Sin carrera"
                        },
                        fontSize = 18.sp,
                        textAlign = TextAlign.Center
                    )

                    Text(
                        text = semestre.ifEmpty {
                            "Sin semestre"
                        },
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center
                    )

                    Text(
                        text = "Estatus: Activo",
                        fontSize = 16.sp,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
            }

            OutlinedTextField(
                value = nombre,
                onValueChange = {
                    nombre = it
                },
                label = {
                    Text("Nombre")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            )

            OutlinedTextField(
                value = carrera,
                onValueChange = {
                    carrera = it
                },
                label = {
                    Text("Carrera")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            )

            Text(
                text = "Selecciona tu semestre:",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(
                    top = 8.dp,
                    bottom = 4.dp
                )
            )

            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(
                    horizontal = 4.dp
                ),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                items((1..9).toList()) { numeroSemestre ->

                    val sufijo = when (numeroSemestre) {
                        1, 3 -> "er"
                        2 -> "do"
                        7 -> "mo"
                        8 -> "vo"
                        9 -> "no"
                        else -> "to"
                    }

                    val textoSemestre =
                        "$numeroSemestre$sufijo semestre"

                    FilterChip(
                        selected = semestre == textoSemestre,
                        onClick = {
                            semestre = textoSemestre
                        },
                        label = {
                            Text("$numeroSemestre$sufijo")
                        }
                    )
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                AssistChip(
                    onClick = {
                        if (meGusta > 0) {
                            meGusta--
                        }
                    },
                    label = {
                        Text("-")
                    }
                )

                Text(
                    text = "Me gusta: $meGusta",
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleMedium
                )

                AssistChip(
                    onClick = {
                        meGusta++
                    },
                    label = {
                        Text("+")
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FichaEstudiantePreview() {
    FichaEstudiante()
}