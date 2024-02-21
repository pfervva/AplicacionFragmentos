package com.example.aplicacionfragmentos.ui.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class User(
    @PrimaryKey val username: String,
    val password: String,
    val isLoggedIn: Boolean = false
)
