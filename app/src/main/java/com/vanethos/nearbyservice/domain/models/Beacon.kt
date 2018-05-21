package com.vanethos.nearbyservice.domain.models

import org.threeten.bp.LocalDateTime

data class Beacon(
        var uid: Int = 0,
        var title : String = "",
        var imageUrl : String = "",
        var message : String = "",
        var actionUrl : String = "",
        var actionMessage : String = "",
        var dateAdded : LocalDateTime = LocalDateTime.now()
)