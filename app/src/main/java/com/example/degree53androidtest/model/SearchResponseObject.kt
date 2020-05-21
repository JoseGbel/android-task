package com.example.degree53androidtest.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class SearchResponseObject (val total_count: Int,
                            val incomplete_results: Boolean,
                            val items: List<GitHubRepo>) : Parcelable