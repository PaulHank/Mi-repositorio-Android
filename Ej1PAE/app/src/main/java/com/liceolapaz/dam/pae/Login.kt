package com.liceolapaz.dam.pae

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.liceolapaz.dam.pae.R.id
import kotlin.system.exitProcess

class Login : AppCompatActivity() {

    private lateinit var errorDisp : TextView
    private lateinit var login : Button
    private lateinit var username : EditText
    private lateinit var password : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        errorDisp = findViewById(id.errorDisp)
        login = findViewById(id.login)
        password = findViewById(id.password)
        username = findViewById(id.Username)



        login.setOnClickListener {
            val intent = Intent(this@Login, DB::class.java)
            var numIntentos = 3;
            if (numIntentos == 0) {
                exitProcess(0)
            }

            if (!password.text.contentEquals("liceo") || !username.text.contentEquals("admin")) {
                username.setText("")
                password.setText("")
                errorDisp.text = "El usuario y/u contraseña incorrecto."
                errorDisp.visibility = VISIBLE
                numIntentos--
            } else {
                errorDisp.visibility == INVISIBLE
                errorDisp.text = ""
                startActivity(intent)
            }
        }
    }
}