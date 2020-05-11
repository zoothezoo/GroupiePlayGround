package com.example.groupieplayground.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.groupieplayground.R
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        button_simple.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_simpleGroupieFragment)
        }
        button_expandable.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_expandableGroupieFragment)
        }
    }

}
