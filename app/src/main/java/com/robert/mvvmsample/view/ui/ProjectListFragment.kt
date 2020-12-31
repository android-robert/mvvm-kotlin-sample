package com.robert.mvvmsample.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import com.robert.mvvmsample.R
import com.robert.mvvmsample.databinding.FragmentProjectListBinding
import com.robert.mvvmsample.service.model.Project
import com.robert.mvvmsample.view.adapter.ProjectAdapter
import com.robert.mvvmsample.view.callback.ProjectClickCallback
import com.robert.mvvmsample.viewmodel.ProjectListViewModel

class ProjectListFragment : Fragment() {
    private var projectAdapter: ProjectAdapter? = null
    private var binding: FragmentProjectListBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_project_list, container, false)
        projectAdapter = ProjectAdapter(projectClickCallback)
        binding!!.projectList.adapter = projectAdapter
        binding!!.isLoading = true
        return binding!!.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val viewModel = ViewModelProvider(this).get(ProjectListViewModel::class.java)
        observeViewModel(viewModel)
    }

    private fun observeViewModel(viewModel: ProjectListViewModel) {
        // Update the list when the data changes
        viewModel.getProjectListObservable()!!.observe(viewLifecycleOwner, { projects ->
            if (projects != null) {
                binding!!.isLoading = false
                projectAdapter!!.addProjectList(projects)
            }
        })
    }

    private val projectClickCallback: ProjectClickCallback = object : ProjectClickCallback {
        override fun onClick(project: Project?) {
            if (lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)) {
                (activity as MainActivity?)?.show(project)
            }
        }
    }

    companion object {
        const val TAG: String = "ProjectListFragment"
    }
}