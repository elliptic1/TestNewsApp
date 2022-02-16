package com.tbse.mininews.domain.data.posts.impl

import com.tbse.mininews.common.utils.network.ServiceResult
import com.tbse.mininews.domain.data.posts.PostsRepository
import com.tbse.mininews.domain.model.Post
import com.tbse.mininews.domain.model.PostsFeed
import kotlinx.coroutines.flow.Flow

class PostsRepositoryImpl : PostsRepository {
    override suspend fun getPost(postId: String?): ServiceResult<Post> {
        TODO("Not yet implemented")
    }

    override suspend fun getPostsFeed(): ServiceResult<PostsFeed> {
        TODO("Not yet implemented")
    }

    override fun observeFavorites(): Flow<Set<String>> {
        TODO("Not yet implemented")
    }

    override suspend fun toggleFavorite(postId: String) {
        TODO("Not yet implemented")
    }
}