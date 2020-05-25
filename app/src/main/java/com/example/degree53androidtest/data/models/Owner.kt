package com.example.degree53androidtest.data.models

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.android.parcel.Parcelize

/**
 * Model class shaped by the github's Json response to repository search
 */
@Keep
@Parcelize
class Owner(
    val login: String,
    val id: Long
) : Parcelable
