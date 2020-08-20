package com.example.testtask.ViewModel

import androidx.lifecycle.*
import com.example.testtask.Database.DAO
import com.example.testtask.Network.CallAdapter
import com.example.testtask.Network.RetrofitService
import com.example.testtask.Pojo.Data
import com.example.testtask.Pojo.Page
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class GeneralViewModel: ViewModel() {

    val adapter: CallAdapter = RetrofitService.getRetrofit()


    fun getProfiles(page: Int): LiveData<Page> = liveData {
        emit(adapter.getProfiles(page))
    }

    fun getLiveDataProfiles(dao: DAO): LiveData<List<Data>> = liveData{
        emit(dao.getAllProfiles())
    }

    suspend fun getCachedProfiles(dao: DAO): List<Data>{
        return dao.getAllProfiles()
    }

    fun insertProfile(dao: DAO,data: Data) = viewModelScope.launch {
        dao.insert(data)
    }

    fun deleteById(dao: DAO, id: Int) = viewModelScope.launch {
        dao.deleteById(id)
    }

    fun updateById(dao: DAO,id: Int,firstName: String, lastName: String, email: String) = viewModelScope.launch {
        dao.updateById(id,firstName,lastName,email)
    }

    fun update(dao: DAO,profile: Data) = viewModelScope.launch{
        dao.update(profile)
    }

    suspend fun checkExistence(dao: DAO, id: Int): Boolean {
        return dao.checkExistence(id)
    }



}