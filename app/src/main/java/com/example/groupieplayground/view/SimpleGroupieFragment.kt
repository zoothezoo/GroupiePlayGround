package com.example.groupieplayground.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.groupieplayground.MainViewModel
import com.example.groupieplayground.PersonCardItem
import com.example.groupieplayground.R
import com.xwray.groupie.Group
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.groupiex.plusAssign
import kotlinx.android.synthetic.main.fragment_simple_groupie.*

class SimpleGroupieFragment : Fragment(R.layout.fragment_simple_groupie) {

    private val viewModel: MainViewModel by activityViewModels()
    private val adapter = GroupAdapter<GroupieViewHolder>()

    @ExperimentalStdlibApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler_simple.apply {
            adapter = this@SimpleGroupieFragment.adapter
            layoutManager = GridLayoutManager(context, 2, LinearLayoutManager.VERTICAL, false)
        }


        adapter.apply {
            setOnItemClickListener(viewModel.onItemClickListener(requireContext()))
            setOnItemLongClickListener(viewModel.onItemLongClickListener(requireContext()))
        }


        viewModel.person.observe( viewLifecycleOwner, Observer { list ->
            val groupList = mutableListOf<Group>()
            adapter += groupList
            list.forEach {
                groupList.add(PersonCardItem(person = it, viewModel = viewModel))
            }
            adapter.update(groupList)
        })


    }
}
