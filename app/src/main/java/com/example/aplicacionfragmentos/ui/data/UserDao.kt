package com.example.aplicacionfragmentos.ui.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {
    @Insert
    suspend fun insert(user: User)

    @Query("UPDATE user_table SET isLoggedIn = :isLoggedIn WHERE username = :username")
    suspend fun updateLoginState(username: String, isLoggedIn: Boolean)

    @Query("SELECT * FROM user_table WHERE isLoggedIn = 1 LIMIT 1")
    suspend fun getLoggedInUser(): User?

    @Query("SELECT * FROM user_table WHERE username = :username AND password = :password LIMIT 1")
    suspend fun getUser(username: String, password: String): User?
    @Query("UPDATE user_table SET isLoggedIn = 0 WHERE isLoggedIn = 1")
    suspend fun logoutCurrentUser()
}

