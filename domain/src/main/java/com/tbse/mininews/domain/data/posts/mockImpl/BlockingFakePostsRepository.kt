/*
 * Copyright 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.tbse.mininews.domain.data.posts.mockImpl

import com.tbse.mininews.domain.data.posts.PostsRepository
import com.tbse.mininews.domain.model.Post
import com.tbse.mininews.domain.model.PostsFeed
import com.tbse.mininews.common.utils.addOrRemove
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.withContext
import com.tbse.mininews.common.utils.network.ServiceResult

/**
 * Implementation of PostsRepository that returns a hardcoded list of
 * posts with resources synchronously.
 */
@OptIn(ExperimentalCoroutinesApi::class)
class BlockingFakePostsRepository : PostsRepository {

    // for now, keep the favorites in memory
    private val favorites = MutableStateFlow<Set<String>>(setOf())

    override suspend fun getPost(postId: String?): ServiceResult<Post> {
        return withContext(Dispatchers.IO) {
            val post = posts.allPosts.find { it.id == postId }
            if (post == null) {
                ServiceResult.Error(IllegalArgumentException("Unable to find post"))
            } else {
                ServiceResult.Success(post)
            }
        }
    }

    override suspend fun getPostsFeed(): ServiceResult<PostsFeed> {
        return ServiceResult.Success(posts)
    }

    override fun observeFavorites(): Flow<Set<String>> = favorites

    override suspend fun toggleFavorite(postId: String) {
        val set = favorites.value.toMutableSet()
        set.addOrRemove(postId)
        favorites.value = set
    }
}
