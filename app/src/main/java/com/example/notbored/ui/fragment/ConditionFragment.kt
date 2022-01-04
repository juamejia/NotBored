package com.example.notbored.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.notbored.databinding.FragmentConditionBinding
import dagger.hilt.android.AndroidEntryPoint


class ConditionFragment : Fragment() {

    lateinit var binding: FragmentConditionBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentConditionBinding.inflate(inflater,container, false)

        binding.btCancel.setOnClickListener {
            findNavController().popBackStack()
        }

        return binding.root
    }


}