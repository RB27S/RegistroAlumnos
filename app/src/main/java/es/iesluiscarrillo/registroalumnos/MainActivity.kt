package es.iesluiscarrillo.registroalumnos

import android.os.Bundle
import es.iesluiscarrillo.registroalumnos.database.Alumnos
import es.iesluiscarrillo.registroalumnos.database.AlumnosApp

import es.iesluiscarrillo.registroalumnos.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ActivityWithMenu() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getElementos()

        binding.botonInsertar.setOnClickListener {
            addElemento(Alumnos(nombre = binding.NombreAlumno.text.toString(), apellido = binding.ApellidoAlumno.text.toString(), curso = binding.CursoAlumno.text.toString()))
        }
    }

    fun addElemento(elemento: Alumnos) {
        CoroutineScope(Dispatchers.IO).launch {
            val id = AlumnosApp.database.AlumnosDAO().addAlumno(elemento)
            val recoveryElemento = AlumnosApp.database.AlumnosDAO().getAlumnoId(id)

            runOnUiThread {
                elementos.add(recoveryElemento)
            }
        }
    }
}