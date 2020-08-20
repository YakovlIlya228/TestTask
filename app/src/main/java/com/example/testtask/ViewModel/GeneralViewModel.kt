package com.example.testtask.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.testtask.Database.DAO
import com.example.testtask.Network.CallAdapter
import com.example.testtask.Network.RetrofitService
import com.example.testtask.Pojo.Data
import com.example.testtask.Pojo.Page
import kotlinx.coroutines.launch

class GeneralViewModel : ViewModel() {

    val adapter: CallAdapter = RetrofitService.getRetrofit()


    fun getProfiles(page: Int): LiveData<Page> = liveData {
        emit(adapter.getProfiles(page))
    }

    fun getLiveDataProfiles(dao: DAO) = dao.getAllProfiles()


    fun insertProfile(dao: DAO, data: Data) = viewModelScope.launch {
        dao.insert(data)
    }

    fun deleteById(dao: DAO, id: Int) = viewModelScope.launch {
        dao.deleteById(id)
    }


    fun update(dao: DAO, profile: Data) = viewModelScope.launch {
        dao.update(profile)
    }

    suspend fun checkExistence(dao: DAO, id: Int): Boolean {
        return dao.checkExistence(id)
    }


}