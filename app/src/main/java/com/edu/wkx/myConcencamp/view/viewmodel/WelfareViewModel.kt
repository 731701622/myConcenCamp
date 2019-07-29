package com.edu.wkx.myConcencamp.view.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.edu.wkx.myConcencamp.App
import com.edu.wkx.myConcencamp.repository.WelfareRepo
import com.edu.wkx.myConcencamp.repository.room.model.WelfareModel
import javax.inject.Inject

class WelfareViewModel : ViewModel() {

    @Inject lateinit var welfareRepo: WelfareRepo

    private var list = MutableLiveData<List<WelfareModel>>()

    init {
        initializeDagger()
    }

    private fun initializeDagger() = App.appComponent.injectWelfare(this)

    fun getList(page: String): LiveData<List<WelfareModel>>? {
        return welfareRepo.loadList(page)
    }

    fun getImgById(id: String) = welfareRepo.getImgById(id)
}