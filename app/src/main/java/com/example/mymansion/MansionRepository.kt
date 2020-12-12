package com.example.mymansion

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.mymansion.model.MansionDetails
import com.example.mymansion.model.MansionRetrofitClient
import com.example.mymansion.model.local.MansionDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MansionRepository(private val myMansionDao: MansionDao) {

    private val myRetrofit = MansionRetrofitClient.retrofitInstance()
    val mansionList = myMansionDao.getAllMansionsItems()

    fun getMansionDetails(id: Int): LiveData<MansionDetails>{
        return myMansionDao.getOneMansionDetails(id)
    }

    fun getMansionsFromApi() = CoroutineScope(Dispatchers.IO).launch{

        val service = kotlin.runCatching { myRetrofit.fetchAllMansions() }
        service.onSuccess {
            when(it.code()) {

                in 200..299 -> it.body()?.let { list ->
                    myMansionDao.insertAllMansionsItems(list)
                }

                in 300..599 -> Log.e("ERROR", it.errorBody().toString())
                else -> Log.d("ERROR", it.errorBody().toString())

            }
        }

        service.onFailure {

            Log.e("ERROR", it.message.toString())

        }
    }

    fun getMansionDetailsFromApi(id: Int) = CoroutineScope(Dispatchers.IO).launch {

        val service = kotlin.runCatching { myRetrofit.fetchOneMansion(id) }
        service.onSuccess {
            when(it.code()) {

                in 200..299 -> it.body()?.let { details ->
                    myMansionDao.insertOneMansionDetails(details)
                }

                in 300..599 -> Log.e("ERROR", it.errorBody().toString())
                else -> Log.d("ERROR", it.errorBody().toString())

            }
        }

        service.onFailure {

            Log.e("ERROR", it.message.toString())

        }

    }
}