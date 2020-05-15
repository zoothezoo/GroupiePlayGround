package com.example.groupieplayground.model

class DataBuilderImpl : DataBuilder {

    override fun buildAllPeople(): List<Person> {
        val list = mutableListOf<Person>()
        for (i in 0..9) {
            list.add(
                Person(
                    id = i,
                    name = names[i],
                    age = ages[i],
                    hobby = hobbies[i]
                )
            )
        }
        return list.toList()
    }

    override fun buildFavoritePeople(): List<Person> {
        val list = mutableListOf<Person>()
        for (i in 0..2) {
            list.add(
                Person(
                    id = i,
                    name = names[i],
                    age = ages[i],
                    hobby = hobbies[i]
                )
            )
        }
        return list.toList()
    }

    override fun buildNormalPeople(): List<Person> {
        val list = mutableListOf<Person>()
        for (i in 3..9) {
            list.add(
                Person(
                    id = i,
                    name = names[i],
                    age = ages[i],
                    hobby = hobbies[i]
                )
            )
        }
        return list.toList()
    }


    companion object {
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
    }

}