package com.example.degree53androidtest.data.models

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.android.parcel.Parcelize


/**
 * Model class shaped by the github's Json response to repository search
 */
@Keep
@Parcelize
class GitHubRepo (val id: Long,
                  val name: String,
                  val full_name: String,
                  val owner: Owner,
                  val forks: Int,
                  val open_issues: Int,
                  val watchers: Int,
                  val language: String,
                  val description: String,
                  val updated_at: String,
                  val stargazers_count: Int) : Parcelable