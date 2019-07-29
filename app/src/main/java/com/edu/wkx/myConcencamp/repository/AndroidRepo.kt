package com.edu.wkx.myConcencamp.repository

import android.arch.lifecycle.LiveData
import com.edu.wkx.myConcencamp.repository.room.AppDatabase
import com.edu.wkx.myConcencamp.repository.remote.RemoteDataSource
import com.edu.wkx.myConcencamp.repository.room.model.AndroidModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AndroidRepo @Inject constructor(
        private val roomDataSource: AppDatabase,
        private val remoteDataSource: RemoteDataSource) {

    fun loadList(page: String, type: String): LiveData<List<AndroidModel>>? {
        remoteDataSource.loadAndroidList(page, type)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ entity ->
                    entity.results?.let { resList ->
                        roomDataSource.androidDao().insertList(resList)
                    }
                }, { t: Throwable -> t.printStackTrace() })
        return roomDataSource.androidDao().selectList(type)
    }

}


