package org.d3if4076.modul4.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.d3if4076.modul4.Hewan
import org.d3if4076.modul4.network.ApiStatus
import org.d3if4076.modul4.network.HewanApi

class MainViewModel : ViewModel() {
    private val data = MutableLiveData<List<Hewan>>()
    private val status = MutableLiveData<ApiStatus>()
    init {
        retrieveData()
    }
    private fun retrieveData() {
        viewModelScope.launch {
            status.value = ApiStatus.LOADING
            try {
                data.value = HewanApi.service.getHewan()
                status.value = ApiStatus.SUCCESS
            } catch (e: Exception) {
                Log.d("MainViewModel", "Failure: ${e.message}")
                status.value = ApiStatus.FAILED
            }
        }
    }

    fun getData(): LiveData<List<Hewan>> = data
    fun getStatus(): LiveData<ApiStatus> = status
}