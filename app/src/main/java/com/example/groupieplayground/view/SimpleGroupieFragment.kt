package com.example.groupieplayground.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.groupieplayground.PersonCardItem
import com.example.groupieplayground.R
import com.example.groupieplayground.model.DataBuilder
import com.xwray.groupie.Group
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.fragment_simple_groupie.*

class SimpleGroupieFragment : Fragment(R.layout.fragment_simple_groupie) {

/*
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_simple_groupie, container, false)
    }
*/

    private val adapter = GroupAdapter<GroupieViewHolder>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler_simple.apply {
            adapter = this@SimpleGroupieFragment.adapter
            //layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            layoutManager = GridLayoutManager(context, 2, LinearLayoutManager.VERTICAL,  false)
        }

        val groupList = mutableListOf<Group>()
        val list = DataBuilder().makePersonList()
        Log.d("debugger", "$list")
        list.forEach {
            groupList.add(PersonCardItem(it))
        }
        Log.d("debugger", "$groupList")
        adapter.apply {
            update(groupList)
        }
    }

}
