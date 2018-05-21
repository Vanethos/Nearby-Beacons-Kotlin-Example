package com.vanethos.nearbyservice.domain.mappers

import com.vanethos.nearbyservice.data.local.entities.BeaconEntity
import com.vanethos.nearbyservice.domain.models.Beacon
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.factory.Mappers

@Mapper
interface BeaconMapper {
    companion object {
        val Instance = Mappers.getMapper(BeaconMapper::class.java)
    }

    fun mapToDomain (beaconEntity: BeaconEntity) : Beacon
    fun mapListToDomain (beaconEntity: List<BeaconEntity>) : List<Beacon>

    @Mapping(target = "uid", ignore = true)
    fun mapToData (beaconEntity: Beacon) : BeaconEntity
    fun mapListToData (beaconEntity: List<Beacon>) : List<BeaconEntity>
}