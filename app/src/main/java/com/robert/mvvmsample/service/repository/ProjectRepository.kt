package com.robert.mvvmsample.service.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.robert.mvvmsample.service.model.Project
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ProjectRepository private constructor() {
    private val gitHubService: GitHubService?
    fun getProjectList(userId: String?): LiveData<MutableList<Project>?> {
        val data = MutableLiveData<MutableList<Project>?>()
        gitHubService!!.getProjectList(userId)!!.enqueue(object : Callback<MutableList<Project>?> {
            override fun onResponse(call: Call<MutableList<Project>?>?, response: Response<MutableList<Project>?>?) {
                data.value = response!!.body()
            }

            override fun onFailure(call: Call<MutableList<Project>?>?, t: Throwable?) {
                // TODO better error handling in part #2 ...
                data.value = null
            }
        })
        return data
    }

    fun getProjectDetails(userID: String?, projectName: String?): LiveData<Project> {
        val data = MutableLiveData<Project>()
        gitHubService!!.getProjectDetails(userID, projectName)!!.enqueue(object : Callback<Project> {
            override fun onResponse(call: Call<Project>?, response: Response<Project>?) {
                simulateDelay()
                data.value = response!!.body()
            }

            override fun onFailure(call: Call<Project>?, t: Throwable?) {
                // TODO better error handling in part #2 ...
                data.value = null
            }
        })
        return data
    }

    private fun simulateDelay() {
        try {
            Thread.sleep(10)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }

    companion object {
        private var projectRepository: ProjectRepository? = null
        @Synchronized
        fun getInstance(): ProjectRepository? {
            //TODO No need to implement this singleton in Part #2 since Dagger will handle it ...
            if (projectRepository == null) {
                if (projectRepository == null) {
                    projectRepository = ProjectRepository()
                }
            }
            return projectRepository
        }
    }

    init {
        //TODO this gitHubService instance will be injected using Dagger in part #2 ...
        val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl(GitHubService.HTTPS_API_GITHUB_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        gitHubService = retrofit.create(GitHubService::class.java)
    }
}