package com.example.alex_medina_prueba_02


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.alex_medina_prueba_02.ui.theme.DivisionAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DivisionAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DivisionApp()
                }
            }
        }
    }
}

data class CalculationState(
    val nombre: String = "",
    val apellido: String = "",
    val dividendo: String = "",
    val divisor: String = "",
    val numero: String = "",
    val parteEntera: String = "",
    val residuo: String = "",
    val numInvertido: String = ""
)

@Composable
fun DivisionApp() {
    val navController = rememberNavController()
    var calculationState by remember { mutableStateOf(CalculationState()) }

    NavHost(navController = navController, startDestination = "lectura") {
        composable("lectura") {
            LecturaScreen(
                state = calculationState,
                onNavigateToIngreso = { navController.navigate("ingreso") }
            )
        }
        composable("ingreso") {
            IngresoScreen(
                state = calculationState,
                onStateChange = { calculationState = it },
                onNavigateToCalculo = { navController.navigate("calculo") },
                onClose = { navController.navigateUp() }
            )
        }
        composable("calculo") {
            CalculoScreen(
                state = calculationState,
                onStateChange = { calculationState = it },
                onClose = { navController.navigateUp() }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LecturaScreen(
    state: CalculationState,
    onNavigateToIngreso: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        TextField(
            value = state.nombre,
            onValueChange = { },
            label = { Text("Nombres") },
            modifier = Modifier.fillMaxWidth(),
            enabled = false
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = state.apellido,
            onValueChange = { },
            label = { Text("Apellidos") },
            modifier = Modifier.fillMaxWidth(),
            enabled = false
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = state.dividendo,
            onValueChange = { },
            label = { Text("Dividendo") },
            modifier = Modifier.fillMaxWidth(),
            enabled = false
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = state.divisor,
            onValueChange = { },
            label = { Text("Divisor") },
            modifier = Modifier.fillMaxWidth(),
            enabled = false
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = state.parteEntera,
            onValueChange = { },
            label = { Text("Parte entera") },
            modifier = Modifier.fillMaxWidth(),
            enabled = false
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = state.residuo,
            onValueChange = { },
            label = { Text("Residuo") },
            modifier = Modifier.fillMaxWidth(),
            enabled = false
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = state.numInvertido,
            onValueChange = { },
            label = { Text("Número invertido") },
            modifier = Modifier.fillMaxWidth(),
            enabled = false
        )

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = onNavigateToIngreso,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Siguiente")
        }

        Button(
            onClick = { /* TODO: Mostrar resultados */ },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.secondary
            )
        ) {
            Text("Mostrar resultados")
        }
    }
}

Solucion necesito

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IngresoScreen(
    state: CalculationState,
    onStateChange: (CalculationState) -> Unit,
    onNavigateToCalculo: () -> Unit,
    onClose: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        TextField(
            value = state.nombre,
            onValueChange = { onStateChange(state.copy(nombre = it)) },
            label = { Text("Nombres") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = state.apellido,
            onValueChange = { onStateChange(state.copy(apellido = it)) },
            label = { Text("Apellidos") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = state.dividendo,
            onValueChange = { },
            label = { Text("Dividendo") },
            modifier = Modifier.fillMaxWidth(),
            enabled = false
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = state.divisor,
            onValueChange = { },
            label = { Text("Divisor") },
            modifier = Modifier.fillMaxWidth(),
            enabled = false
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = state.numero,
            onValueChange = { },
            label = { Text("Número") },
            modifier = Modifier.fillMaxWidth(),
            enabled = false  // Deshabilitado en la segunda pantalla
        )

        Spacer(modifier = Modifier.weight(1f))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = onNavigateToCalculo,
                modifier = Modifier.weight(1f),
                enabled = state.nombre.isNotBlank() && state.apellido.isNotBlank() // Solo permite avanzar si hay nombre y apellido
            ) {
                Text("Siguiente")
            }

            Spacer(modifier = Modifier.width(8.dp))

            Button(
                onClick = onClose,
                modifier = Modifier.weight(1f)
            ) {
                Text("Cerrar")
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalculoScreen(
    state: CalculationState,
    onStateChange: (CalculationState) -> Unit,
    onClose: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        TextField(
            value = state.nombre,
            onValueChange = { },
            label = { Text("Nombres") },
            modifier = Modifier.fillMaxWidth(),
            enabled = false
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = state.apellido,
            onValueChange = { },
            label = { Text("Apellidos") },
            modifier = Modifier.fillMaxWidth(),
            enabled = false
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = state.dividendo,
            onValueChange = { onStateChange(state.copy(dividendo = it)) },
            label = { Text("Dividendo") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = state.divisor,
            onValueChange = { onStateChange(state.copy(divisor = it)) },
            label = { Text("Divisor") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = state.numero,
            onValueChange = {
                val newNumero = it
                onStateChange(state.copy(
                    numero = newNumero,
                    numInvertido = newNumero.reversed()
                ))
            },
            label = { Text("Número") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = state.numInvertido,
            onValueChange = { },
            label = { Text("Número Invertido") },
            modifier = Modifier.fillMaxWidth(),
            enabled = false
        )

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = onClose,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Cerrar")
        }
    }
}

