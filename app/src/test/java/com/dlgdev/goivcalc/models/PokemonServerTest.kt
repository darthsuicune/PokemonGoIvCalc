package com.dlgdev.goivcalc.models

import com.dlgdev.dagger.PokemonServerModule
import com.dlgdev.goivcalc.models.server.PokemonServer
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert
import org.junit.Test

class PokemonServerTest {
    val server: PokemonServer = PokemonServerModule().provideRetrofit()

    @Test fun testServerResponseForVersion() {
        var i: Int? = -1
        i = server.getVersion().execute().body()
        Assert.assertThat(i, `is`(0))
    }

    @Test fun testServerResponseForStats() {
        val stats = server.getStats().execute().body()
        Assert.assertThat(stats?.version, `is`(0))
        val celebi = stats?.mons?.filter { it.id == 251 }!![0]
        Assert.assertThat(celebi.stamina, `is`(200))

    }
}