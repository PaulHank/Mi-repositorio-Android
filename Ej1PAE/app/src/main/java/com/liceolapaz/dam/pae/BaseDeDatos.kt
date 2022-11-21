package com.liceolapaz.dam.pae

import android.content.Context
import android.database.sqlite.SQLiteDatabase.CursorFactory
import android.database.sqlite.SQLiteOpenHelper
import android.database.sqlite.SQLiteDatabase

class BaseDeDatos(context: Context?, nombre: String?, factory: CursorFactory?, version: Int) :
    SQLiteOpenHelper(context, nombre, factory, version) {
    var sqlCreate =
        "CREATE TABLE Jugadores (Codigo INTEGER,Nombre TEXT,Precio INTEGER,Posicion TEXT,Puntos INTEGER)"

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(sqlCreate)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(sqlCreate)
    }
}