package com.example.testapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ColorViewModel: ViewModel() {

    var colorDatabase: ColorDatabase? = null

    private val _colors = MutableLiveData<List<Color>>()
    val colors: LiveData<List<Color>>
        get() = _colors

    fun getColors(){
        viewModelScope.launch(Dispatchers.IO) {
            val arrayOfColor= colorDatabase?.colorDao()?.getAll()
            _colors.postValue(arrayOfColor?.toList())

            withContext(Dispatchers.Main){

            }
        }
    }

    fun getNumber(): Deferred<Int> {
        return viewModelScope.async {
            delay(10000)
            10
        }
    }

    fun insertColor(color: Color){
        viewModelScope.launch(Dispatchers.IO) {
            val arrayOfColor= colorDatabase?.colorDao()?.insert(color)
            getColors()
        }
    }
}