package com.example.notbored.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.notbored.R
import com.example.notbored.databinding.FragmentActivitiesBinding
import com.example.notbored.databinding.FragmentRandomBinding
import com.example.notbored.ui.viewModel.ActivitiesViewModel
import dagger.hilt.android.AndroidEntryPoint



class RandomFragment : Fragment() {
    lateinit var binding: FragmentRandomBinding
    private val activitiesViewModel by activityViewModels<ActivitiesViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRandomBinding.inflate(inflater, container, false)

        activitiesViewModel.setLiveActivities()

        binding.btCancel.setOnClickListener {
            findNavController().popBackStack()
        }

        activitiesViewModel.liveResponseActivity.observe(viewLifecycleOwner, {
            binding.tvResultPrice.text = convertPrice(it.price)
            binding.tvResultParticipants.text = it.participants.toString()
            binding.tvType.text = it.type
            binding.tvRandomActivity.text = it.activity
        })

        binding.btTryAnother.setOnClickListener{
            activitiesViewModel.getRandomActivity(getParticipants())
        }

        return binding.root
    }

    private fun convertPrice(priceDouble: Double): String{
        return when (priceDouble) {
            0.0         -> "Free"
            in 0.1..0.3 -> "Low"
            in 0.4..0.6 -> "Medium"
            else        -> "High"
        }
    }

    fun getParticipants(): String {
        return arguments?.get("participants").toString()
    }
}