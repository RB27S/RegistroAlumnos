package es.iesluiscarrillo.registroalumnos.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Alumnos (
    @PrimaryKey(autoGenerate = true) var id:Int = 0,
    var nombre:String = "",
    var apellido:String = "",
    var curso:String = "",
)
