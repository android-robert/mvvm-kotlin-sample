package com.robert.mvvmsample.service.model

import java.util.*

class Project {
    var id: Long = 0
    @JvmField
    var name: String? = null
    var full_name: String? = null
    var owner: User? = null
    var html_url: String? = null
    @JvmField
    var description: String? = null
    var url: String? = null
    @JvmField
    var created_at: Date? = null
    @JvmField
    var updated_at: Date? = null
    var pushed_at: Date? = null
    var git_url: String? = null
    var ssh_url: String? = null
    @JvmField
    var clone_url: String? = null
    var svn_url: String? = null
    var homepage: String? = null
    var stargazers_count = 0
    var watchers_count = 0
    @JvmField
    var language: String? = null
    var has_issues = false
    var has_downloads = false
    var has_wiki = false
    var has_pages = false
    var forks_count = 0
    var open_issues_count = 0
    var forks = 0
    @JvmField
    var open_issues = 0
    @JvmField
    var watchers = 0
    var default_branch: String? = null

    constructor() {}
    constructor(name: String?) {
        this.name = name
    }
}