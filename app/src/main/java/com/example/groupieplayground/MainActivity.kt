package com.example.groupieplayground

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.xwray.groupie.Group
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.groupiex.plusAssign
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = GroupAdapter<GroupieViewHolder>()
        recycler_view.apply{
           this.adapter = adapter
            this.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)


        }

        val groupList = mutableListOf<Group>()

        val list = makeData()

        list.forEach {
            groupList.add(FruitItem(it))
        }

        adapter.apply {
            this.update(groupList)


        }
        Log.d("debugger", "$adapter")


    }

    fun makeData(): MutableList<String>{
        val list = mutableListOf<String>()
        for(i in 0..9){
           list.add("fruit: fruit$i" )
        }

        return list

    }
}
