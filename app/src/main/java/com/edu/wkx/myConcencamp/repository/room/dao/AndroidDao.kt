package com.edu.wkx.myConcencamp.repository.room.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.edu.wkx.myConcencamp.repository.room.model.AndroidModel

@Dao
interface AndroidDao {

    @Query("select * from android where type = :arg0 order by publishedAt desc")
    fun selectList(type: String): LiveData<List<AndroidModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertList(list: List<AndroidModel>)

}