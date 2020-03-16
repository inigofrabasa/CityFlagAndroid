package com.inigofrabasa.cityflagandroid.data.repository

import com.inigofrabasa.cityflagandroid.data.database.EntryDao
import com.inigofrabasa.cityflagandroid.data.model.Model
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RoomRepository private constructor(private val entryDao: EntryDao) {

    suspend fun insertEntries(entries: List<Model.RoomEntry>) {
        withContext(Dispatchers.IO) {
            entryDao.insertAll(entries)
        }
    }

    fun getEntriesDataBase() = entryDao.getApplications()

    companion object {
        @Volatile private var instance: RoomRepository? = null
        fun getInstance(entryDao: EntryDao) =
            instance
                ?: synchronized(this) {
                instance
                    ?: RoomRepository(
                        entryDao
                    ).also { instance = it }
            }
    }
}