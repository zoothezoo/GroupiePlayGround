package com.example.groupieplayground

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.groupieplayground.model.DataBuilder
import com.example.groupieplayground.model.DataBuilderImpl
import com.example.groupieplayground.model.Person

class MainViewModel : ViewModel() {

    private val dataBuilder: DataBuilder = DataBuilderImpl()

    private val _person = MutableLiveData<List<Person>>()
    val person: LiveData<List<Person>> get() = _person
    private val _favPerson = MutableLiveData<List<Person>>()
    val favPerson: LiveData<List<Person>> get() = _favPerson
    private val _norPerson = MutableLiveData<List<Person>>()
    val norPerson: LiveData<List<Person>> get() = _norPerson

    init {
        getAllPeople()
        getNormalPeople()
        getFavoritePeople()
    }

    fun getAllPeople() {
        _person.value = dataBuilder.buildAllPeople()
    }

    fun getFavoritePeople() {
        _favPerson.value = dataBuilder.buildFavoritePeople()
    }

    fun getNormalPeople() {
        _norPerson.value = dataBuilder.buildNormalPeople()
    }
}