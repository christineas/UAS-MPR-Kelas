package com.example.submission2

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GitUser(
        var username: String?,
        var name: String?,
        var photo: String?,
        var company: String?,
        var location: String?,
        var repository: Int,
        var followers: Int,
        var following: Int
) :Parcelable
