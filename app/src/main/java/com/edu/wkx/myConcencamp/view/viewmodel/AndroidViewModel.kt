package com.edu.wkx.myConcencamp.view.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.edu.wkx.myConcencamp.App
import com.edu.wkx.myConcencamp.repository.room.model.AndroidModel
import com.edu.wkx.myConcencamp.repository.AndroidRepo
import javax.inject.Inject

class AndroidViewModel : ViewModel() {

    @Inject lateinit var androidRepo: AndroidRepo

    init {
        initializeDagger()
    }

    private fun initializeDagger() = App.appComponent.injectAndroid(this)

    fun getList(page: String, type: String): LiveData<List<AndroidModel>>? {
        return androidRepo.loadList(page, type)
    }

}