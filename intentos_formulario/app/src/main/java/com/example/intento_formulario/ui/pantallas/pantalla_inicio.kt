package com.example.intento_formulario.ui.pantallas

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

enum class OpcionesDisponibles{
    opcion_1,
    opcion_2,
    opcion_3
}


@Composable
fun PantallaInicio(modificador: Modifier = Modifier){
    var cajita_palomeada by remember { mutableStateOf(false) }

    var opcion_seleccionada_de_multiples_opciones by remember { mutableStateOf(OpcionesDisponibles.opcion_1) }

    Column(modifier = modificador.fillMaxSize()){
        Text("Formulario sencillo para ir al cine")

        Row(modifier = Modifier.fillMaxWidth()) { // Aqui incluyo un checkbox
            Text("¿Quieres palomitas?")

            Checkbox(checked = cajita_palomeada,
                onCheckedChange = { nuevo_valor -> cajita_palomeada = nuevo_valor }
            )
            Column {
                Text(text = if(cajita_palomeada) "Si quiero" else "No quiero")

                if(cajita_palomeada){
                    Text("Si quiero")
                }
                else {
                    Text("No quiero")
                }
            }
        }
        

        Row{ // Un Boton Radial
            Column {
                Text("¿Qué sabor de palomitas quieres?")

                Row{
                    Row {
                        RadioButton(
                            selected = (opcion_seleccionada_de_multiples_opciones == OpcionesDisponibles.opcion_1),
                            onClick = { opcion_seleccionada_de_multiples_opciones = OpcionesDisponibles.opcion_1 }
                        )
                        Text("Caramelo", modifier = Modifier.padding(15.dp))
                    }

                    Row {
                        RadioButton(
                            selected = (opcion_seleccionada_de_multiples_opciones == OpcionesDisponibles.opcion_2),
                            onClick = { opcion_seleccionada_de_multiples_opciones = OpcionesDisponibles.opcion_2 }
                        )
                        Text("Flaming Hot", modifier = Modifier.padding(15.dp))
                    }
                }

            }
        }


    }
}

@Composable
@Preview(showSystemUi = true)
fun PrevistaPantallaInicio(){
    Scaffold(modifier = Modifier.fillMaxSize()) { padding_interno ->
        PantallaInicio(modificador = Modifier.padding(padding_interno))
    }
}


