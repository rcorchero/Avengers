package com.rcorchero.avengers.data.source.local

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

// Database Info
private const val DATABASE_NAME = "avengersDB"
private const val DATABASE_VERSION = 1

// Table Name
private const val TABLE_AVENGER = "avenger"

// Avenger Table KEYs
private const val KEY_ID = "id"
private const val KEY_ABILITIES = "abilities"
private const val KEY_GROUPS = "groups"
private const val KEY_HEIGHT = "height"
private const val KEY_NAME = "name"
private const val KEY_PHOTO = "photo"
private const val KEY_POWER = "power"
private const val KEY_REAL_NAME = "realName"

class AvengersDatabaseHelper(context: Context):
        SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    // Called when the database is created for the FIRST time.
    override fun onCreate(db: SQLiteDatabase) {
        val queryCreateTable = "CREATE TABLE " + TABLE_AVENGER +
                "(" +
                KEY_NAME + " TEXT PRIMARY KEY," +
                KEY_ABILITIES + " TEXT," +
                KEY_GROUPS + " TEXT," +
                KEY_HEIGHT + " TEXT," +
                KEY_PHOTO + " TEXT," +
                KEY_POWER + " TEXT," +
                KEY_REAL_NAME + " TEXT" +
                ")"

        db.execSQL(queryCreateTable)
    }

    // Called when the database needs to be upgraded.
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Simplest implementation is to drop all old tables and recreate them
        if (oldVersion != newVersion) {
            db.execSQL("DROP TABLE IF EXISTS $TABLE_AVENGER")
            onCreate(db)
        }
    }
}