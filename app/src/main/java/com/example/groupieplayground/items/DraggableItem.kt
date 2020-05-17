package com.example.groupieplayground.items

import androidx.recyclerview.widget.ItemTouchHelper
import com.example.groupieplayground.MainViewModel
import com.example.groupieplayground.PersonCardItem
import com.example.groupieplayground.model.Person

class DraggableItem(
    person: Person,
    viewModel: MainViewModel
) : PersonCardItem(person, viewModel) {

    override fun getDragDirs(): Int =
        ItemTouchHelper.UP or
                ItemTouchHelper.DOWN or
                ItemTouchHelper.START or
                ItemTouchHelper.END
}