package com.example.notbored.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.ui.text.toLowerCase
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.notbored.R
import com.example.notbored.databinding.FragmentActivitiesBinding
import com.example.notbored.ui.adapter.AdapterActivities
import com.example.notbored.ui.viewModel.ActivitiesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ActivitiesFragment : Fragment() {

    lateinit var binding : FragmentActivitiesBinding
    private val activitiesViewModel by activityViewModels<ActivitiesViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentActivitiesBinding.inflate(inflater, container, false)

        // Toast.makeText(context, getBundle(), Toast.LENGTH_SHORT).show()
        activitiesViewModel.setLiveActivities()



        binding.btRandom.setOnClickListener{
            // send API query to obtain a random activity with the participants number
            activitiesViewModel.getRandomActivity(getBundle()!!)

            val bundle = Bundle()
            bundle.putString("participants",getBundle()!!)
            findNavController().navigate(R.id.action_activitiesFragment_to_randomFragment, bundle)
        }

        activitiesViewModel.liveListActivities.observe(viewLifecycleOwner, {
            initRecyclerView(it)
        } )
        return binding.root
    }

    fun initRecyclerView(listActivities: List<String>){

        val adapter = AdapterActivities(listActivities)
        // Method OnClick to get user preference activity
        adapter.setOnItemClickListener(object:AdapterActivities.onItemClickListener{
            override fun onItemCLick(position: Int) {
                // get activity name and number of participants
                activitiesViewModel.getActivity(listActivities[position].lowercase(), getBundle()!!)
                val bundle=Bundle()
                bundle.putString("type",listActivities[position].lowercase())
                bundle.putString("participants",getBundle()!!)

                findNavController().navigate(R.id.action_activitiesFragment_to_recreationalFragment, bundle)
            }
        })
        // set recyclerView  (layout and adapter)
        binding.rvActivities.layoutManager = LinearLayoutManager(context)
        binding.rvActivities.adapter = adapter
    }

    fun getBundle(): String? {
        // participants number
        return arguments?.get("participants").toString()
    }



}