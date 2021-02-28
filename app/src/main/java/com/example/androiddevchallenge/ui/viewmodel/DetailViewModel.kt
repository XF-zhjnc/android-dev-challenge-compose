package com.example.androiddevchallenge.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DetailViewModel : ViewModel() {
    val pet: LiveData<String> = MutableLiveData("sss")
    val isStarred: LiveData<Boolean> = MutableLiveData(false)
    val isAdopted: LiveData<Boolean> = MutableLiveData(false)
}