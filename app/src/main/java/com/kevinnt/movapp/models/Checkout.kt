package com.kevinnt.movapp.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Checkout (
    var seat: String? = "",
    var price: Int? = 0
): Parcelable