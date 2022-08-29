package com.akatsuki.movieapp.models.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "SaveTable")
data class SaveModel (
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "id")
        var id: Int? = null,
        @ColumnInfo(name = "full_name")
        val uid: String = "",
        @ColumnInfo(name = "username")
        val fullname: String = "",
        @ColumnInfo(name = "image")
        val image: String = "",


){

}