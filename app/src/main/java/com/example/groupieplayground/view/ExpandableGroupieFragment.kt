package com.example.groupieplayground.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.groupieplayground.MainViewModel
import com.example.groupieplayground.PersonCardItem
import com.example.groupieplayground.R
import com.example.groupieplayground.items.ExpandableHeaderItem
import com.example.groupieplayground.model.Person
import com.xwray.groupie.ExpandableGroup
import com.xwray.groupie.Group
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.fragment_expandable_groupie.*

class ExpandableGroupieFragment : Fragment(R.layout.fragment_expandable_groupie) {

    private val viewModel: MainViewModel by activityViewModels()

    @ExperimentalStdlibApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val groupAdapter = GroupAdapter<GroupieViewHolder>().apply {
            spanCount = 2
            setOnItemClickListener(viewModel.onItemClickListener(requireContext()))
            setOnItemLongClickListener(viewModel.onItemLongClickListener(requireContext()))
        }

        recycler_expandable.apply {
            this.layoutManager = GridLayoutManager(context, groupAdapter.spanCount).apply {
                spanSizeLookup = groupAdapter.spanSizeLookup
            }
            this.adapter = groupAdapter
        }


        var favList = listOf<Person>()
        var norList = listOf<Person>()
        var favGroup = listOf<PersonCardItem>()
        var norGroup = listOf<PersonCardItem>()

        viewModel.person.observe(viewLifecycleOwner, Observer { list ->
            list.let { nonNullList ->
                favList = nonNullList.filter { it.fav }
                norList = nonNullList.filter { !it.fav }
            }

            favGroup = buildList<PersonCardItem> {
                favList.forEach {
                    this.add(PersonCardItem(it, viewModel = viewModel))
                }
            }

            norGroup = buildList<PersonCardItem> {
                norList.forEach {
                    this.add(PersonCardItem(it, viewModel = viewModel))
                }
            }


            val favorite =
                ExpandableGroup(ExpandableHeaderItem(0, "Favorite", viewLifecycleOwner), true)
                    .apply {
                        addAll(favGroup)
                    }
            val normal =
                ExpandableGroup(ExpandableHeaderItem(1, "Normal", viewLifecycleOwner), true)
                    .apply {
                        addAll(norGroup)
                    }

            val all = mutableListOf<Group>()
            all += favorite
            all += normal
            groupAdapter.update(all)

        })
    }
}

