package com.akatsuki.movieapp.models.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "usersTable")
data class UsersModel (
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "id")
        var id: Int? = null,
        @ColumnInfo(name = "full_name")
        val fullname: String = "",
        @ColumnInfo(name = "username")
        val username: String = "",
        @ColumnInfo(name = "image")
        val image: String = "",
        @ColumnInfo(name = "session")
        val session: String = ""


){

}