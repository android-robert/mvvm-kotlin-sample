package com.robert.mvvmsample.service.repository

import com.robert.mvvmsample.service.model.Project
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

internal interface GitHubService {
    @GET("users/{user}/repos")
    open fun getProjectList(@Path("user") user: String?): Call<MutableList<Project>?>?

    @GET("/repos/{user}/{reponame}")
    open fun getProjectDetails(@Path("user") user: String?, @Path("reponame") projectName: String?): Call<Project>?

    companion object {
        const val HTTPS_API_GITHUB_URL: String = "https://api.github.com/"
    }
}