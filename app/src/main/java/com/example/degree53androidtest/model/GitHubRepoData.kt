package com.example.degree53androidtest.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class GitHubRepoData (val owner: Owner,
                      val name: String,
                      val forks: Int,
                      val stars: Int,
                      val watchers: Int,
                      val open_issues: Int) : Parcelable