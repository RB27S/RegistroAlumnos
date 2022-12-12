package es.iesluiscarrillo.registroalumnos

import android.os.Bundle
import es.iesluiscarrillo.registroalumnos.database.Alumnos
import es.iesluiscarrillo.registroalumnos.database.AlumnosApp
import es.iesluiscarrillo.registroalumnos.databinding.ActivityUpdateBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UpdateActivity : ActivityWithMenu() {

    lateinit var binding: ActivityUpdateBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getElementos()

        binding.botonActualizar.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                var alumno = AlumnosApp.database.AlumnosDAO().getAlumnoPorNombre(binding.NombreAlumnoActualizar.text.toString())

                runOnUiThread {
                    updateElemento(alumno, binding.NuevoCursoAlumno.text.toString())
                }
            }
        }
    }

    fun updateElemento(elemento: Alumnos, nuevoCurso: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val position = elementos.indexOf(elemento)

            elemento.curso = nuevoCurso
            AlumnosApp.database.AlumnosDAO().updateAlumno(elemento)
            elementos[position] = elemento
        }
    }
}