package com.example.degree53androidtest.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Owner (val login: String,
             val id: Long,
             val html_url: String) : Parcelable
