package com.example.groupieplayground.model

interface DataBuilder {

    fun buildAllPeople(): List<Person>

    fun buildFavoritePeople(): List<Person>

    fun buildNormalPeople(): List<Person>

}