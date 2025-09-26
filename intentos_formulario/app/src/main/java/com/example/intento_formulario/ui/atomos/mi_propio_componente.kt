package com.example.intento_formulario.ui.atomos

import android.content.res.Resources.Theme
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.intento_formulario.ui.theme.Intento_formularioTheme


@Composable
fun ComponenteBasico(estado: Boolean, al_puchopicar: () -> Unit,  modificador: Modifier = Modifier){
    Column(modifier = modificador.clickable { al_puchopicar() }) {
        if(estado == true){
            Text("Encendido", color = Color.Green, modifier = Modifier.background(
                Intento_formularioTheme(darkTheme = true).colorScheme.surface))
        }

        else{
            Text("Apagado", color = Color.Red)
        }
    }

}