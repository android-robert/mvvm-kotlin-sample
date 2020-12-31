package com.robert.mvvmsample.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.robert.mvvmsample.service.model.Project
import com.robert.mvvmsample.service.repository.ProjectRepository

class ProjectListViewModel(application: Application) : AndroidViewModel(application) {
    private val projectListObservable: LiveData<MutableList<Project>?>?

    /**
     * Expose the LiveData Projects query so the UI can observe it.
     */
    fun getProjectListObservable(): LiveData<MutableList<Project>?>? {
        return projectListObservable
    }

    init {

        // If any transformation is needed, this can be simply done by Transformations class ...
        projectListObservable = ProjectRepository.getInstance()!!.getProjectList("Google")
    }
}