package com.liceolapaz.dam.pae

import android.content.Context
import android.database.sqlite.SQLiteDatabase.CursorFactory
import android.database.sqlite.SQLiteOpenHelper
import android.database.sqlite.SQLiteDatabase

class BaseDeDatos(context: Context?, name: String?, factory: CursorFactory?, version: Int) :
    SQLiteOpenHelper(context, name, factory, version) {
    private var sqlCreate =
        "CREATE TABLE Jugadores (codigo INTEGER PRIMARY KEY AUTOINCREMENT,nombre TEXT,precio INTEGER,posicion TEXT,puntos INTEGER)"

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(sqlCreate)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS Jugadores")
        db.execSQL(sqlCreate)
    }
}