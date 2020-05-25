package com.example.degree53androidtest.data.models

import android.os.Parcelable
import androidx.annotation.Keep
import com.example.degree53androidtest.data.models.Owner
import kotlinx.android.parcel.Parcelize

/**
 * This DTO holds data to be sent to DetailsFragment for view binding
 */
@Keep
@Parcelize
class RepoDetails (val owner: Owner,
                   val name: String,
                   val forks: Int,
                   val stars: Int,
                   val watchers: Int,
                   val open_issues: Int) : Parcelable