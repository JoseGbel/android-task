package com.example.degree53androidtest.data.models

import android.os.Parcelable
import com.example.degree53androidtest.data.models.Owner
import kotlinx.android.parcel.Parcelize

@Parcelize
class RepoDetails (val owner: Owner,
                   val name: String,
                   val forks: Int,
                   val stars: Int,
                   val watchers: Int,
                   val open_issues: Int) : Parcelable