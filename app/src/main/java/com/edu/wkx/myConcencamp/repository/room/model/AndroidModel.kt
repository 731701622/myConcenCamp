package com.edu.wkx.myConcencamp.repository.room.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "android")
class AndroidModel {

    @PrimaryKey
    var _id = ""
    var createdAt: String? = null
    var desc: String? = null
    var publishedAt: String? = null
    var type: String? = null
    var url: String? = null
    var who: String? = null

}