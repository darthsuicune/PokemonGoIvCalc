package com.dlgdev.goivcalc.models

import retrofit2.Call
import retrofit2.http.GET

interface PokemonServer {
    @GET("/dex/go/version")
    fun getVersion(): Call<Int>

    @GET("/dex/go/stats")
    fun getStats(): Call<PokemonStats>

}