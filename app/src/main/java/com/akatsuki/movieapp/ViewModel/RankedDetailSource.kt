package com.akatsuki.movieapp.ViewModel

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.akatsuki.movieapp.models.remote.Ranked.Data
import com.akatsuki.movieapp.repository.DataRepository

class RankedDetailSource(
    private val repo: DataRepository
) : PagingSource<Int, Data>() {

    override fun getRefreshKey(state: PagingState<Int, Data>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Data> {
        return try {
            val nextPageNumber = params.key ?: 1
            val response =  repo.getRank(nextPageNumber)
            LoadResult.Page(
                data = response.data?.data!!,
                prevKey = null,
                nextKey = if (response.data.data.isNotEmpty())  nextPageNumber + 1 else null
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}