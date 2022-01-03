package com.example.notbored.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.notbored.R
import com.example.notbored.databinding.FragmentActivitiesBinding
import com.example.notbored.ui.adapter.AdapterActivities
import com.example.notbored.ui.viewModel.ActivitiesViewModel


class ActivitiesFragment : Fragment() {

    lateinit var binding : FragmentActivitiesBinding
    private val activitiesViewModel by activityViewModels<ActivitiesViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentActivitiesBinding.inflate(inflater, container, false)

        activitiesViewModel.setLiveActivities()

        activitiesViewModel.liveListActivities.observe(viewLifecycleOwner, {
            initRecyclerView(it)
        } )
        return binding.root
    }

    fun initRecyclerView(listActivities: List<String>){

        val adapter = AdapterActivities(listActivities)
        // set rv,  layout and adapter
        binding.rvActivities.layoutManager = LinearLayoutManager(context)
        binding.rvActivities.adapter = adapter

    }



}