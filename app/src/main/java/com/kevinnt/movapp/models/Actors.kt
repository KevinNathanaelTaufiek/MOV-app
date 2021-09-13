package com.kevinnt.movapp.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Actors (
    var nama: String? = "",
    var url: String? = ""
): Parcelable