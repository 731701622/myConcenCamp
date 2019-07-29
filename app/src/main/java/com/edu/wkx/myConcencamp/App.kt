package com.edu.wkx.myConcencamp

import android.app.Application
import com.edu.wkx.myConcencamp.di.component.AppComponent
import com.edu.wkx.myConcencamp.di.component.DaggerAppComponent
import com.edu.wkx.myConcencamp.di.module.AppModule
import com.edu.wkx.myConcencamp.di.module.RemoteModule
import com.edu.wkx.myConcencamp.di.module.RoomModule
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger

class App : Application() {

    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Logger.addLogAdapter(AndroidLogAdapter())
            Logger.d("开启Debug模式")
        }
        initializeDagger()
    }

    private fun initializeDagger() {
        appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .roomModule(RoomModule())
                .remoteModule(RemoteModule())
                .build()
    }
}