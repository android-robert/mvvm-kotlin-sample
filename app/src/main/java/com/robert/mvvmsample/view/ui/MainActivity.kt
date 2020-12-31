package com.robert.mvvmsample.view.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.robert.mvvmsample.R
import com.robert.mvvmsample.service.model.Project

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Add project list fragment if this is first creation
        if (savedInstanceState == null) {
            val fragment = ProjectListFragment()
            supportFragmentManager.beginTransaction()
                    .add(R.id.fragment_container, fragment, ProjectListFragment.Companion.TAG).commit()
        }
    }

    /** Shows the project detail fragment  */
    fun show(project: Project?) {
        val projectFragment: ProjectFragment = ProjectFragment.forProject(project!!.name)
        supportFragmentManager
                .beginTransaction()
                .addToBackStack("project")
                .replace(R.id.fragment_container,
                        projectFragment, null).commit()
    }
}