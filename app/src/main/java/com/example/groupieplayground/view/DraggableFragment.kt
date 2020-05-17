package com.example.groupieplayground.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.groupieplayground.MainViewModel
import com.example.groupieplayground.R
import com.example.groupieplayground.items.DraggableItem
import com.xwray.groupie.Group
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Section
import com.xwray.groupie.TouchCallback
import com.xwray.groupie.groupiex.plusAssign
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import kotlinx.android.synthetic.main.fragment_draggable.*

class DraggableFragment : Fragment() {

    private val viewModel: MainViewModel by activityViewModels()
    private val groupAdapter = GroupAdapter<GroupieViewHolder>()
    val dragSection = Section()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_draggable, container, false)
    }

    @ExperimentalStdlibApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        groupAdapter.apply {
            spanCount = 2
            setOnItemClickListener(viewModel.onItemClickListener(requireContext()))
            setOnItemLongClickListener(viewModel.onItemLongClickListener(requireContext()))
        }

        recycler_draggable.apply {
            this.layoutManager = GridLayoutManager(context, groupAdapter.spanCount).apply {
                spanSizeLookup = groupAdapter.spanSizeLookup
            }
            this.adapter = groupAdapter
        }

        viewModel.person.observe(viewLifecycleOwner, Observer { list ->
            val aList = list.map {
                DraggableItem(it, viewModel)
            }
            aList.forEach {
                dragSection += it
            }

            ItemTouchHelper(touchCallback).attachToRecyclerView(recycler_draggable)
            groupAdapter.update(aList)
        })
    }

    private val touchCallback: TouchCallback by lazy {
        object : TouchCallback() {
            override fun onMove(
                recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                val item = groupAdapter.getItem(viewHolder.adapterPosition)
                val targetItem = groupAdapter.getItem(target.adapterPosition)

                val dragItems = mutableListOf<Group>()

                for (i in 0 until dragSection.itemCount) {
                    dragItems.add(dragSection.getGroup(i))
                }
                val targetIndex = dragItems.indexOf(targetItem)
                dragItems.remove(item)
                dragItems.add(targetIndex, item)
                groupAdapter.update(dragItems)
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                TODO("Not yet implemented")
            }
        }
    }
}
