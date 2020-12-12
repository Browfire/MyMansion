package com.example.mymansion

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mymansion.model.MansionItem


class FirstFragment : Fragment(), MansionAdapter.PassMansionData {

    private val myViewModel: MansionViewModel by activityViewModels()
    private lateinit var myMansionAdapter: MansionAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        myMansionAdapter= MansionAdapter(this)
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerList)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = myMansionAdapter

        myViewModel.allMansions.observe(viewLifecycleOwner, {
            Log.d("llegan mansiones", it.toString())
            myMansionAdapter.updateAdapter(it)
        })

        myViewModel.getOneMansionDetails(1).observe(viewLifecycleOwner, {

        })

    }

    override fun passItemInfo(mansion: MansionItem) {

        val mansionSelectedId = mansion.id
        myViewModel.mansionSelected(mansionSelectedId)
        findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)

    }
}