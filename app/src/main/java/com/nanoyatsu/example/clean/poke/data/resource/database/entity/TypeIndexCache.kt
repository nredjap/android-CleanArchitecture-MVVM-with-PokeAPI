package com.nanoyatsu.example.clean.poke.data.resource.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.nanoyatsu.example.clean.poke.data.resource.network.model.NamedApiResource

@Entity(tableName = "type_index_cache")
data class TypeIndexCache(
    @PrimaryKey val id: Int,
    val name: String
) {
    companion object {
        fun from(data: NamedApiResource): TypeIndexCache {
            return TypeIndexCache(data.id, data.name)
        }
    }
}