package com.android.applemarket

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AppleItem(
    val appleImg: Int,
    val appleName: String,
    val appleIntro: String,
    val appleSeller: String,
    val applePrice: String,
    val appleAddress: String,
    val appleHeart: Int,
    val appleChat: Int
) : Parcelable
