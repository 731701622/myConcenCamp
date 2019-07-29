package com.edu.wkx.myConcencamp.di.component


import com.edu.wkx.myConcencamp.di.module.AppModule
import com.edu.wkx.myConcencamp.di.module.RemoteModule
import com.edu.wkx.myConcencamp.di.module.RoomModule
import com.edu.wkx.myConcencamp.view.viewmodel.AndroidViewModel
import com.edu.wkx.myConcencamp.view.viewmodel.WelfareViewModel
import dagger.Component
import javax.inject.Singleton


@Component(modules = arrayOf(AppModule::class, RoomModule::class, RemoteModule::class))
@Singleton
interface AppComponent {

    fun injectAndroid(viewModel: AndroidViewModel)

    fun injectWelfare(viewModel: WelfareViewModel)
}