package com.example.elipachecohoyos.moviereviewer.App.Models

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by elipachecohoyos on 10/04/18.
 */
data class Movie(val identifier: Int, val title: String, val rating: String , val largePosterUrl: String) : Parcelable {

    var genres: List<String> = listOf()
    var languages: List<String> = listOf()
    var overView: String = ""

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeInt(identifier)
        dest?.writeString(title)
        dest?.writeString(rating)
        dest?.writeString(largePosterUrl)
    }

    override fun describeContents(): Int {
        return 0
    }

    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString())

    companion object CREATOR : Parcelable.Creator<Movie> {
        override fun createFromParcel(parcel: Parcel): Movie {
            return Movie(parcel)
        }

        override fun newArray(size: Int): Array<Movie?> {
            return arrayOfNulls(size)
        }
    }

}