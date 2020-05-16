package com.example.groupieplayground.items

import androidx.recyclerview.widget.LinearLayoutManager
import com.example.groupieplayground.R
import com.example.groupieplayground.databinding.LayoutCarouselItemBinding
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.databinding.BindableItem
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder

class CarouselItem(
    private val carouselAdapter: GroupAdapter<GroupieViewHolder>
) : BindableItem<LayoutCarouselItemBinding>() {

    override fun getLayout(): Int = R.layout.layout_carousel_item

    override fun bind(viewBinding: LayoutCarouselItemBinding, position: Int) {
        viewBinding.recyclerCarouselHorizonal.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = carouselAdapter
        }
    }

    override fun getId(): Long = id

}