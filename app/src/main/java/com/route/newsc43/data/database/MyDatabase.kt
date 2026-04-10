package com.route.newsc43.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.route.newsc43.data.api.model.SourceDM
import com.route.newsc43.data.database.dao.SourcesDao

@Database(entities = [SourceDM::class], version = 1, exportSchema = false)
abstract class MyDatabase : RoomDatabase() {
    companion object {
        private var myDataBase: MyDatabase? = null

        fun createDatabase(context: Context) {
            myDataBase = Room.databaseBuilder(context, MyDatabase::class.java, "todo-db")
                .fallbackToDestructiveMigration()
                .build()
        }

        fun getDataBase(): MyDatabase {
            return myDataBase!!;
        }
    }

    abstract fun getSourcesDao(): SourcesDao
}
