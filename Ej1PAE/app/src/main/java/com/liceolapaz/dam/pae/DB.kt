package com.liceolapaz.dam.pae

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu

class DB : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.db)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }
}