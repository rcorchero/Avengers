package com.rcorchero.avengers.data.source.local

import android.database.sqlite.SQLiteOpenHelper
import com.rcorchero.avengers.data.entity.AvengerEntity
import com.rcorchero.avengers.data.source.local.Contract.COLUMN_NAME
import com.rcorchero.avengers.data.source.local.Contract.TABLE_NAME
import java.util.ArrayList

class LocalDataSourceImpl(
        private val sqLiteOpenHelper: SQLiteOpenHelper): LocalDataSource {

    override fun getAvengers(): List<AvengerEntity> {
        val db = sqLiteOpenHelper.readableDatabase
        val sql = "SELECT * FROM $TABLE_NAME"
        val superheroEntityList = ArrayList<AvengerEntity>()

        val cursor = db.rawQuery(sql, null)
        if (cursor.moveToFirst()) {
            do {
                superheroEntityList.add(AvengerEntity(cursor))
            } while (cursor.moveToNext())
        }

        if (!cursor.isClosed) {
            cursor.close()
        }

        return superheroEntityList
    }

    override fun getAvenger(avengerName: String): AvengerEntity? {
        val db = sqLiteOpenHelper.readableDatabase
        val sql = "SELECT * FROM $TABLE_NAME WHERE $COLUMN_NAME = \"$avengerName\""
        var avengerEntity: AvengerEntity? = null

        val cursor = db.rawQuery(sql, null)
        if (cursor.moveToFirst()) {
            avengerEntity = AvengerEntity(cursor)
        }

        if (!cursor.isClosed) {
            cursor.close()
        }

        return avengerEntity
    }

    override fun saveAvengers(avengerEntityList: List<AvengerEntity>) {
        val db = sqLiteOpenHelper.writableDatabase

        db.beginTransaction()
        for (superheroEntity in avengerEntityList) {
            db.insert(TABLE_NAME, null, superheroEntity.getContentValues())
        }
        db.setTransactionSuccessful()
        db.endTransaction()
    }

    override fun deleteAllAvengers() {
        val db = sqLiteOpenHelper.writableDatabase
        db.delete(TABLE_NAME, null, null)
    }
}