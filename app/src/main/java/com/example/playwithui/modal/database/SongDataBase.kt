package com.example.playwithui.modal.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.playwithui.modal.dao.SongDao
import com.example.playwithui.modal.data.Song
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [Song::class], version = 2)
abstract class SongDataBase : RoomDatabase() {
    abstract fun songDao(): SongDao
    private class SongDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    var songDao = database.songDao()
                    // Delete all content here.
                    //  songDao.deleteAll()
                    // Add sample songs.
                    var song = Song(title ="kutu ma kutu" , singer = " Deepak raj Giri")
                    songDao.save(song)
                    println( song.toString())

                }
            }
        }
    }
    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: SongDataBase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): SongDataBase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database]
            println("You have acces over here ")
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SongDataBase::class.java,
                    "song_database"
                ).addCallback(SongDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }


    }
}