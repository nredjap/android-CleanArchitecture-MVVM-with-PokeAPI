package com.nanoyatsu.example.clean.poke.data.repository

import com.nanoyatsu.example.clean.poke.data.resource.database.dao.PokeDetailDao
import com.nanoyatsu.example.clean.poke.data.resource.database.relation.PokeCacheWithTypeAndAbility
import com.nanoyatsu.example.clean.poke.data.resource.network.PokeNetworkResource
import com.nanoyatsu.example.clean.poke.domain.pokeDetail.PokeDetail
import com.nanoyatsu.example.clean.poke.domain.pokeDetail.PokeDetailRepository

class PokeDetailRepositoryImpl(
    private val networkResource: PokeNetworkResource,
    private val dao: PokeDetailDao
) : PokeDetailRepository {
    override suspend fun get(id: Int): PokeDetail {
        val dbModel = dao.getPoke(id)
            ?: fromNetworkWithCaching(id, networkResource, dao)
        return PokeDetail.from(dbModel)
    }

    private suspend fun fromNetworkWithCaching(
        id: Int, api: PokeNetworkResource, dao: PokeDetailDao
    ): PokeCacheWithTypeAndAbility {
        val convertedApiResult = api.get(id)
            .let(PokeCacheWithTypeAndAbility.Companion::from)
        insertPokeCacheWithTypeAndAbility(convertedApiResult, dao)
        return convertedApiResult
    }

    private suspend fun insertPokeCacheWithTypeAndAbility(
        row: PokeCacheWithTypeAndAbility, dao: PokeDetailDao
    ) {
        dao.insertPoke(row.poke)
        row.types.map { it.type }.let { dao.insertAllTypeIndex(it) }
        row.types.map { it.pokeType }.let { dao.insertAllPokeType(it) }
        row.abilities.map { it.ability }.let { dao.insertAllAbilityIndex(it) }
        row.abilities.map { it.pokeAbility }.let { dao.insertAllPokeAbility(it) }
    }

    override suspend fun refresh(id: Int): PokeDetail {
        val dbModel = fromNetworkWithCaching(id, networkResource, dao)
        return PokeDetail.from(dbModel)
    }
}