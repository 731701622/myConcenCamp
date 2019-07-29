package com.edu.wkx.myConcencamp.repository.room

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.edu.wkx.myConcencamp.repository.room.dao.AndroidDao
import com.edu.wkx.myConcencamp.repository.room.dao.WelfareDao
import com.edu.wkx.myConcencamp.repository.room.model.AndroidModel
import com.edu.wkx.myConcencamp.repository.room.model.WelfareModel

@Database(entities = arrayOf(AndroidModel::class, WelfareModel::class), version = 1)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        val TAG = "wkx_camp"
    }

    abstract fun androidDao(): AndroidDao
    abstract fun welfareDao(): WelfareDao
}