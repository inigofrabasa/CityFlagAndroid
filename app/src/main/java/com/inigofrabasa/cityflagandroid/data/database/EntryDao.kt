package com.inigofrabasa.cityflagandroid.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.inigofrabasa.cityflagandroid.data.model.Model

@Dao
interface EntryDao {
    @Query("SELECT * FROM entriesTable")
    fun getApplications(): LiveData<List<Model.RoomEntry>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(entries: List<Model.RoomEntry>)
}