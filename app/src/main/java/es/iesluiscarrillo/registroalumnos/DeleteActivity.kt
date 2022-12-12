package es.iesluiscarrillo.registroalumnos

import android.os.Bundle
import es.iesluiscarrillo.registroalumnos.database.Alumnos
import es.iesluiscarrillo.registroalumnos.database.AlumnosApp
import es.iesluiscarrillo.registroalumnos.databinding.ActivityDeleteBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DeleteActivity : ActivityWithMenu() {

    lateinit var binding: ActivityDeleteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDeleteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getElementos()

        binding.botonEliminar.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                val alumno = AlumnosApp.database.AlumnosDAO().getAlumnoPorNombre(binding.NombreAlumnoEliminar.text.toString())

                runOnUiThread {
                    deleteElemento(alumno)
                }
            }
        }
    }

    fun deleteElemento(elemento: Alumnos) {
        CoroutineScope(Dispatchers.IO).launch {
            AlumnosApp.database.AlumnosDAO().deleteAlumno(elemento)
            elementos.remove(elemento)
        }
    }
}