package com.example.playwithui.modal.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.playwithui.modal.dao.SongDao
import com.example.playwithui.modal.data.Song

class SongRepository(private val songDao: SongDao) {

    val allSongs: LiveData<List<Song>> = songDao.getAlphabetizedWords()
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(song: Song)
    {
        println(" inserted the song  ")
        songDao.save(song)
        println(allSongs.getValue())
    }

}