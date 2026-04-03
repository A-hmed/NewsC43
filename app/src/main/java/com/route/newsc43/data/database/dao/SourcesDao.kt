package com.route.newsc43.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.route.newsc43.data.api.model.SourceDM

@Dao
interface SourcesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveSources(sources: List<SourceDM>)


    @Query("select * from SourceDM where category =:category")
    suspend fun getSources(category: String): List<SourceDM>

    @Query("select * from SourceDM")
    suspend fun getAllSources(): List<SourceDM>
}