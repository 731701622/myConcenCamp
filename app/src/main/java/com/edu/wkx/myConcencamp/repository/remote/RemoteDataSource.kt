package com.edu.wkx.myConcencamp.repository.remote

import com.edu.wkx.myConcencamp.repository.remote.api.RemoteService
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val service: RemoteService) {

    fun loadAndroidList(page: String, type: String) = service.getAndroidList(page, type)

    fun loadWelfareList(page: String) = service.getWelfareList(page)
}