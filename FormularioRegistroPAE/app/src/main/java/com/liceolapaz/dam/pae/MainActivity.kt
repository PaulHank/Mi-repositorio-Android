package com.liceolapaz.dam.pae

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.*
import com.liceolapaz.dam.pae.R.id
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {

    private lateinit var cmbOpciones: Spinner
    private lateinit var txtName: EditText
    private lateinit var txtSurname: EditText
    private lateinit var txtPassword: EditText
    private lateinit var txtRepeatPassword: EditText
    private lateinit var txtTelephone: EditText
    private lateinit var txtBD: EditText
    private lateinit var txtEmail: EditText
    private lateinit var btnAceptar: Button
    private lateinit var btnExit: Button
    private lateinit var radioGroup: RadioGroup
    private lateinit var RB1: RadioButton
    private lateinit var RB2: RadioButton
    private lateinit var chkFeed: CheckBox
    private lateinit var lblCountryRes: TextView

    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        txtName = findViewById(id.txtName)
        txtSurname = findViewById(id.txtSurname)
        txtPassword = findViewById(id.txtPassword)
        txtRepeatPassword = findViewById(id.txtRepeatPassword)
        txtTelephone = findViewById(id.txtTelephone)
        txtBD = findViewById(id.txtBD)
        txtEmail = findViewById(id.txtEmail)

        btnAceptar = findViewById(id.btnAccept)
        btnExit = findViewById(id.btnExit)

        chkFeed = findViewById(id.chkFeed)

        radioGroup = findViewById(id.radioGroup)
        RB1 = findViewById(id.RB1)
        RB2 = findViewById(id.RB2)

        cmbOpciones = findViewById(id.cmbOpciones)
        lblCountryRes = findViewById(id.lblCountryRes)

        //Spinner de paises
        val paises = arrayOf("Españita", "Francia", "Andorra", "Alemania")
        val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, paises)

        cmbOpciones.adapter = arrayAdapter

        cmbOpciones.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                lblCountryRes.text = paises[position]
            }
        }

        //RadioButton de generos
        radioGroup.setOnCheckedChangeListener { group, chekedId ->
            if (chekedId == id.RB1)
                Toast.makeText(this, RB1.text.toString(), Toast.LENGTH_SHORT).show()

            if (chekedId == id.RB2)
                Toast.makeText(this, RB2.text.toString(), Toast.LENGTH_SHORT).show()
        }
        radioGroup.clearCheck()

        //Boton pa salir de la aplicacion.
        btnExit.setOnClickListener {
            finishAndRemoveTask();
            exitProcess(0);
        }

        //Valores que le pasa el boton de aceptar a la clase DisplayDatosDeMain.kt
        btnAceptar.setOnClickListener {
            val intent = Intent(this@MainActivity, DisplayDatosDeMain::class.java)

            if (TextUtils.isEmpty(txtName.text)) {
                txtName.error = "Necesitas un nombre"
            } else {
                intent.putExtra("Nombre", txtName.text.toString())
            }

            if (TextUtils.isEmpty(txtEmail.text)) {
                txtEmail.error = "Necesitas un correo"
            } else {
                intent.putExtra("Correo", txtEmail.text.toString())
            }

            if (TextUtils.isEmpty(txtSurname.text)) {
                txtSurname.error = "Necesitas un apellido"
            } else {
                intent.putExtra("Apellidos", txtSurname.text.toString())
            }

            if (TextUtils.isEmpty(txtPassword.text)) {
                txtPassword.error = "Necesitas una contraseña"
            }
            if (TextUtils.isEmpty(txtRepeatPassword.text)) {
                txtRepeatPassword.error = "vuelva a introducir la contraseña"
            } else {
                if (!txtPassword.text.contentEquals(txtRepeatPassword.text)) {
                    txtRepeatPassword.error = "Tus contraseñas no coinciden"
                } else {

                    if (!chkFeed.isChecked) {
                        intent.putExtra("Boletin", "No")
                    } else {
                        intent.putExtra("Boletin", "Si")
                    }

                    if (RB1.isChecked) {
                        intent.putExtra("Sexo", RB1.text.toString())
                    } else if (RB2.isChecked) {
                        intent.putExtra("Sexo", RB2.text.toString())
                    }

                    intent.putExtra("Pais", lblCountryRes.text.toString())
                    intent.putExtra("Fecha", txtBD.text.toString())
                    intent.putExtra("Telefono", txtTelephone.text.toString())
                    startActivity(intent)
                }
            }
        }
    }
}
