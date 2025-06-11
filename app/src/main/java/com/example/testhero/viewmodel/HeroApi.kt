package com.example.testhero.viewmodel


import com.example.testhero.model.Superhero
import retrofit2.http.GET
import retrofit2.http.Path

interface HeroApi {
    @GET("all.json")
    suspend fun getHeroAll(): List<Superhero>
    @GET("/id/{id}.json")
    suspend fun getProductById(@Path("id") id: Int): Superhero

}