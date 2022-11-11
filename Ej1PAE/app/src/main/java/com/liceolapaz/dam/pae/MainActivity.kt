package com.liceolapaz.dam.pae

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
<<<<<<< HEAD
import android.view.View.VISIBLE
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {

    private lateinit var error : TextView
    private lateinit var login : Button
    private lateinit var username : EditText
    private lateinit var password : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        error = findViewById(R.id.errorDisp)
        login = findViewById(R.id.login)
        password = findViewById(R.id.password)
        username = findViewById(R.id.Username)



        login.setOnClickListener {
            var numIntentos = 3;

            if (!username.equals("admin")) {
                error.text = "El nombre de usuario es incorrecto"
                error.error
                error.visibility = VISIBLE
                if (!password.equals("liceo")) {
                    error.text = "La contraseña es incorrecta"
                    error.error
                    error.visibility = VISIBLE
                    numIntentos--
                }
                numIntentos--
            }

            if (numIntentos == 0) {
                exitProcess(0)
            }


            startActivity(intent)
        }
=======

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
>>>>>>> da20b1c4d207cb03b35f1f601f3cee404a16e832
    }
}