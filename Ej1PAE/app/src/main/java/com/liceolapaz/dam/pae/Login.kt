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
import com.liceolapaz.dam.pae.databinding.DbBinding
import com.liceolapaz.dam.pae.databinding.LoginBinding
import kotlin.system.exitProcess

class Login : AppCompatActivity() {

    private lateinit var binding: LoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.login.setOnClickListener {
            val intent = Intent(this@Login, DB::class.java)
            var numIntentos = 3;
            if (numIntentos == 0) {
                exitProcess(0)
            }

            if (!binding.password.text.contentEquals("liceo") || !binding.Username.text.contentEquals("admin")) {
                binding.Username.setText("")
                binding.password.setText("")
                binding.errorDisp.text = "El usuario y/u contraseña incorrecto."
                binding.errorDisp.visibility = VISIBLE
                numIntentos--
            } else {
                binding.errorDisp.visibility == INVISIBLE
                binding.errorDisp.text = ""
                startActivity(intent)
            }
        }
    }
}