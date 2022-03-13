package com.wardhanisukoco.newstron.application.adapter

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.wardhanisukoco.newstron.infrastructure.network.ApiResponse
import retrofit2.HttpException
import java.io.IOException

abstract class BasePagingSource<T : Any, R: Any> : PagingSource<Int, T>() {
    companion object {
        const val NETWORK_PAGE_SIZE = 5
        const val STARTING_PAGE_INDEX = 1
    }
    abstract suspend fun call(pageIndex: Int): ApiResponse<R>
    abstract fun getList(response: ApiResponse<R>): List<T>
    protected fun getResult(list: List<T>, nextKey: Int?, prevKey: Int?): LoadResult.Page<Int, T> {
        return LoadResult.Page(
            data = list,
            prevKey = prevKey,
            nextKey = nextKey
        )
    }
    protected fun getPrevKey(pageIndex: Int): Int? {
        return if (pageIndex == STARTING_PAGE_INDEX) null else pageIndex
    }
    protected fun getNextKey(list: List<T>, loadSize: Int, pageIndex: Int): Int? {
        return if (list.isEmpty()) {
            null
        } else {
            pageIndex + (loadSize / NETWORK_PAGE_SIZE)
        }
    }
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, T> {
        val pageIndex = params.key ?: STARTING_PAGE_INDEX
        return try {
            val response = call(pageIndex)
            if (response.isSuccessful) {
                val list = getList(response)
                val nextKey = getNextKey(list, params.loadSize, pageIndex)
                val prevKey = getPrevKey(pageIndex)
                getResult(list, nextKey, prevKey)
            } else {
                LoadResult.Error(response.exception!!)
            }
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, T>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}