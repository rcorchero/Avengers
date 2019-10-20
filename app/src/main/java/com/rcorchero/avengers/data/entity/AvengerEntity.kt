package com.rcorchero.avengers.data.entity

import android.content.ContentValues
import android.database.Cursor
import com.google.gson.annotations.SerializedName
import com.rcorchero.avengers.data.source.local.Contract.COLUMN_ABILITIES
import com.rcorchero.avengers.data.source.local.Contract.COLUMN_GROUPS
import com.rcorchero.avengers.data.source.local.Contract.COLUMN_HEIGHT
import com.rcorchero.avengers.data.source.local.Contract.COLUMN_ID
import com.rcorchero.avengers.data.source.local.Contract.COLUMN_NAME
import com.rcorchero.avengers.data.source.local.Contract.COLUMN_PHOTO
import com.rcorchero.avengers.data.source.local.Contract.COLUMN_POWER
import com.rcorchero.avengers.data.source.local.Contract.COLUMN_REAL_NAME
import java.util.*

data class AvengerEntity(
    @SerializedName("abilities") val abilities: String,
    @SerializedName("groups") val groups: String,
    @SerializedName("height") val height: String,
    @SerializedName("name") val name: String,
    @SerializedName("photo") val photo: String,
    @SerializedName("power") val power: String,
    @SerializedName("realName") val realName: String){

    constructor(cursor: Cursor): this(
        cursor.getString(cursor.getColumnIndex(COLUMN_ABILITIES)),
        cursor.getString(cursor.getColumnIndex(COLUMN_GROUPS)),
        cursor.getString(cursor.getColumnIndex(COLUMN_HEIGHT)),
        cursor.getString(cursor.getColumnIndex(COLUMN_NAME)),
        cursor.getString(cursor.getColumnIndex(COLUMN_PHOTO)),
        cursor.getString(cursor.getColumnIndex(COLUMN_POWER)),
        cursor.getString(cursor.getColumnIndex(COLUMN_REAL_NAME)))

    fun getContentValues(): ContentValues {
        val values = ContentValues()
        values.put(COLUMN_ABILITIES, this.abilities)
        values.put(COLUMN_GROUPS, this.groups)
        values.put(COLUMN_HEIGHT, this.height)
        values.put(COLUMN_NAME, this.name)
        values.put(COLUMN_PHOTO, this.photo)
        values.put(COLUMN_POWER, this.power)
        values.put(COLUMN_REAL_NAME, this.realName)
        return values
    }
}