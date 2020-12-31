package com.robert.mvvmsample.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.robert.mvvmsample.R
import com.robert.mvvmsample.databinding.ProjectListItemBinding
import com.robert.mvvmsample.service.model.Project
import com.robert.mvvmsample.view.adapter.ProjectAdapter.ProjectViewHolder
import com.robert.mvvmsample.view.callback.ProjectClickCallback

class ProjectAdapter(private val projectClickCallback: ProjectClickCallback?) : RecyclerView.Adapter<ProjectViewHolder?>() {
    var projectList: MutableList<Project>? = null

    fun addProjectList(projectList: MutableList<Project>?) {
        if (this.projectList == null) {
            this.projectList = projectList
            notifyItemRangeInserted(0, projectList!!.size)
        } else {
            val result = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
                override fun getOldListSize(): Int {
                    return this@ProjectAdapter.projectList!!.size
                }

                override fun getNewListSize(): Int {
                    return projectList!!.size
                }

                override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                    return this@ProjectAdapter.projectList!![oldItemPosition].id ==
                            projectList!![newItemPosition].id
                }

                override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                    val project = projectList!![newItemPosition]
                    val old = projectList[oldItemPosition]
                    return (project.id == old.id && project.git_url == old.git_url)
                }
            })
            this.projectList = projectList
            result.dispatchUpdatesTo(this)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjectViewHolder {
        val binding: ProjectListItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.project_list_item, parent, false)
        binding.callback = projectClickCallback
        return ProjectViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProjectViewHolder, position: Int) {
        holder.binding.project = projectList!![position]
        holder.binding.executePendingBindings()
    }

    override fun getItemCount(): Int {
        return if (projectList == null) 0 else projectList!!.size
    }

    class ProjectViewHolder(val binding: ProjectListItemBinding) : RecyclerView.ViewHolder(binding.root)
}