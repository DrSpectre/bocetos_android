package mx.uacj.aplicacionfragmentos.pantallas_fragmentos

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class InformacionCompartida: ViewModel() {
    var variable = 0
    var variable_2 = 2


    private val _mutante_numero_clicks = MutableStateFlow(EstadosData.NumeroDeClicks(0))
    val mutante_numero_clicks: StateFlow<EstadosData> = _mutante_numero_clicks
    fun agregar_un_click(){
        val clicks_anteriores = _mutante_numero_clicks.value.clicks

        if(clicks_anteriores > 20){
            //_mutante_numero_clicks. = EstadosData.Error("Has pulsado demasaidas veces el boton")
        }
        _mutante_numero_clicks.value = EstadosData.NumeroDeClicks(clicks_anteriores + 1)


    }
    override fun onCleared() {
        super.onCleared()
        Log.v("SALIDA", "Estoy eliminando la informacion del ViewModel Informacion Compartida")
    }
}

sealed class EstadosData{
    data class NumeroDeClicks(val clicks: Int): EstadosData()
    data class Error(val error: String): EstadosData()

}
