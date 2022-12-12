package es.iesluiscarrillo.registroalumnos.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface AlumnosDAO {
    @Query("SELECT * FROM alumnos")
    fun getAllAlumnos(): MutableList<Alumnos>

    @Query("SELECT * FROM alumnos WHERE id like :id")
    fun getAlumnoId(id: Long): Alumnos

    @Query("SELECT * FROM alumnos WHERE nombre LIKE :nombre")
    fun getAlumnoPorNombre(nombre:String): Alumnos

    @Insert
    fun addAlumno(taskEntity: Alumnos): Long

    @Update
    fun updateAlumno(taskEntity: Alumnos): Int

    @Delete
    fun deleteAlumno(taskEntity: Alumnos): Int
}