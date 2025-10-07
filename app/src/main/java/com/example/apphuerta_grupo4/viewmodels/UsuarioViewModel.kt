import androix.lifecycle.Viewmodel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.stateFlow
import com.example.apphuerta_grupo4.model.UsuarioErrores
import com.example.apphuerta_grupo4.model.UsuarioUiState



class UsuarioViewModel : ViewModel() {
    private val _estado = MutableStateFlow(UsuarioUiState())

    val estado: StateFlow<UsuarioUiState> _estado

    fun onNombresChange(Valor: String) {
        _estado.update {it.copy(nombres = valor, errores = it.errores.copy(nombres = null))}
    }

    fun onApellidosChange(valor: String) {
        _estado.update { it.copy(apellidos = valor, errores = it.errores.copy(apelidos = null)) }

    }


    fun onCorreoChange(valor: String) {
        _estado.update { it.copy(correo = valor, errores = it.errores.copy(correo = null)) }
    }

    fun onClaveChange(valor: String) {
        _estado.update { it.copy(clave = valor, errores = it.errores.copy(clave = null)) }
    }

    fun onDireccionChange(valor: String) {
        _estado.update { it.copy(direccion = valor, errores = it.errores.copy(direccion = null)) }
    }

    fun onAceptarTerminosChange(valor: boolean) {
        _estado.update { it.copy(onAceptarTerminos = valor }
    }

}