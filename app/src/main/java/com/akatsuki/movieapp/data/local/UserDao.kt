package com.akatsuki.movieapp.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.akatsuki.movieapp.models.local.UsersModel
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Query("SELECT * FROM usersTable")
    fun getalluser(): Flow<List<UsersModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveUser(usersModel: UsersModel)

    @Query("DELETE FROM usersTable WHERE id==:id")
    suspend fun deleteUser(id: Int)

    @Query("DELETE FROM usersTable")
    suspend fun deleteUsers()

    @Query("SELECT * FROM usersTable WHERE id = :userid")
    fun getUserById(userid: Int): LiveData<UsersModel>






}
