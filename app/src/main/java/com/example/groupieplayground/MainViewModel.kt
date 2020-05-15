package com.example.groupieplayground

import android.content.Context
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.groupieplayground.model.DataBuilder
import com.example.groupieplayground.model.DataBuilderImpl
import com.example.groupieplayground.model.Person
import com.xwray.groupie.OnItemClickListener
import com.xwray.groupie.OnItemLongClickListener

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

    private fun getAllPeople() {
        _person.value = dataBuilder.buildAllPeople()
    }

    private fun getFavoritePeople() {
        _favPerson.value = dataBuilder.buildFavoritePeople()
    }

    private fun getNormalPeople() {
        _norPerson.value = dataBuilder.buildNormalPeople()
    }

    fun delete(position: Int) {
        val personlist = _person.value
        personlist?.let {
            if (position != -1 && position < personlist.size) {
                val a = _person.value?.toMutableList()
                a?.removeAt(position)
                _person.value = a?.toList()
            }
        }
    }

    fun onItemClickListener(context: Context) = OnItemClickListener { item, view ->
        Log.d("debugger", "touched!!")
        if (item is PersonCardItem && !TextUtils.isEmpty(item.person.name)) {
            Toast.makeText(context, item.person.name, Toast.LENGTH_SHORT).show()
        }
    }

    @ExperimentalStdlibApi
    fun onItemLongClickListener(context: Context) =
        OnItemLongClickListener { item, view ->
            if (item is PersonCardItem && !item.person.name.isNullOrBlank()) {
                Toast.makeText(
                    context,
                    "Long clicked:Delete this " + item.person.name,
                    Toast.LENGTH_SHORT
                ).show()
                return@OnItemLongClickListener true
            }
            false
        }
}