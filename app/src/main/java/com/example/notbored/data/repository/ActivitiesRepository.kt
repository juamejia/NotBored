package com.example.notbored.data.repository

import com.example.notbored.data.model.Activities

class ActivitiesRepository {
    private val activities = Activities()

    fun getActivities(): List<String>{
        return activities.listActivities
    }

}