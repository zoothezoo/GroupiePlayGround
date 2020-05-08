package com.example.groupieplayground.model

class DataBuilder {
    val names = listOf<String>(
        "Ando",
        "Bando",
        "Chihiro",
        "Daisuke",
        "Eri",
        "Fujikawa",
        "Gushiken",
        "Hayato",
        "Ikeda",
        "Jiro"
    )

    val ages = listOf<Int>(
        22, 30, 12, 34, 23, 41, 15, 19, 28, 30
    )

    val hobbies = listOf<String>(
        "baseball",
        "music",
        "running",
        "programming",
        "cooking",
        "reading",
        "movie",
        "sleeping",
        "youtube",
        "talking"
    )

    fun makePersonList(): List<Person> {
        val list = mutableListOf<Person>()
        for (i in 0..9) {
            list.add(
                Person(
                    name = names[i],
                    age = ages[i],
                    hobby = hobbies[i]
                )
            )
        }
        return list.toList()
    }
}