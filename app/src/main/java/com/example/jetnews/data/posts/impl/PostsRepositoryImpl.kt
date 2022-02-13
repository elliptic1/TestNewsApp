package com.example.jetnews.data.posts.impl

import com.example.jetnews.data.Result
import com.example.jetnews.data.posts.PostsRepository
import com.example.jetnews.model.Post
import com.example.jetnews.model.PostsFeed
import kotlinx.coroutines.flow.Flow

class PostsRepositoryImpl : PostsRepository {
    override suspend fun getPost(postId: String?): Result<Post> {
        TODO("Not yet implemented")
    }

    override suspend fun getPostsFeed(): Result<PostsFeed> {
        TODO("Not yet implemented")
    }

    override fun observeFavorites(): Flow<Set<String>> {
        TODO("Not yet implemented")
    }

    override suspend fun toggleFavorite(postId: String) {
        TODO("Not yet implemented")
    }
}