package com.example.mymansion

import androidx.lifecycle.LiveData
import com.example.mymansion.model.MansionDetails
import com.example.mymansion.model.MansionRetrofitClient
import com.example.mymansion.model.local.MansionDao

class MansionRepository(private val myMansionDao: MansionDao) {

    private val myRetrofit = MansionRetrofitClient.retrofitInstance()
    val mansionList = myMansionDao.getAllMansionsItems()

    fun getMansionDetails(id: Int): LiveData<MansionDetails>{
        return myMansionDao.getOneMansionDetails(id)
    }

}