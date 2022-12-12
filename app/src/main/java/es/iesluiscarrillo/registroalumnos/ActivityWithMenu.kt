package es.iesluiscarrillo.registroalumnos

import android.content.Intent
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import es.iesluiscarrillo.registroalumnos.database.Alumnos
import es.iesluiscarrillo.registroalumnos.database.AlumnosApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

open class ActivityWithMenu : AppCompatActivity() {
    companion object {
        var actividadActual = 0
        lateinit var elementos: MutableList<Alumnos>
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_principal, menu)

        for (i in 0 until(menu.size())) menu.getItem(i).isEnabled = true
        menu.getItem(actividadActual).isEnabled = false

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.InsertAlumno -> {
                val intent = Intent(this, MainActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
                actividadActual = 0
                startActivity(intent)
                true
            }
            R.id.UpdateCurso -> {
                val intent = Intent(this, UpdateActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
                actividadActual = 1
                startActivity(intent)
                true
            }
            R.id.DeleteAlumno -> {
                val intent = Intent(this, DeleteActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
                actividadActual = 2
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun getElementos() {
        CoroutineScope(Dispatchers.IO).launch {
            elementos = AlumnosApp.database.AlumnosDAO().getAllAlumnos()
        }
    }
}