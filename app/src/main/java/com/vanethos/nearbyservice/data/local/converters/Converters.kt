package com.vanethos.nearbyservice.data.local.converters

import android.arch.persistence.room.TypeConverter
import org.threeten.bp.Instant
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZoneId

class Converters {
    @TypeConverter
    fun longTimeToDate(value : Long) : LocalDateTime {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(value), ZoneId.systemDefault())
    }

    @TypeConverter
    fun dateToLongTime(value : LocalDate) : Long {
        return value.toEpochDay()
    }
}