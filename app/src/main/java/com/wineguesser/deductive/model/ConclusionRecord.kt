package com.wineguesser.deductive.model

import android.os.Parcelable
import com.google.firebase.database.Exclude
import kotlinx.parcelize.Parcelize

@Parcelize
data class ConclusionRecord(
    @get:Exclude var conclusionId: String? = null,
    @get:Exclude var userId: String? = null,
    var actualLabel: String? = null,
    var actualVariety: String? = null,
    var actualCountry: String? = null,
    var actualRegion: String? = null,
    var actualQuality: String? = null,
    var actualVintage: Int? = null,
    var userConclusionVariety: String? = null,
    var userConclusionCountry: String? = null,
    var userConclusionRegion: String? = null,
    var userConclusionQuality: String? = null,
    var userConclusionVintage: Int? = null,
    var appConclusionVariety: String? = null
) : Parcelable
