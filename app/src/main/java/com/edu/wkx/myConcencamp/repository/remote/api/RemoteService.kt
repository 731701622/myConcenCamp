package com.edu.wkx.myConcencamp.repository.remote.api

import com.edu.wkx.myConcencamp.entity.AndroidEntity
import com.edu.wkx.myConcencamp.entity.WelfareEntity
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path


interface RemoteService {

    @GET("data/{type}/10/{page}")
    fun getAndroidList(@Path("page") page:String, @Path("type") type:String):Observable<AndroidEntity>

    @GET("data/福利/10/{page}")
    fun getWelfareList(@Path("page") page:String):Observable<WelfareEntity>

}