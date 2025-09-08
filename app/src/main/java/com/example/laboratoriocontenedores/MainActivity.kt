package com.example.laboratoriocontenedores

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EncuestaApp()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EncuestaApp() {
    MaterialTheme {
        Scaffold(
            topBar = { TopAppBar(title = { Text("Encuesta de Satisfacción") }) },
            bottomBar = { BottomAppBar { Text("Laboratorio Contenedores", Modifier.padding(8.dp)) } }
        ) { padding ->
            EncuestaContenido(Modifier.padding(padding))
        }
    }
}

@Composable
fun EncuestaContenido(modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        item { PreguntaCheckBox() }
        item { PreguntaRadioButton() }
        item { PreguntaSlider() }
        item { PreguntaTexto() }
        item { PreguntaPreferencias() }
        item { BotonEnviar() }
    }
}

@Composable
fun PreguntaCheckBox() {
    var checked by remember { mutableStateOf(false) }
    Column {
        Text("¿Recomendarías esta app a un amigo?")
        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(checked = checked, onCheckedChange = { checked = it })
            Text(if (checked) "Sí" else "No")
        }
    }
}

@Composable
fun PreguntaRadioButton() {
    var opcion by remember { mutableStateOf("Ninguna") }
    Column {
        Text("¿Cómo calificas la dificultad del laboratorio?")
        Row(verticalAlignment = Alignment.CenterVertically) {
            RadioButton(selected = opcion == "Fácil", onClick = { opcion = "Fácil" })
            Text("Fácil")
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            RadioButton(selected = opcion == "Normal", onClick = { opcion = "Normal" })
            Text("Normal")
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            RadioButton(selected = opcion == "Difícil", onClick = { opcion = "Difícil" })
            Text("Difícil")
        }
    }
}

@Composable
fun PreguntaSlider() {
    var valor by remember { mutableStateOf(5f) }
    Column {
        Text("¿Qué tan satisfecho estás? (1 a 10)")
        Slider(value = valor, onValueChange = { valor = it }, valueRange = 1f..10f)
        Text("Puntuación: ${valor.toInt()}")
    }
}

@Composable
fun PreguntaTexto() {
    var comentario by remember { mutableStateOf("") }
    Column {
        Text("Escribe un comentario:")
        OutlinedTextField(
            value = comentario,
            onValueChange = { comentario = it },
            label = { Text("Tu opinión") },
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun PreguntaPreferencias() {
    val items = listOf("UI", "Velocidad", "Compatibilidad", "Funciones", "Simplicidad")
    Column {
        Text("¿Qué aspecto te gusta más?")
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(items.size) { i ->
                Card(
                    modifier = Modifier.size(120.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Text(items[i])
                    }
                }
            }
        }
    }
}

@Composable
fun BotonEnviar() {
    var enviado by remember { mutableStateOf(false) }
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Button(onClick = { enviado = true }) {
            Text("Enviar respuestas")
        }
        if (enviado) {
            Text("✅ Encuesta enviada. ¡Gracias!")
        }
    }
}
