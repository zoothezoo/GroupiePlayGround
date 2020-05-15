package com.example.groupieplayground.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.groupieplayground.MainViewModel
import com.example.groupieplayground.PersonCardItem
import com.example.groupieplayground.R
import com.example.groupieplayground.items.ExpandableHeaderItem
import com.xwray.groupie.ExpandableGroup
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Section
import kotlinx.android.synthetic.main.fragment_expandable_groupie.*

class ExpandableGroupieFragment : Fragment(R.layout.fragment_expandable_groupie) {

    private val viewModel: MainViewModel by activityViewModels()
    private val normalSection = Section()
    private val favoriteSection = Section()

    @ExperimentalStdlibApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val favPeople = viewModel.favPerson.value
        val norPeople = viewModel.norPerson.value

        val groupAdapter = GroupAdapter<GroupieViewHolder>().apply {
            spanCount = 2
        }

        val favList = buildList<PersonCardItem> {
            favPeople?.forEach {
                this.add(PersonCardItem(it, viewModel = viewModel))
            }
        }

        val norList = buildList<PersonCardItem> {
            norPeople?.forEach {
                this.add(PersonCardItem(it, viewModel = viewModel))
            }
        }

        recycler_expandable.apply {
            this.layoutManager = GridLayoutManager(context, groupAdapter.spanCount).apply {
                spanSizeLookup = groupAdapter.spanSizeLookup
            }
            this.adapter = groupAdapter
        }

        ExpandableGroup(ExpandableHeaderItem("Favorite", viewLifecycleOwner), true).apply {
            favoriteSection.addAll(favList)
            add(favoriteSection)
            groupAdapter.add(this)
        }

        ExpandableGroup(ExpandableHeaderItem("Normal", viewLifecycleOwner), true).apply {
            normalSection.addAll(norList)
            add(normalSection)
            groupAdapter.add(this)
        }
    }
}

