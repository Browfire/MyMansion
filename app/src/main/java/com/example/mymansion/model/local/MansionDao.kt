package com.example.mymansion.model.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mymansion.model.MansionDetails
import com.example.mymansion.model.MansionItem

@Dao
interface MansionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllMansionsItems(list: List<MansionItem>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOneMansionDetails(details: MansionDetails)

    @Query("SELECT * FROM mansionItem")
    fun getAllMansionsItems(): LiveData<List<MansionItem>>

    @Query("SELECT * FROM mansionDetails WHERE id=:id")
    fun getOneMansionDetails(id: Int): LiveData<MansionDetails>

}