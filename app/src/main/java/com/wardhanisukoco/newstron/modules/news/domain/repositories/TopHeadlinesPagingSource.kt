package com.wardhanisukoco.newstron.modules.news.domain.repositories

import com.wardhanisukoco.newstron.infrastructure.adapter.BasePagingSource
import com.wardhanisukoco.newstron.modules.news.domain.models.Article
import com.wardhanisukoco.newstron.modules.news.domain.models.ArticlesResponse
import com.wardhanisukoco.newstron.modules.news.domain.services.NewsApiService
import com.wardhanisukoco.newstron.infrastructure.network.ApiRequest
import com.wardhanisukoco.newstron.infrastructure.network.ApiResponse

class TopHeadlinesPagingSource(
    private val category: String? = null,
    private val service: NewsApiService
) : BasePagingSource<Article, ArticlesResponse>() {
    override suspend fun call(pageIndex: Int): ApiResponse<ArticlesResponse> {
        return ApiRequest.safeApiCall {
            service.getTopHeadlines(
                category = category,
                pageSize = NETWORK_PAGE_SIZE,
                page = pageIndex
            )
        }
    }

    override fun getList(response: ApiResponse<ArticlesResponse>): List<Article> {
        return response.body.articles
    }

//    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
//        val pageIndex = params.key ?: STARTING_PAGE_INDEX
//        return try {
//            val response = ApiRequest.safeApiCall {
//                service.getTopHeadlines(
//                    category = category,
//                    pageSize = NETWORK_PAGE_SIZE,
//                    page = pageIndex
//                )
//            }
//            if (response.isSuccessful) {
//                val articles = response.body.articles
//                val nextKey =
//                    if (articles.isEmpty()) {
//                        null
//                    } else {
//                        pageIndex + (params.loadSize / NETWORK_PAGE_SIZE)
//                    }
//                LoadResult.Page(
//                    data = articles,
//                    prevKey = if (pageIndex == STARTING_PAGE_INDEX) null else pageIndex,
//                    nextKey = nextKey
//                )
//            } else {
//                return LoadResult.Error(response.exception!!)
//            }
//        } catch (exception: IOException) {
//            return LoadResult.Error(exception)
//        } catch (exception: HttpException) {
//            return LoadResult.Error(exception)
//        }
//    }
//
//    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
//        return state.anchorPosition?.let { anchorPosition ->
//            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
//                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
//        }
//    }
//
//    companion object {
//        const val NETWORK_PAGE_SIZE = 5
//    }
}