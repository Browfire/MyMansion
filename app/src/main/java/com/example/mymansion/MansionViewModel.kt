package com.example.mymansion

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mymansion.model.MansionDetails
import com.example.mymansion.model.MansionItem
import com.example.mymansion.model.local.MansionDataBase

class MansionViewModel(application: Application): AndroidViewModel(application) {

    private val myRepository: MansionRepository
    val allMansions: LiveData<List<MansionItem>>
    val mansionSelection = MutableLiveData<Int>()

    init{
        val myDao = MansionDataBase.getDatabase(application).mansionDao()
        myRepository = MansionRepository(myDao)
        allMansions = myRepository.mansionList
        myRepository.getMansionsFromApi()
    }

    fun getOneMansionDetails(id: Int): LiveData<MansionDetails>{
        myRepository.getMansionDetailsFromApi(id)   //consulta a inet
        return myRepository.getMansionDetails(id)   //consulta a base
    }

    fun mansionSelected (mansionId: Int){
        mansionSelection.value = mansionId
    }
}