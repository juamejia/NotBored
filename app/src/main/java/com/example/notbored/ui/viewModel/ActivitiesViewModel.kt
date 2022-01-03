package com.example.notbored.ui.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.notbored.data.repository.ActivitiesRepository

class ActivitiesViewModel: ViewModel() {

    val liveListActivities = MutableLiveData<List<String>>()
    val activitiesRepository = ActivitiesRepository()

    fun setLiveActivities(){
        liveListActivities.postValue(activitiesRepository.getActivities())
    }

}