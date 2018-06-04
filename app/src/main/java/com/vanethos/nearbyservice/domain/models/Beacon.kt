package com.vanethos.nearbyservice.domain.models

import android.os.Parcel
import android.os.Parcelable
import org.threeten.bp.LocalDateTime

data class Beacon(
        var id: Int = -1,
        var title : String = "",
        var imageUrl : String = "",
        var message : String = "",
        var actionUrl : String = "",
        var actionMessage : String = "",
        var dateAdded : LocalDateTime = LocalDateTime.now()
) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            TODO("dateAdded")) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(title)
        parcel.writeString(imageUrl)
        parcel.writeString(message)
        parcel.writeString(actionUrl)
        parcel.writeString(actionMessage)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Beacon> {
        override fun createFromParcel(parcel: Parcel): Beacon {
            return Beacon(parcel)
        }

        override fun newArray(size: Int): Array<Beacon?> {
            return arrayOfNulls(size)
        }
    }
}