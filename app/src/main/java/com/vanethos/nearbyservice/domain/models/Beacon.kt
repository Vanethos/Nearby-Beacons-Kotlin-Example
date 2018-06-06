package com.vanethos.nearbyservice.domain.models

import android.os.Parcel
import android.os.Parcelable
import org.threeten.bp.LocalDateTime

data class Beacon(
        var id: Int = -1,
        var title: String = "",
        var imageUrl: String = "",
        var message: String = "",
        var actionUrl: String = "",
        var actionMessage: String = "",
        var dateAdded: LocalDateTime = LocalDateTime.now()
) : Parcelable {
    constructor(source: Parcel) : this(
            source.readInt(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readSerializable() as LocalDateTime
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeInt(id)
        writeString(title)
        writeString(imageUrl)
        writeString(message)
        writeString(actionUrl)
        writeString(actionMessage)
        writeSerializable(dateAdded)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Beacon> = object : Parcelable.Creator<Beacon> {
            override fun createFromParcel(source: Parcel): Beacon = Beacon(source)
            override fun newArray(size: Int): Array<Beacon?> = arrayOfNulls(size)
        }
    }
}