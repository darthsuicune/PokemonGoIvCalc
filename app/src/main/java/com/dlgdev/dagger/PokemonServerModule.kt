package com.dlgdev.dagger

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class PokemonServerModule {
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl("localhost:8008").build()
    }
}