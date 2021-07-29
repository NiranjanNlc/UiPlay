package com.example.playwithui.modal.data

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Song(
    @PrimaryKey(autoGenerate= true)
    val id: Int = 0,
    var title: String,
    var singer :String
)