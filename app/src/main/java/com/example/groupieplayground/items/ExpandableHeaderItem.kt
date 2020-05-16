package com.example.groupieplayground.items

import androidx.lifecycle.LifecycleOwner
import com.example.groupieplayground.R
import com.example.groupieplayground.databinding.LayoutExpandableHeaderBinding
import com.xwray.groupie.ExpandableGroup
import com.xwray.groupie.ExpandableItem
import com.xwray.groupie.databinding.BindableItem

class ExpandableHeaderItem(
    private val id: Int,
    private val title: String,
    private val lifecycleOwner: LifecycleOwner
) : BindableItem<LayoutExpandableHeaderBinding>(), ExpandableItem {

    private lateinit var expandableGroup: ExpandableGroup

    override fun bind(viewBinding: LayoutExpandableHeaderBinding, position: Int) {
        viewBinding.title = title
        viewBinding.lifecycleOwner = lifecycleOwner
        viewBinding.root.setOnClickListener {
            expandableGroup.onToggleExpanded()
            viewBinding.expandIcon.setImageResource(setIcon())

        }
    }

    override fun getLayout(): Int = R.layout.layout_expandable_header

    override fun setExpandableGroup(onToggleListener: ExpandableGroup) {
        expandableGroup = onToggleListener
    }

    private fun setIcon() = when (expandableGroup.isExpanded) {
        true -> R.drawable.ic_expand_less_black_24dp
        false -> R.drawable.ic_expand_more_black_24dp
    }

    override fun getId(): Long = id.toLong()
}