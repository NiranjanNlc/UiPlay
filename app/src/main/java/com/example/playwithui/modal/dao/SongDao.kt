package com.example.playwithui.modal.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.Update
import com.example.playwithui.modal.data.Song

@Dao
interface SongDao {
    @Insert(onConflict = REPLACE)
    fun save(song: Song)

    @Update
    fun update(song:Song)

    @Query("SELECT * FROM  Song ORDER BY title ASC")
    fun getAlphabetizedWords():  LiveData<List<Song>>

}