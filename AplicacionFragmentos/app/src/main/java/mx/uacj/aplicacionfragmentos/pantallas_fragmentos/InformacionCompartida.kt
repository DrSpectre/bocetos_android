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


    private val _mutante_numero_clicks: MutableStateFlow<EstadosData> = MutableStateFlow(EstadosData.NumeroDeClicks(0))
    val mutante_numero_clicks: StateFlow<EstadosData> = _mutante_numero_clicks
    fun agregar_un_click(){
        when(_mutante_numero_clicks.value){
            is EstadosData.Error -> {
                Log.v("Actualizacion", "Aqui paso algo")
            }
            is EstadosData.NumeroDeClicks -> {
                val clicks_anteriores = _mutante_numero_clicks.value as EstadosData.NumeroDeClicks
                clicks_anteriores.clicks
                _mutante_numero_clicks.value = EstadosData.NumeroDeClicks(clicks_anteriores.clicks + 1)

                if(clicks_anteriores.clicks > 20){
                    _mutante_numero_clicks.value = EstadosData.Error("Has pulsado demasaidas veces el boton")
                }
            }
        }
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
