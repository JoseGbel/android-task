package com.example.degree53androidtest.data.models

import android.os.Parcelable
import com.example.degree53androidtest.data.models.GitHubRepo
import kotlinx.android.parcel.Parcelize

@Parcelize
class SearchResponse (val total_count: Int,
                      val incomplete_results: Boolean,
                      val items: List<GitHubRepo>) : Parcelable