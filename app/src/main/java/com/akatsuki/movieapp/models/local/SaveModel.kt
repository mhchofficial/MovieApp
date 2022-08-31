package com.akatsuki.movieapp.models.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "saveTable")
data class SaveModel (
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "id")
        var id: Int? = null,
        @ColumnInfo(name = "title")
        val title: String = "",
        @ColumnInfo(name = "poster")
        val poster: String = "",
        @ColumnInfo(name = "imdb")
        val imdb: String = "",
        @ColumnInfo(name = "year")
        val year: String = "",
        @ColumnInfo(name = "country")
        val country: String = ""


){

}