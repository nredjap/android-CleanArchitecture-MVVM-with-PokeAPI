package com.nanoyatsu.example.clean.poke.domain.pokeDetail

import com.nanoyatsu.example.clean.poke.core.base.UseCase

class RefreshPokeDetail(private val repo: PokeDetailRepository) :
    UseCase<Int, PokeDetail>() {
    override suspend fun run(params: Int): PokeDetail {
        return repo.refresh(params)
    }
}