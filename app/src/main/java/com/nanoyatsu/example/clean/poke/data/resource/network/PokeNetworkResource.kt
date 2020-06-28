package com.nanoyatsu.example.clean.poke.data.resource.network

import me.sargunvohra.lib.pokekotlin.client.PokeApi

class PokeNetworkResource(private val client: PokeApi) {
    fun get(id: Int) = client.getPokemon(id)
    fun list(offset: Int, limit: Int) = client.getPokemonList(offset, limit)
}