package com.edu.wkx.myConcencamp.repository

import android.arch.lifecycle.LiveData
import com.edu.wkx.myConcencamp.repository.remote.RemoteDataSource
import com.edu.wkx.myConcencamp.repository.room.AppDatabase
import com.edu.wkx.myConcencamp.repository.room.model.WelfareModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WelfareRepo @Inject constructor(
        private val roomDataSource: AppDatabase,
        private val remoteDataSource: RemoteDataSource) {

    fun loadList(page: String) : LiveData<List<WelfareModel>>? {
        remoteDataSource.loadWelfareList(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ entity ->
                    entity.results?.let { resList ->
                        roomDataSource.welfareDao().insertList(resList)
                    }
                }, { t: Throwable -> t.printStackTrace() })
        return roomDataSource.welfareDao().selectList()
    }

    fun getImgById(id: String) = roomDataSource.welfareDao().selectById(id)
}