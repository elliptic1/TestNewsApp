package com.tbse.mininews.domain.data.posts.impl

import android.content.Context
import android.graphics.drawable.Drawable
import coil.ImageLoader
import coil.request.ImageRequest
import com.tbse.mininews.common.utils.network.ServiceResult
import com.tbse.mininews.domain.data.mapper.MapArticleToPost
import com.tbse.mininews.domain.data.posts.PostsRepository
import com.tbse.mininews.domain.model.Post
import com.tbse.mininews.domain.model.PostsFeed
import com.tbse.mininews.service.client.ApiClient
import com.tbse.mininews.service.client.ApiConstants
import com.tbse.mininews.service.model.Article
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PostsRepositoryImpl(val context: Context) : PostsRepository {

    private var service: ApiClient

    private val articleToPost = MapArticleToPost()

    private val imageLoader = ImageLoader(context)

    init {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(ApiConstants.serviceUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        service = retrofit.create(ApiClient::class.java)

    }

    override suspend fun getPost(postId: String?): ServiceResult<Post> {
        TODO("Not yet implemented")
    }

    private suspend fun getImageDrawable(article: Article): Drawable? {
        val request = ImageRequest.Builder(context)
            .data(article.urlToImage)
            .build()
        return imageLoader.execute(request).drawable
    }

    override suspend fun getPostsFeed(): ServiceResult<PostsFeed> {
        return withContext(Dispatchers.IO) {
            val response = service.getPosts()
            val articles = response.articles ?: listOf()
            val total = response.totalResults ?: 0

            val popular = mutableListOf<Post>()
            val recommended = mutableListOf<Post>()
            val recent = mutableListOf<Post>()

            val highlightedPost = articleToPost(
                articles[0],
                -1//getImageDrawable(articles[0])
            )

            if (total > 3) {
                popular.addAll(
                    articles.subList(1, 3).map {
                        articleToPost(
                            it,
                            -1//getImageDrawable(articles[0])
                        )
                    }
                )
            }

            if (total > 5) {
                recommended.addAll(
                    articles.subList(3, 5).map {
                        articleToPost(
                            it,
                            -1//getImageDrawable(articles[0])
                        )
                    }
                )
            }

            if (total > 7) {
                recent.addAll(
                    articles.subList(7, total).map {
                        articleToPost(
                            it,
                            -1//getImageDrawable(articles[0])
                        )
                    }
                )
            }

            ServiceResult.Success(
                PostsFeed(
                    highlightedPost = highlightedPost,
                    popularPosts = popular,
                    recentPosts = recent,
                    recommendedPosts = recommended
                )
            )
        }
    }

    override fun observeFavorites(): Flow<Set<String>> {
        return flowOf(emptySet())
    }

    override suspend fun toggleFavorite(postId: String) {
        TODO("Not yet implemented")
    }
}