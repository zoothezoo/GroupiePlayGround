package com.example.groupieplayground.model

class DataBuilderImpl : DataBuilder {

    @ExperimentalStdlibApi
    override fun buildAllPeople(): List<Person> {
        return buildList {
            for (i in 0..9) {
                this.add(
                    Person(
                        id = i,
                        name = names[i],
                        age = ages[i],
                        hobby = hobbies[i],
                        fav = favs[i]
                    )
                )
            }
        }
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

        val favs = listOf<Boolean>(
            true, false, true, true, false, true, false, false, false, true
        )
    }
}