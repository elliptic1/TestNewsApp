package com.tbse.mininews.service.client

import com.tbse.mininews.service.model.Posts
import retrofit2.http.GET

/**
 * Created by toddsmith on 9/4/21.
 * Copyright TBSE 2017
 */
interface ApiClient {

    @GET("news")
    suspend fun getPosts(): Posts

}