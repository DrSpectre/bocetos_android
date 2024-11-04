package mx.uacj.aplicacionfragmentos.pantallas_fragmentos

import android.util.Log
import androidx.lifecycle.ViewModel

class InformacionCompartida: ViewModel() {
    var variable = 0
    var variable_2 = 2

    override fun onCleared() {
        super.onCleared()
        Log.v("SALIDA", "Estoy eliminando la informacion del ViewModel Informacion Compartida")
    }
}