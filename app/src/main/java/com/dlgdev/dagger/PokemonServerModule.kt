package com.dlgdev.dagger

import com.dlgdev.goivcalc.models.server.PokemonServer
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class PokemonServerModule {
    @Provides
    fun provideRetrofit(): PokemonServer {
        return Retrofit.Builder().baseUrl("http://localhost:8008")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(PokemonServer::class.java)
    }
}