package com.example.degree53androidtest.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class GitHubRepo (val name: String,
                  val owner: Owner,
                  val forks: Int,
                  val open_issues: Int,
                  val watchers: Int) : Parcelable