package com.liceolapaz.dam.pae

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class DisplayDatosDeMain : AppCompatActivity() {

    private lateinit var Nombre : TextView
    private lateinit var Apellidos : TextView
    private lateinit var Correo : TextView
    private lateinit var Sexo : TextView
    private lateinit var Boletin : TextView
    private lateinit var Telefono : TextView
    private lateinit var Pais : TextView
    private lateinit var Fecha : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_datos_de_main)

        Nombre = findViewById(R.id.displayUserName)
        val nome = intent.getStringExtra("Nombre")
        "Nombre : $nome".also { Nombre.text = it }

        Apellidos = findViewById(R.id.displayUserSurname)
        val apellido = intent.getStringExtra("Apellidos")
        "Apellidos : $apellido".also { Apellidos.text = it }

        Correo = findViewById(R.id.displayUserEmail)
        val correoE = intent.getStringExtra("Correo")
        "Correo: $correoE".also { Correo.text = it }

        Sexo = findViewById(R.id.displayUserSex)
        val sex = intent.getStringExtra("Sexo")
        "Sexo: $sex".also { Sexo.text = it }

        Boletin = findViewById(R.id.displayBoolean)
        val bool = intent.getStringExtra("Boletin")
        "Recibir boletin de noticias: $bool".also { Boletin.text = it }

        Telefono = findViewById(R.id.displayTel)
        val tel = intent.getStringExtra("Telefono")
        "Telefono : $tel".also { Telefono.text = it }

        Fecha = findViewById(R.id.displayBD)
        val bD = intent.getStringExtra("Fecha")
        "Fecha de nacimiento: $bD".also { Fecha.text = it }

        Pais = findViewById(R.id.displayCountry)
        val country = intent.getStringExtra("Pais")
        "Pais: $country".also { Pais.text = it }
        }

    }
