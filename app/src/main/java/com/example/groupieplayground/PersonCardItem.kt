package com.example.groupieplayground

import com.example.groupieplayground.databinding.LayoutPersonCardItemBinding
import com.example.groupieplayground.model.Person
import com.xwray.groupie.databinding.BindableItem

class PersonCardItem(
    val person: Person,
    val viewModel: MainViewModel
) : BindableItem<LayoutPersonCardItemBinding>() {
    override fun getLayout(): Int = R.layout.layout_person_card_item

    override fun bind(viewBinding: LayoutPersonCardItemBinding, position: Int) {
        viewBinding.name = person.name
        viewBinding.age = person.age
        viewBinding.hobby = person.hobby
        viewBinding.clear.setOnClickListener{ viewModel.delete(position) }
    }

    override fun getSpanSize(spanCount: Int, position: Int) = spanCount / 2

    override fun getId(): Long = person.id.toLong()

}