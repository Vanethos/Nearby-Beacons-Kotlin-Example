package com.vanethos.nearbyservice.data.local.converters

import android.arch.persistence.room.TypeConverter
import org.threeten.bp.*

class Converters {
    @TypeConverter
    fun longTimeToDate(value : Long) : LocalDateTime {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(value), ZoneId.systemDefault())
    }

    @TypeConverter
    fun dateToLongTime(value : LocalDateTime) : Long {
        return value.toEpochSecond(OffsetDateTime.now().offset)
    }
}