package com.example.testtask.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.testtask.Database.DAO
import com.example.testtask.Network.ProfileService
import com.example.testtask.Pojo.Data
import com.example.testtask.Pojo.Page
import com.example.testtask.Pojo.User
import kotlinx.coroutines.launch

class GeneralViewModel(val profileService: ProfileService, val dao: DAO) : ViewModel() {



    fun getProfiles(page: Int): LiveData<Page> = liveData {
        emit(profileService.getProfiles(page))
    }

    fun getUsers(): LiveData<List<User>> = liveData { emit(profileService.getUsers()) }

    fun getLiveDataProfiles() = dao.getAllProfiles()

    fun insertProfile(data: Data) = viewModelScope.launch {
        dao.insert(data)
    }

    fun deleteById(id: Int) = viewModelScope.launch {
        dao.deleteById(id)
    }


    fun update(profile: Data) = viewModelScope.launch {
        dao.update(profile)
    }

    suspend fun checkExistence(id: Int): Boolean {
        return dao.checkExistence(id)
    }


}