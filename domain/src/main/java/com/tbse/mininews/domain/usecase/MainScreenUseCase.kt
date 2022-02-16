package com.tbse.mininews.domain.usecase

import android.content.Context
import com.tbse.mininews.common.utils.network.ServiceResult
import com.tbse.mininews.domain.data.posts.PostsRepository
import com.tbse.mininews.domain.data.posts.impl.PostsRepositoryImpl
import com.tbse.mininews.domain.model.PostsFeed


/**
 * Created by toddsmith on 2/12/22.
 */
class MainScreenUseCase(context: Context) {

    private var postsRepository: PostsRepository = PostsRepositoryImpl(context)

    suspend fun getPosts(): ServiceResult<PostsFeed> {
        return postsRepository.getPostsFeed()
    }
}