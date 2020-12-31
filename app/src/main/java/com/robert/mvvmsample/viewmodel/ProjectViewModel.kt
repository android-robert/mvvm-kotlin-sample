package com.robert.mvvmsample.viewmodel

import android.app.Application
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory
import com.robert.mvvmsample.service.model.Project
import com.robert.mvvmsample.service.repository.ProjectRepository

class ProjectViewModel(application: Application, private val projectID: String?) : AndroidViewModel(application) {
    private val projectObservable: LiveData<Project> = ProjectRepository.getInstance()!!.getProjectDetails("Google", projectID)

    @JvmField
    var project: ObservableField<Project> = ObservableField()
    fun getObservableProject(): LiveData<Project> {
        return projectObservable
    }

    fun setProject(project: Project?) {
        this.project.set(project)
    }

    /**
     * A creator is used to inject the project ID into the ViewModel
     */
    class Factory(private val application: Application, private val projectID: String?) : NewInstanceFactory() {
        override fun <T : ViewModel?> create(modelClass: Class<T?>): T {
            return ProjectViewModel(application, projectID) as T
        }
    }

}