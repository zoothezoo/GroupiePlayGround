package com.example.groupieplayground.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.groupieplayground.MainViewModel
import com.example.groupieplayground.PersonCardItem
import com.example.groupieplayground.R
import com.example.groupieplayground.items.CarouselItem
import com.xwray.groupie.Group
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import kotlinx.android.synthetic.main.fragment_carousel.*

class CarouselFragment : Fragment() {

    val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_carousel, container, false)
    }

    @ExperimentalStdlibApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val groupAdapter = GroupAdapter<GroupieViewHolder>()
        groupAdapter.add(makeCarouselItem())

        recycler_carousel.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = groupAdapter
        }
    }

    @ExperimentalStdlibApi
    private fun makeCarouselItem(): CarouselItem {
        val carouselAdapter = GroupAdapter<GroupieViewHolder>()
        carouselAdapter.apply {
            setOnItemClickListener(viewModel.onItemClickListener(requireContext()))
            setOnItemLongClickListener(viewModel.onItemLongClickListener(requireContext()))
        }
        viewModel.person.observe(viewLifecycleOwner, Observer {
            val people = viewModel.person
            val itemList = buildList<Group> {
                people.value?.forEach {
                    this.add(PersonCardItem(it, viewModel = viewModel))
                }
            }
            carouselAdapter.update(itemList)
        })
        return CarouselItem(carouselAdapter)
    }
}
