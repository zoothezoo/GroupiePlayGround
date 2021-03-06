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

    init {
        getAllPeople()
    }

    private fun getAllPeople() {
        _person.value = dataBuilder.buildAllPeople()
    }

    fun deleteInPerson(id: Int) {
        val list = _person.value?.filter { it.id != id }
        _person.value = list
    }

    fun onItemClickListener(context: Context) = OnItemClickListener { item, view ->
        if (item is PersonCardItem && !TextUtils.isEmpty(item.person.name)) {
            val id = item.person.id
            val list = _person.value?.map {
                if (it.id == id) {
                    it.apply { fav = fav.not() }
                } else {
                    it
                }
            }
            _person.value = list
            Toast.makeText(context, "${item.person.fav}", Toast.LENGTH_SHORT).show()
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