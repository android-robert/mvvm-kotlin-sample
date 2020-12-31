package com.robert.mvvmsample.view.callback

import com.robert.mvvmsample.service.model.Project

interface ProjectClickCallback {
    fun onClick(project: Project?)
}