package com.example.groupieplayground

import android.util.Log
import com.example.groupieplayground.databinding.LayoutFruitItemBinding
import com.xwray.groupie.databinding.BindableItem

class FruitItem(
    val text: String
):BindableItem<LayoutFruitItemBinding>()
{
    override fun getLayout(): Int = R.layout.layout_fruit_item

    override fun bind(viewBinding: LayoutFruitItemBinding, position: Int) {
        viewBinding.text = text
        Log.d("debugger", text)
    }
}