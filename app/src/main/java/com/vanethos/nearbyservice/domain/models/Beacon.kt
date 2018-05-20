package com.vanethos.nearbyservice.domain.models

import org.threeten.bp.LocalDateTime

data class Beacon(
        val uid: Int = 0,
        val title : String = "",
        val imageUrl : String = "",
        val message : String = "",
        val actionUrl : String = "",
        val actionMessage : String = "",
        val dateAdded : LocalDateTime = LocalDateTime.now()
)