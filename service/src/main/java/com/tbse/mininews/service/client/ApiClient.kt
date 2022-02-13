package com.tbse.mininews.service.client

import retrofit2.http.GET

/**
 * Created by toddsmith on 9/4/21.
 * Copyright TBSE 2017
 */
interface ApiClient {

    @GET("/top-headlines?country=us&apiKey=${ApiKey.KEY}")
    suspend fun getPosts()

}